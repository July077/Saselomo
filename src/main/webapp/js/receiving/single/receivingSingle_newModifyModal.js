$(function(){
	//页面加载时,获取信息,填入选择产品的下拉菜单中
	doGetProductInfo();
	$('#productNameRecSingle').on('keyup', doGetProductInfo);//选择产品下拉框事件,进行产品查询
	$('#productDropdownMenu').on('click', 'a', chooseFillProductName);
	$('#gradeDropdownMenu').on('click', 'a', chooseGrade);
	$('#newModifyModalBills').on('click', '.recSingleOk', doSaveOrUpdateRecSingle);
	$('#productNameRecSingle').on('change', proNameRecSingleChange);
	//数量框失去焦点事件,判定数量输入是否合法为
	$('#recSingleCount').on('blur', recSingleCountBlur);
	
	var recSingleId=$("#newModifyModalBills").data("id");
	//假如id有值,说明这是修改,然后根据id获得对象,初始化模态框数据
	if(recSingleId) doGetRecSingleById(recSingleId);
	
	//当模态框隐藏时,执行相关动作
	var count = 0;//计数器,强制模态框组件事件监听只出现一次
	$("#newModifyModalBills").on("hidden.bs.modal",function() {
		if (++count == 1) {
			//.recSingleOk上绑定的事件执行解绑动作,删除绑定数据
			$(this).off('click', '.recSingleOk').removeData("id");
			//获得模态框上绑定的id值
			var recId=$("#detailsModal").data("id");
			doGetObjectById(recId);
		}
	});
});
//数量框失去焦点事件,判定数量输入是否合法为
function recSingleCountBlur(){
	//判定产品是否选择,未选择,结束此方法,提示用户
	let boo = inputNullValueJudge('productNameRecSingle', '亲,请先选择产品');
	if(boo){
		return;
	}
	let boo2 = inputNullValueJudge('recSingleCount', '亲,数量不能为空');
	if(boo2){
		return;
	}
	countRegEx('recSingleCount','recInvenJudgeAlert','亲,请输入1到99999的数字');
}
//用户选定产品后,进行输入查询后,但未更换,需补全原产品名;用户输入但未选定
function proNameRecSingleChange(){
	console.log('proNameRecSingleChange')
	let pro = $('#productNameRecSingle').data('product');
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
//根据id获取收货单子项相关信息
function doGetRecSingleById(id){
	//将产品名称设为只读
	$('#productNameRecSingle').attr('readonly', 'readonly');
	//将产品名取消下拉
	$('#productNameRecSingle').attr('data-toggle', '');
	var url="receivingSingle/doFindRecSingleById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
			//初始化表单数据
			doFillRecSingleFormData(result.data);
			var product = {
					'firstStage': result.data.firstStage,
					'secondStage': result.data.secondStage,
					'supremacy': result.data.supremacy,
					'derivative': result.data.derivative,
					'retail': result.data.retail
			};//获取产品价格信息
			$('#productNameRecSingle').data('product', product);//将产品价格信息绑定到产品名输入框,便于选择级别时,获取价格信息
		}else{
			alert(result.message);
		}
	});
}
//将收货单子项相关信息填入表单
function doFillRecSingleFormData(recSingle){
	$('#productNameRecSingle').val(recSingle.name);
	$('#proPrice').val(recSingle.purchasePrice);
	$('#recSingleCount').val(recSingle.count);
	$('#recSingleValid').data("valid", recSingle.valid);
}
//存储或更新收货单子项信息
function doSaveOrUpdateRecSingle(){
	//判定产品是否选择,未选择,结束此方法,提示用户
	let boo1 = inputNullValueJudge('productNameRecSingle', '亲,请先选择产品');
	if(boo1){
		return;
	}
	//判定价格是否为空,若为空,结束此方法,提示用户
	let boo2 = inputNullValueJudge('proPrice', '亲,请选择级别,生成价格信息');
	if(boo2){
		return;
	}
	//判定数量是否为空,若为空,结束此方法,提示用户
	let boo3 = inputNullValueJudge('recSingleCount', '亲,数量不能为空');
	if(boo3){
		return;
	}
	//判定用户数量输入是否合法
	let content = $('#recInvenJudgeAlert').html();
	if(content){
		return alert(content);
	}
	var product = $('#productNameRecSingle').data('product');//获取产品信息
	//1.获得表单数据
	var params = doGetEditFormDataRecSingle();
	params.productId = product.id;//已选择产品,将产品id加入
	//2.将数据提交到服务端
	var id=$("#newModifyModalBills").data("id");//获取绑定的id,若没有,说明是新建,否则反之
	var url=id?"receivingSingle/doUpdateRecSingle.do":"receivingSingle/doSaveReceivingSingle.do";
	$.post(url,params,function(result){
		if(result.state==1){
			//1)隐藏模态框
			$("#newModifyModalBills").modal("hide");
			//2)重新查询列表数据
			doGetReceivingSingle($("#detailsModal").data("id"));
		}else{
		   alert(result.message);
		}
	});
}
//获取收货单子项,表单数据
function doGetEditFormDataRecSingle(){
	var receivingId = $("#detailsModal").data("id");//获取收货单id
	var params = {
			'id': $("#newModifyModalBills").data("id"),
			'valid': $('#recSingleValid').data("valid")?$('#recSingleValid').data("valid"):1,
			'purchasePrice': $('#proPrice').val(),
			'count': $('#recSingleCount').val(),
			'receivingId':receivingId
	};
	return params;
}
//选择级别,进行输入框填充
function chooseGrade(){
	var grade = $(this).html();//获取级别
	$('#gradeRecSingle').val(grade);//填充级别信息
	//填充产品价格信息
	fillProPrice(grade);
}
function fillProPrice(grade){
	var product = $('#productNameRecSingle').data('product');//获取产品信息
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
	$('#productNameRecSingle').val(productName);//填充到输入框
	var product = $(this).parent().data('product');//获取产品信息
	$('#productNameRecSingle').data('product', product);//将产品信息绑定到产品名输入框,便于选择级别时,获取价格信息
	var grade = $('#gradeRecSingle').val();//判定级别有没有选定
	if(grade){
		fillProPrice(grade);
	}
}
//页面加载时,获取产品信息,填入选择产品的下拉菜单中
function doGetProductInfo(){
	var url = 'product/doFindObjectByName.do';
	var name = $('#productNameRecSingle').val();//获取输入的产品名
	var params = {
			'name':name
	};
	$.post(url, params, function(result){
		doFillDropdownMenuData(result.data);
	});
};
//将获取的产品信息名称填入到下拉菜单
function doFillDropdownMenuData(products){
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



