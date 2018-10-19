$(function(){
	$('#clientDropdownMenu').on('click', 'a', chooseFillClientName);
	$('#clientNameSales').on('keyup', doGetClientInfo);
	doGetClientInfo();//页面加载时,获取客户信息,填入到客户下拉表
	
	$("#newModifyModalBills").on('click','.salesOk', doSaveOrUpdate);
	//获得模态框上绑定的id值
	var id=$("#newModifyModalBills").data("id");
	//假如id有值,说明这是修改,然后根据id获得对象,初始化模态框数据
	if(id) doGetObjectById(id);
	
	//调用日期控件
	datepicker();
	
	//当模态框隐藏时在.receivingOk上绑定的事件执行解绑动作
	var count = 0;//计数器,强制模态框组件事件监听只出现一次
	$("#newModifyModalBills").on("hidden.bs.modal",function() {
		if (++count == 1) {
			$(this).off('click', '.salesOk').removeData("id").removeData("clientName");
			$('#clientNameSales').removeData('client');
		}
		
	});
});
//填充售货单表单
function doFillFormData(data){
	$("#salesSaCodeModify").val(data.sales.saleCode);
	$("#salesSaDateModify").val(data.sales.saleDate);
	$("#salesNoteModify").val(data.sales.note);
	$("#clientNameSales").val(data.client.name);
	$("#salesTotalPriceModify").data("totalPrice", data.sales.totalPrice);
	$("#salesValidModify").data("valid", data.sales.valid);
}
//根据id查询一条售货单信息
function doGetObjectById(id){
	$('#salesSaCodeModify').attr('readonly', true);//将售货单编号设置为只读
	var url="sales/doFindSalesOrClientById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
			console.log(JSON.stringify(result))
			$('#clientNameSales').data('client', result.data.client);
			//初始化表单数据
			doFillFormData(result.data);
		}else{
			alert(result.message);
		}
	});
}
//获取表单数据
function doGetEditFormData(){
	var params={
			'id':$("#newModifyModalBills").data("id"),//修改时需要
			'saleCode':$("#salesSaCodeModify").val(),
			'saleDate':$("#salesSaDateModify").val(),
			'note':$("#salesNoteModify").val(),
			'valid':$("#salesValidModify").data("valid")?$("#salesValidModify").data("valid"):1,
			'totalPrice':$("#salesTotalPriceModify").data("totalPrice")?$("#salesTotalPriceModify").data("totalPrice"):0,
			'createdUser':'花花',
			'modifiedUser':'花花'
	};
	//检测获得的结果
	return params;
}
//存储或更新售货单信息
function doSaveOrUpdate(){
	var client = $('#clientNameSales').data('client');//先获取客户信息
	if(!client){//没有选择客户,报错
		debugger
		alert('请先选择客户');
		return;
	}
	//1.获得表单数据
	var params = doGetEditFormData();
	params.clientId = client.id;//已选择客户,添加客户id
	console.log(JSON.stringify(params));
	//2.将数据提交到服务端
	var id=$("#newModifyModalBills").data("id");
	var url=id?"sales/doUpdateSales.do":"sales/doSaveSales.do";
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
//选择填充客户名
function chooseFillClientName(){
	var clientName = $(this).html();//获取客户名
	$('#clientNameSales').val(clientName);//填充到输入框
	var client = $(this).parent().data('client');//获取客户信息
	$('#clientNameSales').data('client', client);//将客户信息绑定到客户名输入框,便于存储售货单时绑定客户
}
//页面加载时,获取客户信息,填入选择产品的下拉菜单中
function doGetClientInfo(){
	var url = 'client/doFindObjectByName.do';
	var name = $('#clientNameSales').val();//获取输入的客户
	var params = {
			'name':name
	};
	$.post(url, params, function(result){
		doFillDropdownMenuData(result.data);
	});
};
//将获取的客户信息名称填入到下拉菜单
function doFillDropdownMenuData(clients){
	var ulClient = $('#clientDropdownMenu');//获取下拉菜单
	ulClient.empty();//情况
	for(var i in clients){
		var li = $('<li></li>');//创建li
		//绑定此客户数据,方便选中后操作
		li.data('client',clients[i]);
		var a = '<a href="javascript:;">' + clients[i].name + '</a>';//创建a标签
		li.append(a);//将a标签追加到li
		ulClient.append(li);//将li对象追加到下拉表
	}
}