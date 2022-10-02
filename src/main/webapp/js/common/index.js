$(function(){
	$('#deleteModal').on('click', '.okBtn', doDeleteObject);
	//当模态框隐藏时删除绑定的id值和class值
	$("#deleteModal").on("hidden.bs.modal",function() {
		$(this).off('click', 'okBtn').removeData("id").removeData("className");
		DelOrUpdateRec();
		DelOrUpdateSales();
	});
	
});

//如果模态框有salesId值,将此值进行删除,同时刷新收货单详情信息和收货单子项
function DelOrUpdateSales(){
	var salesId = $('#deleteModal').data('salesId');//收货单子项中,关联的收货单id
	if(salesId){
		$('#deleteModal').removeData("salesId");
		//获得模态框上绑定的id值
		var id=$("#detailsModal").data("id");
		doGetObjectById(id);
		doGetSalesSingle(id);
	}
}
//如果模态框有receivingId值,将此值进行删除,同时刷新收货单详情信息和收货单子项
function DelOrUpdateRec(){
	var receivingId = $('#deleteModal').data('receivingId');//收货单子项中,关联的收货单id
	if(receivingId){
		$('#deleteModal').removeData("receivingId");
		//获得模态框上绑定的id值
		var id=$("#detailsModal").data("id");
		doGetObjectById(id);
		doGetReceivingSingle(id);
	}
}
//删除对应信息
function doDeleteObject(){
	//获取删除的id值
	var id = $('#deleteModal').data('id');
	//此id用于删除子项后更新收货单的价格信息
	var receivingId = $('#deleteModal').data('receivingId');//收货单子项中,关联的收货单id
	var params = {'id': id};
	
	//此id用于删除子项后更新售货单的价格信息
	var salesId = $('#deleteModal').data('salesId');//收货单子项中,关联的收货单id
	var params = {'id': id};
	
	if(receivingId){//如果有,则添加
		params.receivingId = receivingId;
	}
	if(salesId){//如果有,则添加
		params.salesId = salesId;
	}
//	console.log(JSON.stringify(params));
	//获取url
	var url = predicateDeleteUrl();
//	console.log('delUrl='+url);
	//执行删除动作
	$.post(url, params, function(result){
		if(result.state == 1){
			//1)隐藏模态框
			$("#deleteModal").modal("hide");
			//2)重新查询列表数据
			doGetObjects();
		}else{
			//1)隐藏模态框
			$("#deleteModal").modal("hide");
			$('#alertInfo').html(result.message);
			$('#alertBox').slideDown(300);
		}
	});
}
//判定执行哪个删除的url
function predicateDeleteUrl(){
	var url;
	//获取删除模态框上绑定的class值
	var className = $('#deleteModal').data('className').split(' ')[1];
	console.log(className)
	if(className == 'clientDeleteBtn'){//客户信息
		url = 'client/doDeleteClient.do';
		return url;
	}
	if(className == 'productDeleteBtn'){
		url = 'product/doDeleteProduct.do';
		return url;
	}
	if(className == 'receivingDeleteBtn'){
		url = 'receiving/doDeleteReceiving.do';
		return url;
	}
	if(className == 'recSingleDeleteBtn'){
		url = 'receivingSingle/doDeleteRecSingle.do';
		return url;
	}
	if(className == 'salesDeleteBtn'){
		url = 'sales/doDeleteSales.do';
		return url;
	}
	if(className == 'salSingleDeleteBtn'){
		url = 'salesSingle/doDeleteSalSingle.do';
		return url;
	}
	if(className == 'userDeleteBtn'){
		url = 'user/doDeleteUser.do';
		return url;
	}
	
}

