$(function() {
	$('#coreShow').on('click', '.userAddBtn, .userModifyBtn', showEditModal);
	$('#coreShow').on('click','.userDeleteBtn', showDeleteModal);
	$('#coreShow').on('click','.userSearchBtn', doGetObjects);
	doGetObjects();//发起ajax请求,将服务器传入的数据显示在页面
});
//显示删除模态框
function showDeleteModal(){
	$('#deleteModal .deletePromptMessage').html('确定删除此用户信息么?');//填写内容
	$('#deleteModal').data('className', this.className);//将class值绑定到模态框
	$("#deleteModal").data("id", $(this).parent().parent().parent().parent().data("id"));
	$('#deleteModal').modal({
		backdrop: false,//点击空白处不关闭对话框
		show: true
	});
}
//显示编辑模态框
function showEditModal(){
//	console.log('showEditModal');
	var url = 'user/newModifyModalUI.do';
	var title;
	if($(this).hasClass("userAddBtn")){
		title="新建用户信息";
	}
	if($(this).hasClass("userModifyBtn")){
		title="修改用户信息";
		//将要修改的记录的id值绑定到模态框上
		//目的时通过一个模块实现添加或更新操作 
		$("#newModifyModalBills").data("id", $(this).parent().parent().parent().parent().data("id"));
	}
	//在模态框modal-body位置异步加载url
	$('#newModifyModalBills .modal-body').load(url,function(){//加载完成执行此函数
		$("#newModifyModalBills .modal-title").html(title);
		$("#newModifyModalBills").modal({
//			backdrop: false,//点击空白处不关闭对话框
			show: true
		});
	});
}
//加载用户信息
function doGetObjects(){
	//异步请求路径
	var url = 'user/doFindUsers.do';
	var params = getQueryFormData();
	var currentPage = $('#coreShow').data('currentPage');
//	console.log('currentPage:'+currentPage);
	if(currentPage){//若当前页有值，加入当前页
		params.currentPage = currentPage
	};
	$.post(url,params, function(result){
		if(result.state == 1){
			var pageObject = result.data.pageObject;
			loadPaginationUI(pageObject);
//			console.log(JSON.stringify(result));
			//设置表格tabody中的内容
			setTableRows(result.data.users);
			
		}else{
			alert(result.message);
		}
		
	});
}
//获取查询参数
function getQueryFormData(){
	var params = {
			"name":$('#searchUserName').val(),
			"username":$('#searchUserUName').val(),
			"mobile":$('#searchUserMobile').val()
	};
//	console.log("getQueryFormData");
	return params;
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
//设置表格tbody中的内容
function setTableRows(users){
//	console.log(clients);
	//获取用户内容区对象
	var content = $('#content');
	//清空
	content.empty();
	var template = '<th scope="row">[no]</th>' 	+
    			   '<td>[name]</td>'			+
    			   '<td>[username]</td>'		+
    			   '<td>[mobile]</td>'			+
    			   '<td>[email]</td>'			+
    			   '<td>[valid]</td>'			+
    			   '<td>[createdTime]</td>'			+
				   '<td>'						+
				    	'<div class="bs-example tooltip-demo">' +
				    		'<div class="bs-example-tooltips">'	+
					          	'<button data-toggle="tooltip" data-placement="top" title="详情" class="btn userDetailsBtn [rowDetailsBtn]">'	+
					          		'<span class="glyphicon glyphicon-eye-open text-primary"></span>'					+
					          	'</button>'																				+
					          	'<button data-toggle="tooltip" data-placement="top" title="修改" class="btn userModifyBtn [rowModifyBtn]">'	+
					          		'<span class="glyphicon glyphicon-pencil" style="color: #FFD793;"></span>'			+
					          	'</button>'																				+
					          	'<button data-toggle="tooltip" data-placement="top" title="删除" class="btn userDeleteBtn [rowDeleteBtn]">'	+
					          		'<span class="glyphicon glyphicon-trash text-right" style="color: red;"></span>'	+
					          	'</button>'																				+
				    		'</div>'		+
				    	'</div>'			+
				    '</td>';
	//内容数量标识
	var token = 0;
	//追加新数据
	for(var i in users){
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
		 
		//绑定id数据,便于后续获得id属性进行修改操作
		tr.data('id', users[i].id);
		//将td模板追加到tr
		tr.append(template
				.replace('[no]', token)
				.replace('[name]', users[i].name)
				.replace('[username]', users[i].username)
				.replace('[mobile]', users[i].mobile)
				.replace('[email]', users[i].email)
				.replace('[createdTime]', users[i].createdTime)
				.replace('[valid]', users[i].valid)
				.replace('[rowDetailsBtn]', btnClassName)
				.replace('[rowModifyBtn]', btnClassName)
				.replace('[rowDeleteBtn]', btnClassName));
		//将tr对象追加到内容区
		content.append(tr);
	}
	//启动工具提示
	$('[data-toggle="tooltip"]').tooltip();
};