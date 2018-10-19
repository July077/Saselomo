$(function(){
	//获得模态框上绑定的id值
	var id=$("#detailsModal").data("id");
	doGetObjectById(id);
	//页面加载完成执行此方法
	//1.发起ajax请求(findObjects.do)
	//2.将返回的结果填充到content位置
	doGetReceivingSingle(id);
	
	$('#coreShow').on('click', '.recSingleAddBtn, .recSingleModifyBtn', showEditRecSingleModal);//新建修改
	$('#coreShow').on('click','.recSingleDeleteBtn', showDeleteModal);//删除
	$('#coreShow').on('click', '.recSingleSearchBtn', function(){
		var id=$("#detailsModal").data("id");
		doGetReceivingSingle(id);
	});//查询
	
	//点击返回按钮
	$('#coreShow').on('click', '.recDetailsReturn', function(){
		var url="receiving/listUI.do?t="+Math.random(1000);//返回收货单页面
		$('#context-core').load(url);
		$("#detailsModal").removeData("id").removeData("currentPage");//删除当前页,和id值
	});
	
	// 点击复选框的全选,选择全部复选框
	$("#checkedAll").click(function() {
        $(":checkbox[name='checkedSingle']").prop("checked", this.checked); // this指代的你当前选择的这个元素的JS对象
    });
	//确认收货,打开模态框
	$('#coreShow').on('click', '.recConfirmBtn', confirmRecModal);
	//设置收货模态框中确认按钮事件,提交选中的收货项
	$('#confirmReceiving').on('click', '.confirmRecOk', confirmRecSingle);
	//设置收货模态框,取消按钮事件
	$('#confirmReceiving').on('click', '.cancelBtn', function(){
		$('#confirmReceiving').modal('hide');
	});
});
//确认收货选中子项
function confirmRecSingle(){
	var recId=$("#detailsModal").data("id");
	var params = {
			'recSinIds':getCheckedRecSinIds(),
			'recId': recId
	};
	console.log(JSON.stringify(params));
	var url = 'receivingSingle/doConfirmRecSingle.do';
	$.post(url,params,function(result){
		if(result.state==1){//1表示成功了
			$('#confirmReceiving').modal('hide');
			var id=$("#detailsModal").data("id");
			doGetReceivingSingle(id);//重新查询收货单子项列表
			doGetObjectById(id);//收货单详情
		}else{
			$('#confirmReceiving').modal('hide');
			alert(result.message);//显示错误信息
		}
	});
}
//获取选中项的收货单子项id
function getCheckedRecSinIds(){
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
//确认收货
function confirmRecModal(){
	let cheSingles = $('.checkboxSingle input[name="checkedSingle"]:checked');//选择的全部子项
	if(!cheSingles.length){//为空,没有进行选择
		$('#alertInfo').html('请至少选择一个子项');
		$('#alertBox').slideDown(300);
		return;
	}
//	已选择,弹出模态框
	$('#confirmReceiving .confirmPromptMessage').html('确定收货选中子项么?');//填写内容
	$("#confirmReceiving").data("id", $(this).parent().parent().parent().parent().data("id"));
	$('#confirmReceiving').modal('show');
}
//显示删除模态框
function showDeleteModal(){
	$('#deleteModal .deletePromptMessage').html('确定删除此收货单子项么?');//填写内容
	$('#deleteModal').data('className', this.className);//将class值绑定到模态框
	$("#deleteModal").data("id", $(this).parent().parent().parent().parent().data("id"));
	$("#deleteModal").data("receivingId", $(this).parent().parent().parent().parent().data("receivingId"));
	$('#deleteModal').modal('show');
//	console.log('deleteBtn - className='+this.className);
}
//显示编辑模态框
function showEditRecSingleModal(){
//	console.log('showEditModal');
	var url = 'receivingSingle/newModifyModalUI.do';
	var title;
	if($(this).hasClass("recSingleAddBtn")){
		title="新建收货单子项";
	}
	if($(this).hasClass("recSingleModifyBtn")){
		title="修改收货单子项";
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
function loadPaginationReceivingSingleUI(pageObject, parentId){
//	console.log(JSON.stringify(pageObject));
	var current = pageObject.currentPage;//当前页
	var  total = pageObject.totalPage;//总页数
	$("#paginationReceivingSingle").pagination({//分页
		currentPage: current,
		totalPage: total,
		callback: function(currentPage) {//回调函数
			$('#detailsModal').data('currentPage', currentPage);
			doGetReceivingSingle(parentId);
		}
	});
}
//加载收货单子项信息
function doGetReceivingSingle(parentId){
//	console.log('doGetObjects(parentId)'+parentId);
	//异步请求路径
	var url = 'receivingSingle/doFindReceivingSingles.do';
	var params = {
			'parentId':parentId,//加入id信息
			'productName': $('#searchRecProductNameId').val()
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
			loadPaginationReceivingSingleUI(pageObject, parentId);
//			console.log(JSON.stringify(result));
			//设置表格tabody中的内容
			setTableRowsReceivingSingle(result.data.receivingSingles);
		}else{
			alert(result.message);
		}
		
	});
}
//设置收货单子项表格信息
//设置表格tbody中的内容
function setTableRowsReceivingSingle(receivingSingles){
//	console.log(JSON.stringify(receivingSingles));
	//获取客户内容区对象
	var content = $('#contentReceivingSingles');
	//清空
	content.empty();
	var template = '<td class="checkboxSingle"><label><input [disBox] type="checkbox" name="[checkBoxName]" value="[id]"></label></td>' +
    			   '<th scope="row">[no]</th>'		+
    			   '<td>[receiptCode]</td>'			+
    			   '<td>[name]</td>'				+
    			   '<td>[valid]</td>'				+
    			   '<td>[purchasePrice]</td>'		+
    			   '<td>[count]</td>'				+
    			   '<td class="productIdSave" style="display: none;"></td>'				+
				   '<td>'							+
				    	'<div class="bs-example tooltip-demo">' +
				    		'<div class="bs-example-tooltips">'	+
					          	'<button [disModify] data-toggle="tooltip" data-placement="top" title="修改" class="btn recSingleModifyBtn [rowModifyBtn]">'	+
					          		'<span class="glyphicon glyphicon-pencil" style="color: #FFD793;"></span>'			+
					          	'</button>'																				+
					          	'<button [disDel] data-toggle="tooltip" data-placement="top" title="删除" class="btn recSingleDeleteBtn [rowDeleteBtn]">'	+
					          		'<span class="glyphicon glyphicon-trash text-right" style="color: red;"></span>'	+
					          	'</button>'																				+
//					          	'<button disabled="disabled"  data-toggle="tooltip" data-placement="top" title="确认收货" class="recSingleConfirmBtn [rowDetailsBtn]">'	+
//					          	'<span class="glyphicon glyphicon-ok text-success"></span>'								+
//					          	'</button>'																				+
				    		'</div>'		+
				    	'</div>'			+
				    '</td>';
	//内容数量标识
	var token = 0;
	//追加新数据
	for(var i in receivingSingles){
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
		if(receivingSingles[i].valid == 1){
			validShow = '新建';
			btnDisabled = '';
			checkBoxName = 'checkedSingle';
		}else if(receivingSingles[i].valid == 2){
			validShow = '部分收货';
			btnDisabled = 'disabled';
			checkBoxName = '';
		}else if(receivingSingles[i].valid == 3){
			validShow = '全部收货';
			btnDisabled = 'disabled';
			checkBoxName = '';
		}else if(receivingSingles[i].valid == null){
			validShow = '状态未明';
			alert('收货出现状态未明,请联系系统管理员');
		}
		//绑定id数据,便于后续获得id属性进行修改操作
		tr.data('id', receivingSingles[i].id);
		//绑定receivingId数据,便于后续获得此属性进行删除后价格变动
		tr.data('receivingId', receivingSingles[i].receivingId);
		//将td模板追加到tr
		tr.append(template
				.replace('[id]', receivingSingles[i].id)
				.replace('[no]', token)
				.replace('[receiptCode]', receivingSingles[i].receiptCode)
				.replace('[name]', receivingSingles[i].name)
				.replace('[valid]', validShow)
				.replace('[count]', receivingSingles[i].count)
				.replace('[purchasePrice]', receivingSingles[i].purchasePrice)
				.replace('[productId]', receivingSingles[i].proId)
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
//根据id查找receiving对象
function doGetObjectById(id){
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
//	console.log("doFillFormData");
//	console.log(JSON.stringify(obj));
	var validShow;//页面显示状态
	if(obj.valid == 1){
		validShow = '新建';
	}else if(obj.valid == 2){
		validShow = '部分收货';
	}else if(obj.valid == 3){
		validShow = '全部收货';
	}else if(obj.valid == null){
		validShow = '状态未明';
		alert('收货出现状态未明,请联系系统管理员');
	}
	$("#receivingReceiptCodeDetails").html(obj.receiptCode);
	$("#receivingValidDetails").html(validShow);
	$("#receivingPurchaseTimeDetails").html(obj.purchaseTime);
	$('#receivingNoteDetails').html(obj.note);
	$('#receivingTotalPriceDetails').html(obj.totalPrice);
}
