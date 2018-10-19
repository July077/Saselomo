$(function(){
	$('#coreShow').on('click', '.receivingAddBtn, .receivingModifyBtn', showEditModal);
	$('#queryBox').on('click','.receivingSearchBtn', doGetObjects);
	$('#coreShow').on('click','.receivingDeleteBtn', showDeleteModal);
	$('#coreShow').on('click', '.receivingDetailsBtn', showDetails);
	//页面加载完成执行此方法
	//1.发起ajax请求(findObjects.do)
	//2.将返回的结果填充到content位置
	doGetObjects();
	//调用日期控件
	datepicker();
});
//显示详情页
function showDetails(){
	console.log('showDetails');
	//将要查看的id值绑定到模态框上,目的是实现查询详情
	$("#detailsModal").data("id", $(this).parent().parent().parent().parent().data("id"));
	//异步加载url
	var url='receiving/detailsUI.do?'+Math.random(1000);
	$('#context-core').load(url);
}
//显示删除模态框
function showDeleteModal(){
	$('#deleteModal .deletePromptMessage').html('确定删除此收货单信息么?');//填写内容
	$('#deleteModal').data('className', this.className);//将class值绑定到模态框
	$("#deleteModal").data("id", $(this).parent().parent().parent().parent().data("id"));
	$('#deleteModal').modal('show');
//	console.log('deleteBtn - className='+this.className);
}

//显示编辑模态框
function showEditModal(){
//	console.log('showEditModal');
	var url = 'receiving/newModifyModalUI.do';
	var title;
	if($(this).hasClass("receivingAddBtn")){
		title="新建收获单信息";
	}
	if($(this).hasClass("receivingModifyBtn")){
		title="修改收获单信息";
		//将要修改的记录的id值绑定到模态框上
		//目的时通过一个模块实现添加或更新操作
		$("#newModifyModalBills").data("id", $(this).parent().parent().parent().parent().data("id"));
	}
	//在模态框modal-body位置异步加载url
	$('#newModifyModalBills .modal-body').load(url,function(){//加载完成执行此函数
		$("#newModifyModalBills .modal-title").html(title);
		$("#newModifyModalBills").modal("show");
	});
}

//加载分页
function loadPaginationUI(pageObject){
//	console.log(JSON.stringify(pageObject));
	var current = pageObject.currentPage;//当前页
	var  total = pageObject.totalPage;//总页数
	$("#pagination").pagination({//分页
		currentPage: current,
		totalPage: total,
		callback: function(currentPage) {//回调函数
			$('#coreShow').data('currentPage', currentPage);
			doGetObjects();
		}
	});
}
//加载收货单信息
function doGetObjects(){
	//异步请求路径
	var url = 'receiving/doFindReceivings.do';
	var params = getQueryFormData();
	var currentPage = $('#coreShow').data('currentPage');
//	console.log('currentPage:'+currentPage);
	if(currentPage){//若当前页有值，加入当前页
		params.currentPage = currentPage
	};
	$.post(url,params,  function(result){
		if(result.state == 1){
			var pageObject = result.data.pageObject;
			loadPaginationUI(pageObject);
//			console.log(JSON.stringify(result));
			//设置表格tabody中的内容
			setTableRows(result.data.receivings);
			
		}else{
			alert(result.message);
		}
		
	});
}

//获取查询参数
function getQueryFormData(){
	var params = {
			"purchaseTime":$('#searchReceivingPurchaseTime').val(),
			"receiptCode":$('#searchReceivingReceiptCode').val()
	};
//	console.log("getQueryFormData");
	return params;
}
//设置表格tbody中的内容
function setTableRows(receivings){
	//获取收货单内容区对象
	var content = $('#content');
	//清空
	content.empty();
	var template = '<th scope="row">[no]</th>' +
    			   '<td>[receiptCode]</td>'		+
    			   '<td>[valid]</td>'			+
    			   '<td>[purchaseTime]</td>'			+
    			   '<td>[totalPrice]</td>'	+
				   '<td>'					+
				    	'<div class="bs-example tooltip-demo">' +
				    		'<div class="bs-example-tooltips">'	+
					          	'<button data-toggle="tooltip" data-placement="top" title="详情" class="btn receivingDetailsBtn [rowDetailsBtn]">'	+
					          		'<span class="glyphicon glyphicon-eye-open text-primary"></span>'					+
					          	'</button>'																				+
					          	'<button data-toggle="tooltip" data-placement="top" title="修改" class="btn receivingModifyBtn [rowModifyBtn]">'	+
					          		'<span class="glyphicon glyphicon-pencil" style="color: #FFD793;"></span>'			+
					          	'</button>'																				+
					          	'<button data-toggle="tooltip" data-placement="top" title="删除" class="btn receivingDeleteBtn [rowDeleteBtn]">'	+
					          		'<span class="glyphicon glyphicon-trash text-right" style="color: red;"></span>'	+
					          	'</button>'																				+
				    		'</div>'		+
				    	'</div>'			+
				    '</td>';
	//内容数量标识
	var token = 0;
	//追加新数据
	for(var i in receivings){
		//记录数
		token++;
		//创建tr对象
		var tr;
		//按钮的class属性
		var btnClassName;
		if(i%2==0){
			tr = $('<tr class="rowOne"></tr>');
			btnClassName = 'rowOneBtn';
		}else{
			tr = $('<tr class="rowTwo"></tr>');
			btnClassName = 'rowTwoBtn';
		}
		//判定状态,1为新建,2为部分收货,3为全部收货
		var valid = receivings[i].valid;
//		console.log('valid='+valid);
		var validShow;//页面展示状态
		if(valid == 1){
			validShow = '新建';
		}else if(valid == 2){
			validShow = '部分收货';
		}else if(valid == 3){
			validShow = '全部收货';
		}else if(valid == null){
			validShow = '状态未明';
			alert('收货出现状态未明,请联系系统管理员');
		}
		 
		//绑定id数据,便于后续获得id属性进行修改操作
		tr.data('id', receivings[i].id);
		//将td模板追加到tr
		tr.append(template
				.replace('[no]', token)
				.replace('[receiptCode]', receivings[i].receiptCode)
				.replace('[valid]', validShow)
				.replace('[purchaseTime]', receivings[i].purchaseTime?receivings[i].purchaseTime:'')
				.replace('[totalPrice]', receivings[i].totalPrice)
				.replace('[rowDetailsBtn]', btnClassName)
				.replace('[rowModifyBtn]', btnClassName)
				.replace('[rowDeleteBtn]', btnClassName));
		//将tr对象追加到内容区
		content.append(tr);
	}
	//启动工具提示
	$('[data-toggle="tooltip"]').tooltip();
};