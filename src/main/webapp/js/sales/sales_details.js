$(function(){
	//获得模态框上绑定的id值
	var id=$("#detailsModal").data("id");
	doGetObjectById(id);
	//页面加载完成执行此方法
	//1.发起ajax请求(findObjects.do)
	//2.将返回的结果填充到content位置
	doGetSalesSingle(id);
	
	$('#coreShow').on('click', '.salSingleAddBtn, .salSingleModifyBtn', showEditSalSingleModal);//新建修改
	$('#coreShow').on('click','.salSingleDeleteBtn', showDeleteModal);//删除
	$('#coreShow').on('click', '.salSingleSearchBtn', function(){
		var id=$("#detailsModal").data("id");
		doGetSalesSingle(id);
	});//查询
	
	//点击返回按钮
	$('#coreShow').on('click', '.salDetailsReturn', function(){
		var url="sales/listUI.do?t="+Math.random(1000);//返回售货单页面
		$('#context-core').load(url);
		$("#detailsModal").removeData("id").removeData("currentPage");//删除当前页,和id值
	});
	
	// 点击复选框的全选,选择全部复选框
	$("#checkedAll").click(function() {
        $(":checkbox[name='checkedSingle']").prop("checked", this.checked); // this指代的你当前选择的这个元素的JS对象
    });
	//确认售货,打开模态框
	$('#coreShow').on('click', '.salConfirmBtn', confirmSalModal);
	//设置售货模态框中确认按钮事件,提交选中的售货项
	$('#confirmSales').on('click', '.confirmSalOk', confirmSalSingle);
	//设置售货模态框,取消按钮事件
	$('#confirmSales').on('click', '.cancelBtn', function(){
		$('#confirmSales').modal('hide');
	});
});
//确认售货选中子项
function confirmSalSingle(){
	var salId=$("#detailsModal").data("id");
	var params = {
			'salSinIds':getCheckedSalSinIds(),
			'salId': salId
	};
//	console.log(JSON.stringify(params));
	var url = 'salesSingle/doConfirmSalSingle.do';
	$.post(url,params,function(result){
		if(result.state==1){//1表示成功了
			$('#confirmSales').modal('hide');
			var id=$("#detailsModal").data("id");
			doGetSalesSingle(id);//重新查询售货单子项列表
			doGetObjectById(id);//售货单详情
		}else{
			$('#confirmSales').modal('hide');
			alert(result.message);//显示错误信息
		}
	});
}
//获取选中项的售货单子项id
function getCheckedSalSinIds(){
	var checkedIds = '';//选中的id
	$('.checkboxSingle input[name="checkedSingle"]').each(function(){
		//判定这个input对象是否是选中的input
		if($(this).is(":checked")){
			//将选中的checkbox的值拼接为字符串
			if(checkedIds==''){
				checkedIds+=$(this).val();
			}else{
				checkedIds+=","+$(this).val();
			}//"1,2,3,4,5";
		};
	});
	return checkedIds;
}
//确认售货
function confirmSalModal(){
	let cheSingles = $('.checkboxSingle input[name="checkedSingle"]:checked');//选择的全部子项
	if(!cheSingles.length){//为空,没有进行选择
		$('#alertInfo').html('请至少选择一个子项');
		$('#alertBox').slideDown(300);
		return;
	}
//	已选择,弹出模态框
	$('#confirmSales .confirmPromptMessage').html('确定已售货选中子项么?');//填写内容
	$("#confirmSales").data("id", $(this).parent().parent().parent().parent().data("id"));
	$('#confirmSales').modal('show');
}
//显示删除模态框
function showDeleteModal(){
	$('#deleteModal .deletePromptMessage').html('确定删除此售货单子项么?');//填写内容
	$('#deleteModal').data('className', this.className);//将class值绑定到模态框
	$("#deleteModal").data("id", $(this).parent().parent().parent().parent().data("id"));
	$("#deleteModal").data("salesId", $(this).parent().parent().parent().parent().data("salesId"));
	$('#deleteModal').modal('show');
//	console.log('deleteBtn - className='+this.className);
}
//显示编辑模态框
function showEditSalSingleModal(){
	console.log('showEditModal');
	var url = 'salesSingle/newModifyModalUI.do';
	var title;
	if($(this).hasClass("salSingleAddBtn")){
		title="新建售货单子项";
	}
	if($(this).hasClass("salSingleModifyBtn")){
		title="修改售货单子项";
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
function loadPaginationSalesSingleUI(pageObject, parentId){
//	console.log(JSON.stringify(pageObject));
	var current = pageObject.currentPage;//当前页
	var  total = pageObject.totalPage;//总页数
	$("#paginationSalesSingle").pagination({//分页
		currentPage: current,
		totalPage: total,
		callback: function(currentPage) {//回调函数
			$('#detailsModal').data('currentPage', currentPage);
			doGetSalesSingle(parentId);
		}
	});
}
//加载售货单子项信息
function doGetSalesSingle(parentId){
//	console.log('doGetObjects(parentId)'+parentId);
	//异步请求路径
	var url = 'salesSingle/doFindSalesSingles.do';
	var params = {
			'parentId':parentId,//加入id信息
			'productName': $('#searchSalProductNameId').val()
	};
	var currentPage = $('#detailsModal').data('currentPage');//获取分页信息
//	console.log('currentPage:'+currentPage);
	if(currentPage){//若当前页有值，加入当前页
		params.currentPage = currentPage
	};
//	console.log('params='+JSON.stringify(params));
	$.post(url,params,  function(result){
		if(result.state == 1){
			var pageObject = result.data.pageObject;
			loadPaginationSalesSingleUI(pageObject, parentId);
//			console.log(JSON.stringify(result));
			//设置表格tabody中的内容
			setTableRowsSalesSingle(result.data.salesSingles);
		}else{
			alert(result.message);
		}
		
	});
}
//设置售货单子项表格信息
//设置表格tbody中的内容
function setTableRowsSalesSingle(salesSingles){
//	console.log(JSON.stringify(salesSingles));
	//获取客户内容区对象
	var content = $('#contentSalesSingles');
	//清空
	content.empty();
	var template = '<td class="checkboxSingle"><label><input [disBox] type="checkbox" name="[checkBoxName]" value="[id]"></label></td>' +
    			   '<th scope="row">[no]</th>'		+
    			   '<td>[saleCode]</td>'			+
    			   '<td>[name]</td>'				+
    			   '<td>[valid]</td>'				+
    			   '<td>[salePrice]</td>'		+
    			   '<td>[count]</td>'				+
    			   '<td class="productIdSave" style="display: none;"></td>'				+
				   '<td>'							+
				    	'<div class="bs-example tooltip-demo">' +
				    		'<div class="bs-example-tooltips">'	+
					          	'<button [disModify] data-toggle="tooltip" data-placement="top" title="修改" class="btn salSingleModifyBtn [rowModifyBtn]">'	+
					          		'<span class="glyphicon glyphicon-pencil" style="color: #FFD793;"></span>'			+
					          	'</button>'																				+
					          	'<button [disDel] data-toggle="tooltip" data-placement="top" title="删除" class="btn salSingleDeleteBtn [rowDeleteBtn]">'	+
					          		'<span class="glyphicon glyphicon-trash text-right" style="color: red;"></span>'	+
					          	'</button>'																				+
//					          	'<button disabled="disabled"  data-toggle="tooltip" data-placement="top" title="确认售货" class="btn salSingleConfirmBtn [rowDetailsBtn]">'	+
//					          	'<span class="glyphicon glyphicon-ok text-success"></span>'								+
//					          	'</button>'																				+
				    		'</div>'		+
				    	'</div>'			+
				    '</td>';
	//内容数量标识
	var token = 0;
	//追加新数据
	for(var i in salesSingles){
		//记录数
		token++;
		//创建tr对象
		var tr;
		//按钮的class属性
		var btnClassName;
		//复选框name名字
		var checkBoxName;
		if(i%2==0){
			tr = $('<tr class="rowOne"></tr>');
			btnClassName = 'rowOneBtn';
		}else{
			tr = $('<tr class="rowTwo"></tr>');
			btnClassName = 'rowTwoBtn';
		}
		let	btnDisabled;//按钮是否禁用
		let validShow;//页面显示状态
		if(salesSingles[i].valid == 1){
			validShow = '新建';
			btnDisabled = '';
			checkBoxName = 'checkedSingle';
		}else if(salesSingles[i].valid == 2){
			validShow = '部分出售';
			btnDisabled = 'disabled';
			checkBoxName = '';
		}else if(salesSingles[i].valid == 3){
			validShow = '全部出售';
			btnDisabled = 'disabled';
			checkBoxName = '';
		}else if(salesSingles[i].valid == null){
			validShow = '状态未明';
			alert('售货出现状态未明,请联系系统管理员');
		}
		//绑定id数据,便于后续获得id属性进行修改操作
		tr.data('id', salesSingles[i].id);
		//绑定salesId数据,便于后续获得此属性进行删除后价格变动
		tr.data('salesId', salesSingles[i].salesId);
		//将td模板追加到tr
		tr.append(template
				.replace('[id]', salesSingles[i].id)
				.replace('[no]', token)
				.replace('[saleCode]', salesSingles[i].saleCode)
				.replace('[name]', salesSingles[i].name)
				.replace('[valid]', validShow)
				.replace('[count]', salesSingles[i].count)
				.replace('[salePrice]', salesSingles[i].salePrice)
				.replace('[productId]', salesSingles[i].proId)
				.replace('[rowDetailsBtn]', btnClassName)
				.replace('[rowModifyBtn]', btnClassName)
				.replace('[rowDeleteBtn]', btnClassName)
				.replace('[checkBoxName]', checkBoxName)
				.replace('[disBox]', btnDisabled)
				.replace('[disModify]', btnDisabled)
				.replace('[disDel]', btnDisabled));
		//将tr对象追加到内容区
		content.append(tr);
	}
	//若复选框子项选择完毕,全选为选中状态
	$('.checkboxSingle input[name="checkedSingle"]').click(function(){
    	var boo = checkboxSingleIsAll();
//    	console.log(boo)
    	$("#checkedAll").prop("checked", boo);
    });
	//启动工具提示
	$('[data-toggle="tooltip"]').tooltip();
};
//判断复选框子项是否已经全部选择完毕.(选择完毕返回true,反之false)
function checkboxSingleIsAll(){
	let singles = $('.checkboxSingle input[name="checkedSingle"]');//全部子项
//	console.log(singles.length);
	let cheSingles = $('.checkboxSingle input[name="checkedSingle"]:checked');//选择的全部子项
//	console.log(cheSingles.length);
	if (cheSingles.length == singles.length) {//相等,为全选,返回true
		return true;
	}
	return false;//反之false
}
//根据id查找sales对象
function doGetObjectById(id){
	var url="sales/doFindSalesById.do";
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
	var validShow;//页面显示状态
	if(obj.valid == 1){
		validShow = '新建';
	}else if(obj.valid == 2){
		validShow = '部分出售';
	}else if(obj.valid == 3){
		validShow = '全部出售';
	}else if(obj.valid == null){
		validShow = '状态未明';
		alert('售货出现状态未明,请联系系统管理员');
	}
	$("#salesSalCodeDetails").html(obj.saleCode);
	$("#salesValidDetails").html(validShow);
	$("#salesSalDateDetails").html(obj.saleDate);
	$('#salesNoteDetails').html(obj.note);
	$('#salesTotalPriceDetails').html(obj.totalPrice);
}
