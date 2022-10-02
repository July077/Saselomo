$(function(){
	$("#newModifyModalBills").on('click','.receivingOk', doSaveOrUpdate);
	//获得模态框上绑定的id值
	var id=$("#newModifyModalBills").data("id");
	//假如id有值,说明这是修改,然后根据id获得对象,初始化模态框数据
	if(id) doGetObjectById(id);
	//当模态框隐藏时在.receivingOk上绑定的事件执行解绑动作
	var count = 0;//计数器,强制模态框组件事件监听只出现一次
	$("#newModifyModalBills").on("hidden.bs.modal",function() {
		if (++count == 1) {
			$(this).off('click', '.receivingOk').removeData("id");
		}
	});
	//调用日期控件
	datepicker();
});
//根据id查找receiving对象
function doGetObjectById(id){
	//将收货单编号设置为只读
	$('#receivingReceiptCodeModify').attr('readonly', 'readonly');
	var url="receiving/doFindReceivingById.do";
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
	$("#receivingReceiptCodeModify").val(obj.receiptCode);
	$("#receivingPurchaseTimeModify").val(obj.purchaseTime);
	$("#receivingNoteModify").val(obj.note);
	$("#receivingTotalPriceModify").data("totalPrice", obj.totalPrice);
	$("#recValidModify").data("valid", obj.valid);
}
//保存或更新收货单信息
function doSaveOrUpdate(){
//	console.log('doSaveOrUpdate');
	//1.获得表单数据
	var params = doGetEditFormData();
	//2.将数据提交到服务端
	var id=$("#newModifyModalBills").data("id");
	var url=id?"receiving/doUpdateReceiving.do":"receiving/doSaveReceiving.do";
	$.post(url,params,function(result){
		if(result.state==1){
			//1)隐藏模态框
			$("#newModifyModalBills").modal("hide");
			//2)重新查询列表数据
			doGetObjects();
		}else{
		   alert(result.message);
		}
	});
}
//获取表单数据
function doGetEditFormData(){
	var params={
			'id':$("#newModifyModalBills").data("id"),//修改时需要
			'receiptCode':$("#receivingReceiptCodeModify").val(),
			'purchaseTime':$("#receivingPurchaseTimeModify").val(),
			'note':$("#receivingNoteModify").val(),
			'valid':$("#recValidModify").data("valid")?$("#recValidModify").data("valid"):1,
			'totalPrice':$("#receivingTotalPriceModify").data("totalPrice")?$("#receivingTotalPriceModify").data("totalPrice"):0,
			'createdUser':'花花',
			'modifiedUser':'花花'
	};
	//检测获得的结果
//	console.log(JSON.stringify(params));
	return params;
}