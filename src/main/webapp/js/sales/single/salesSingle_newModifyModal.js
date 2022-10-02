$(function(){
	//页面加载时,获取信息,填入选择产品的下拉菜单中
	doGetProductInfo();
	$('#productNameSalSingle').on('keyup', doGetProductInfo);//选择产品下拉框事件,进行产品查询
	$('#productDropdownMenu').on('click', 'a', chooseFillProductName);
	$('#gradeDropdownMenu').on('click', 'a', chooseGrade);
	$('#newModifyModalBills').on('click', '.salSingleOk', doSaveOrUpdateSalSingle);
	//将数量框绑定失去焦点事件,如判断库存是否足够,用户是否输入合法
	$('#salSingleCount').on('blur', salSingleCountBlur);
	$('#productNameSalSingle').on('change', proNameSalSingleChange);
	
	let salSingleId= $("#newModifyModalBills").data("id");
	//假如id有值,说明这是修改,然后根据id获得对象,初始化模态框数据
	if(salSingleId) doGetSalSingleById(salSingleId);
	
	//当模态框隐藏时,执行相关动作
	var count = 0;//计数器,强制模态框组件事件监听只出现一次
	$("#newModifyModalBills").on("hidden.bs.modal",function() {
		if (++count == 1) {
			//.recSingleOk上绑定的事件执行解绑动作,删除绑定数据
			$(this).off('click', '.salSingleOk').removeData("id");
			//获得模态框上绑定的id值
			let salId=$("#detailsModal").data("id");
			doGetObjectById(salId);
		}
	});
});
//用户选定产品后,进行输入查询后,但未更换,需补全原产品名;用户输入但未选定
function proNameSalSingleChange(){
	console.log('proNameSalSingleChange')
	let pro = $('#productNameSalSingle').data('product');
	console.log(pro)
	if(pro){
		$(this).val(pro.name);
		doGetProductInfo();
	}else{//没有进行产品选定
		$(this).val('');//清空输入
		doGetProductInfo();
		return alert('亲,请先选择产品');
	}
}
//将数量框绑定失去焦点,如判断库存是否足够,用户是否输入
function salSingleCountBlur(){
	let countInput = $(this).val();
	//判定产品是否选择,未选择,结束此方法,提示用户
	let boo1 = inputNullValueJudge('productNameSalSingle', '亲,请先选择产品');
	if(boo1){
		return;
	}
	//判定数量是否为空,若为空,结束此方法,提示用户
	let boo2 = inputNullValueJudge('salSingleCount', '亲,数量不能为空');
	if(boo2){
		return;
	}
	//判定数量是否输入为>1&<99999的数
	let boo3 = countRegEx('salSingleCount', 'salInvenJudgeAlert', '亲,请输入1到99999的数字');
	if(boo3){
		return;
	}
	//判定库存是否足够
	judgeInvenSatisfy(countInput);
}
//判断库存是否足够
function judgeInvenSatisfy(countInput){
	console.log('judgeInvenSatisfy')
	let product = $('#productNameSalSingle').data('product');//获取产品信息
	let url = 'inventory/doFindObjectByProId.do';
	let params = {
		'productId':product.id	
	};
	$.post(url, params, function(result){
		if(countInput > result.data.inventoryAvailable){
			$('#salSingleCount').next().html('此产品库存不足,库存可用为:'+result.data.inventoryAvailable);
		}else{
			$('#salSingleCount').next().html('');
		}
	});
}
//根据id获取售货单子项相关信息
function doGetSalSingleById(id){
	//将产品名称设为只读
	$('#productNameSalSingle').attr('readonly', true);
	//将产品名取消下拉
	$('#productNameSalSingle').attr('data-toggle', '');
	var url="salesSingle/doFindSalSingleById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
			//初始化表单数据
			doFillSalSingleFormData(result.data);
			var product = {
					'id':result.data.productId,
					'firstStage': result.data.firstStage,
					'secondStage': result.data.secondStage,
					'supremacy': result.data.supremacy,
					'derivative': result.data.derivative,
					'retail': result.data.retail
			};//获取产品价格信息
			$('#productNameSalSingle').data('product', product);//将产品价格信息绑定到产品名输入框,便于选择级别时,获取价格信息
		}else{
			alert(result.message);
		}
	});
}
//将售货单子项相关信息填入表单
function doFillSalSingleFormData(salSingle){
	$('#productNameSalSingle').val(salSingle.name);
	$('#proPrice').val(salSingle.salePrice);
	$('#salSingleCount').val(salSingle.count);
	$('#salSingleValid').data('valid', salSingle.valid);
}
//存储或更新售货单子项信息
function doSaveOrUpdateSalSingle(){
	//判定产品是否选择,未选择,结束此方法,提示用户
	let boo1 = inputNullValueJudge('productNameSalSingle', '亲,请先选择产品');
	if(boo1){
		return;
	}
	//判定价格是否为空,若为空,结束此方法,提示用户
	let boo2 = inputNullValueJudge('proPrice', '亲,请选择级别,生成价格信息');
	if(boo2){
		return;
	}
	//判定数量是否为空,若为空,结束此方法,提示用户
	let boo3 = inputNullValueJudge('salSingleCount', '亲,数量不能为空');
	if(boo3){
		return;
	}
	//判定输入是否符合规则, || 库存是否充足, 不满足结束此方法
	let content = $('#salInvenJudgeAlert').html();
	if(content){
		return alert(content);
	}
	//1.获得表单数据
	var product = $('#productNameSalSingle').data('product');
	var params = doGetEditFormDataSalSingle();
	params.productId = product.id;//已选择产品,将产品id加入
	//2.将数据提交到服务端
	var id=$("#newModifyModalBills").data("id");//获取绑定的id,若没有,说明是新建,否则反之
	var url=id?"salesSingle/doUpdateSalSingle.do":"salesSingle/doSaveSalesSingle.do";
	$.post(url,params,function(result){
		if(result.state==1){
			//1)隐藏模态框
			$("#newModifyModalBills").modal("hide");
			//2)重新查询列表数据
			doGetSalesSingle($("#detailsModal").data("id"));
		}else{
		   alert(result.message);
		}
	});
}
//获取售货单子项,表单数据
function doGetEditFormDataSalSingle(){
	var salesId = $("#detailsModal").data("id");//获取售货单id
	var params = {
			'id': $("#newModifyModalBills").data("id"),
			'valid': $('#salSingleValid').data('valid')?$('#salSingleValid').data('valid'):1,
			'salePrice': $('#proPrice').val(),
			'count': $('#salSingleCount').val(),
			'salesId':salesId
	};
	return params;
}
//选择级别,进行输入框填充
function chooseGrade(){
	var grade = $(this).html();//获取级别
	$('#gradeSalSingle').val(grade);//填充级别信息
	$('#salSingleCount').val('');//将数量清空
	$('#salInvenJudgeAlert').html('');//将库存判定提示清空
	//填充产品价格信息
	fillProPrice(grade);
}
function fillProPrice(grade){
	var product = $('#productNameSalSingle').data('product');//获取产品信息
	if(!product){
		debugger
		alert('亲,请先选择产品');
		return;
	}
	if(grade == '一级'){
		$('#proPrice').val(product.firstStage);
	}else if(grade == '二级'){
		$('#proPrice').val(product.secondStage);
	}else if (grade == '至尊VIP') {
		$('#proPrice').val(product.supremacy);
	}else if (grade == '微商') {
		$('#proPrice').val(product.derivative);
	}else if(grade = '零售'){
		$('#proPrice').val(product.retail);
	}
}
//选择填充产品名
function chooseFillProductName(){
	var productName = $(this).html();//获取产品名
	$('#productNameSalSingle').val(productName);//填充到输入框
	$('#salSingleCount').val('');//将数量清空
	$('#salInvenJudgeAlert').html('');//将库存判定提示清空
	var product = $(this).parent().data('product');//获取产品信息
	$('#productNameSalSingle').data('product', product);//将产品信息绑定到产品名输入框,便于选择级别时,获取价格信息
	var grade = $('#gradeSalSingle').val();//判定级别有没有选定
	if(grade){
		fillProPrice(grade);
	}
}

//页面加载时,获取产品信息,填入选择产品的下拉菜单中
function doGetProductInfo(){
	var url = 'product/doFindInvenInObjectByName.do';
	var name = $('#productNameSalSingle').val();//获取输入的产品名
	var params = {
			'name':name
	};
	$.post(url, params, function(result){
//		console.log(JSON.stringify(result.data))
//		if(!result.data.length){
//			return alert("库存为空,请进行添加...");//库存为空时,报错
//		}
		doFillDropdownMenuData(result.data);
	});
};
//将获取的产品信息名称填入到下拉菜单
function doFillDropdownMenuData(products){
//	console.log(JSON.stringify(products))
	var ulProduct = $('#productDropdownMenu');//获取下拉菜单
	ulProduct.empty();//情况
	for(var i in products){
		var li = $('<li></li>');//创建li
		//绑定此产品数据,方便选中后操作
		li.data('product',products[i]);
		var a = '<a href="javascript:;">' + products[i].name + '</a>';//创建a标签
		li.append(a);//将a标签追加到li
		ulProduct.append(li);//将li对象追加到下拉表
	}
}



