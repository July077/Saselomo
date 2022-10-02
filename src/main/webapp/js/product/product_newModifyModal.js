$(function(){
	console.log('产品新建|修改modal打开了')
	$("#newModifyModal").on('click','.productOk', doSaveOrUpdate);
	$('#productNameModify').on('blur', productNameBlur);
	$('#productCartonSizeModify').on('change', proCartonSizeChange)
	$("#newModifyModal").on('change', '.proPrice', proPriceChange);
	
	//获得模态框上绑定的id值
	var id=$("#newModifyModal").data("id");
	//假如id有值,说明这是修改,然后根据id获得对象,初始化模态框数据
	if(id) doGetObjectByModify(id);
	//当模态框隐藏时在.productOk上绑定的事件执行解绑动作
	var count = 0;//计数器,强制模态框组件事件监听只出现一次
	$("#newModifyModal").on("hidden.bs.modal",function() {
		if (++count == 1) {
			$(this).off('click', '.productOk').removeData("id");
		}
	});
});
//产品价格值改变事件
function proPriceChange(){
	let proPrice = $(this).val();
	if(proPrice==''){
		return;
	}
	console.log(proPrice)
	let reg1 = /^\d{1,7}$/;//先判定是否为7位数字
	let reg2 = /^\d{7}[.]\d{1,2}$/;//在进行判定是否为浮点型
	if(!reg1.test(proPrice)){//不匹配
		console.log(proPrice)
		if(!reg2.test(proPrice)){
			$(this).val('');
			$('#alertInfo').html('金额输入错误,请输入7整数或格式为0.00,整数最大为7位');
			$('#alertBox').slideDown(300);
		}
	}
}
//箱规数量框值改变事件,进行判定输入是否合法
function proCartonSizeChange(){
	let boo = countRegEx('','','');
	if(boo){
		
	}
}
//产品名框失去焦点事件,进行是否为空的判断
function productNameBlur(){
	inputNullValueJudge('productNameModify','亲,产品名为必输项,不能为空');
}
//根据id查找product对象
function doGetObjectByModify(id){
	var url="product/doFindProductById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
			//初始化表单数据
			doFillFormData(result.data);
		}else{
			alert(result.message);
		}
	});
}
//将获得的数据填充到form表单中
function doFillFormData(obj){
	$("#productNameModify").val(obj.name);
	$("#productAbbreviationModify").val(obj.abbreviation);
	$("#productCartonSizeModify").val(obj.cartonSize);
	$('#productFirstStageModify').val(obj.firstStage);
	$('#productSecondStageModify').val(obj.secondStage);
	$('#productSupremacyModify').val(obj.supremacy);
	$('#productDerivativeModify').val(obj.derivative);
	$('#productRetailModify').val(obj.retail);
	$('#productEffectModify').val(obj.effect);
	$('#productSellingPointsModify').val(obj.sellingPoints);
	$('#productNoteModify').val(obj.note);
}
//保存或更新产品信息
function doSaveOrUpdate(){
	//判定产品名是否为空
	let boo = inputNullValueJudge('productNameModify','亲,产品名为必输项,不能为空');
	if(boo){
		return;
	}
	//1.获得表单数据
	var params = doGetEditFormData();
	//2.将数据提交到服务端
	var id=$("#newModifyModal").data("id");
	var url=id?"product/doUpdateProduct.do":"product/doSaveProduct.do";
	$.post(url,params,function(result){
		if(result.state==1){
			//1)隐藏模态框
			$("#newModifyModal").modal("hide");
			//2)重新查询列表数据
			doGetObjects();
		}else{
		   alert(result.message);
		}
	});
}
//获取表单数据
function doGetEditFormData(){
	let firstStage = $('#productFirstStageModify').val();
	let secondStage = $('#productSecondStageModify').val();
	let supremacy = $('#productSupremacyModify').val();
	let derivative = $('#productDerivativeModify').val();
	let retail = $('#productRetailModify').val();
	let params={
			"id":$("#newModifyModal").data("id"),//修改时需要
			"name":$("#productNameModify").val(),
			"valid":'1',
			"abbreviation":$("#productAbbreviationModify").val(),
			"cartonSize":$("#productCartonSizeModify").val(),
			"firstStage":firstStage?firstStage:0,
			"secondStage":secondStage?secondStage:0,
			"supremacy":supremacy?supremacy:0,
			"derivative":derivative?derivative:0,
			"retail":retail?retail:0,
			"effect":$('#productEffectModify').val(),
			"sellingPoints":$('#productSellingPointsModify').val(),
			"note":$('#productNoteModify').val(),
			"createdUser":'花花',
			"modifiedUser":'花花'};
	//检测获得的结果
//	console.log(JSON.stringify(params));
	return params;
}