$(function(){
	$('#queryBox').on('click','.inventorySearchBtn', doGetObjects);
	//页面加载完成执行此方法
	//1.发起ajax请求(findObjects.do)
	//2.将返回的结果填充到content位置
	 doGetObjects();
});
//导出库存信息
function exportInventory(){
	location.href = 'inventory/exportInventory.do';
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
//加载库存信息
function doGetObjects(){
	//异步请求路径
	var url = 'inventory/doFindInventorys.do';
	var params = getQueryFormData();//获取查询参数
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
			setTableRows(result.data.inventorys);
			
		}else{
			alert(result.message);
		}
		
	});
}
//获取查询参数
function getQueryFormData(){
	var params = {
			"proName":$('#searchProNameInven').val()
	};
//	console.log("getQueryFormData");
	return params;
}
//设置表格tbody中的内容
function setTableRows(inventorys){
//	console.log(inventorys);
	//获取客户内容区对象
	var content = $('#content');
	//清空
	content.empty();
	var template = '<th scope="row">[no]</th>' +
    			   '<td>[proName]</td>'		+
    			   '<td>[inventoryCount]</td>'			+
    			   '<td>[inventoryAvailable]</td>'			+
    			   '<td>[inventoryOrderForm]</td>'			+
    			   '<td>[inventoryFreeze]</td>'	+
				   '<td>'					+
				    	'<div class="bs-example tooltip-demo">' +
				    		'<div class="bs-example-tooltips">'	+
					          	'<button data-toggle="tooltip" data-placement="top" title="库存冻结" class="btn inventoryFreezeBtn [rowDetailsBtn]">'	+
					          		'<span class="glyphicon glyphicon-record text-warning"></span>'					+
					          	'</button>'																				+
				    		'</div>'		+
				    	'</div>'			+
				    '</td>';
	//内容数量标识
	var token = 0;
	//追加新数据
	for(var i in inventorys){
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
		tr.data('id', inventorys[i].id);
		//将td模板追加到tr
		tr.append(template
				.replace('[no]', token)
				.replace('[proName]', inventorys[i].name)
				.replace('[inventoryCount]', inventorys[i].inventoryCount)
				.replace('[inventoryAvailable]', inventorys[i].inventoryAvailable)
				.replace('[inventoryOrderForm]', inventorys[i].inventoryOrderForm)
				.replace('[inventoryFreeze]', inventorys[i].inventoryFreeze)
				.replace('[rowDetailsBtn]', btnClassName)
				.replace('[rowModifyBtn]', btnClassName)
				.replace('[rowDeleteBtn]', btnClassName));
		//将tr对象追加到内容区
		content.append(tr);
	}
	//启动工具提示
	$('[data-toggle="tooltip"]').tooltip();
};