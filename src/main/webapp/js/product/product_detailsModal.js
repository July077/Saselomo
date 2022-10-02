$(function(){
	//获得模态框上绑定的id值
	var id=$("#detailsModal").data("id");
	doGetObjectByModify(id);
	//当模态框隐藏时删除绑定的id值
	var count = 0;//计数器,强制模态框组件事件监听只出现一次
	$("#newModifyModal").on("hidden.bs.modal",function() {
		if (++count == 1) {
			$(this).removeData("id");
		}
	});
});
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
//	console.log("doFillFormData");
//	console.log(JSON.stringify(obj));
	$("#productNameDetails").html(obj.name);
	$("#productAbbreviationDetails").html(obj.abbreviation);
	$("#productCartonSizeDetails").html(obj.cartonSize);
	$('#productFirstStageDetails').html(obj.firstStage);
	$('#productSecondStageDetails').html(obj.secondStage);
	$('#productSupremacyDetails').html(obj.supremacy);
	$('#productDerivativeDetails').html(obj.derivative);
	$('#productRetailDetails').html(obj.retail);
	$('#productEffectDetails').html(obj.effect);
	$('#productSellingPointsDetails').html(obj.sellingPoints);
	$('#productNoteDetails').html(obj.note);
}
