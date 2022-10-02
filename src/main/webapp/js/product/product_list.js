$(function(){
	$('#coreShow').on('click', '.productAddBtn, .productModifyBtn', showEditModal);
	$('#coreShow').on('click', '.productDetailsBtn', showDetailsModal);
	$('#coreShow').on('click','.productDeleteBtn', showDeleteModal);
	$('#queryBox').on('click','.productSearchBtn', doGetObjects);
	$('#queryBox').on('click','.productUpload', showImportModal);
	//页面加载完成执行此方法
	//1.发起ajax请求(findObjects.do)
	//2.将返回的结果填充到content位置
	  doGetObjects();
});
//显示导入模态框
function showImportModal(){
	var url = 'product/importUI.do';
	//在模态框modal-body位置异步加载url
	$('#importModal .modal-content').load(url,function(){//加载完成执行此函数
		$("#importModal").modal({
			backdrop: false,//点击空白处不关闭对话框
			show: true
		});
	});
}
//导出产品数据信息
function exportProduct(){
	//这里不能用ajax请求，ajax请求无法弹出下载保存对话框
	location.href="product/exportProduct.do";
}
//显示删除模态框
function showDeleteModal(){
	$('#deleteModal .deletePromptMessage').html('确定删除此产品信息么?');//填写内容
	$('#deleteModal').data('className', this.className);//将class值绑定到模态框
//	console.log(this.className);
	$("#deleteModal").data("id", $(this).parent().parent().parent().parent().data("id"));
//	console.log($(this).parent().parent().parent().parent().data("id"));
	$('#deleteModal').modal('show');
//	console.log('deleteBtn - className='+this.className);
}
//显示详情模态框
function showDetailsModal(){
//	console.log('showDetailsModal');
	var url = 'product/detailsModalUI.do';
	//获取产品简称
	var abbreviation = $(this).parent().parent().parent().prevAll(".productAbbreviation").html();
	//获取产品全称
	var name = $(this).parent().parent().parent().prevAll(".productName").html();
	var title = "产品详情 - " + (abbreviation?abbreviation:name);
	//将要查看的id值绑定到模态框上,目的是实现查询详情
	$("#detailsModal").data("id", $(this).parent().parent().parent().parent().data("id"));
	//在模态框modal-body位置异步加载url
	$('#detailsModal .modal-body').load(url,function(){//加载完成执行此函数
		$("#detailsModal .modal-title").html(title);
		$("#detailsModal").modal("show");
	});
}
//显示编辑模态框
function showEditModal(){
//	console.log('showEditModal');
	var url = 'product/newModifyModalUI.do';
	var title;
	if($(this).hasClass("productAddBtn")){
		title="新建产品信息";
	}
	if($(this).hasClass("productModifyBtn")){
		title="修改产品信息";
		//将要修改的记录的id值绑定到模态框上
		//目的时通过一个模块实现添加或更新操作
		$("#newModifyModal").data("id", $(this).parent().parent().parent().parent().data("id"));
	}
	//在模态框modal-body位置异步加载url
	$('#newModifyModal .modal-body').load(url,function(){//加载完成执行此函数
		$("#newModifyModal .modal-title").html(title);
		$("#newModifyModal").modal("show");
	});
	
}
//加载分页
function loadPaginationUI(pageObject){
//	console.log('pageObject:'+JSON.stringify(pageObject));
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
//加载产品信息
function doGetObjects(){
	//异步请求路径
	var url = 'product/doFindProducts.do';
	var params = getQueryFormData();
	var currentPage = $('#coreShow').data('currentPage');
//	console.log('currentPage:'+currentPage);
	if(currentPage){//若当前页有值，加入当前页
		params.currentPage = currentPage;
	};
	$.post(url,params,  function(result){
		if(result.state == 1){
			var pageObject = result.data.pageObject;
			loadPaginationUI(pageObject);
//			console.log('result:'+JSON.stringify(result));
			//设置表格tabody中的内容
			setTableRows(result.data.products);
			
		}else{
			alert(result.message);
		}
		
	});
}

//获取查询参数
function getQueryFormData(){
	var params = {
			"name":$('#searchProductName').val(),
			"abbreviation":$('#searchProductAbbreviation').val(),
			"sellingPoints":$('#searchProductSellingPoints').val(),
			"effect":$('#searchProductEffect').val()
	};
//	console.log("getQueryFormData");
	return params;
}
//设置表格tbody中的内容
function setTableRows(products){
//	console.log(products);
	//获取产品内容区对象
	var content = $('#content'); 
	//清空
	content.empty();
	var template = '<th scope="row">[no]</th>' +
				   '<td class="productAbbreviation">[abbreviation]</td>'		+
    			   '<td class="productName">[name]</td>'		+
    			   '<td>[effect]</td>'			+
    			   '<td>[cartonSize]</td>'			+
				   '<td>'					+
				    	'<div class="bs-example tooltip-demo">' +
				    		'<div class="bs-example-tooltips">'	+
					          	'<button data-toggle="tooltip" data-placement="top" title="详情" class="btn productDetailsBtn [rowDetailsBtn]">'	+
					          		'<span class="glyphicon glyphicon-eye-open text-primary"></span>'					+
					          	'</button>'																				+
					          	'<button data-toggle="tooltip" data-placement="top" title="修改" class="btn productModifyBtn [rowModifyBtn]">'	+
					          		'<span class="glyphicon glyphicon-pencil" style="color: #FFD793;"></span>'			+
					          	'</button>'																				+
					          	'<button data-toggle="tooltip" data-placement="top" title="删除" class="btn productDeleteBtn [rowDeleteBtn]">'	+
					          		'<span class="glyphicon glyphicon-trash text-right" style="color: red;"></span>'	+
					          	'</button>'																				+
				    		'</div>'		+
				    	'</div>'			+
				    '</td>';
	//内容数量标识
	var token = 0;
	//追加新数据
	for(var i in products){
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
		tr.data('id', products[i].id);
		//将td模板追加到tr
		tr.append(template
				.replace('[no]', token)
				.replace('[abbreviation]', products[i].abbreviation)
				.replace('[name]', products[i].name)
				.replace('[effect]', products[i].effect)
				.replace('[cartonSize]', products[i].cartonSize?products[i].cartonSize:'')
				.replace('[rowDetailsBtn]', btnClassName)
				.replace('[rowModifyBtn]', btnClassName)
				.replace('[rowDeleteBtn]', btnClassName));
		//将tr对象追加到内容区
		content.append(tr);
	}
	//启动工具提示
	$('[data-toggle="tooltip"]').tooltip();
};
