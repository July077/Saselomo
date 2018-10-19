$(function(){
	//获得模态框上绑定的id值
	var id=$("#detailsModal").data("id");
	//查询对应客户信息
	doGetObjectByModify(id);
	//查询客户购买记录
	goGetObjectsPurHistory(id);
	//产品全简显示
	$('#proNameShow').on('click', 'a', chooseProNameShow);
	//查询按钮
	$('#queryBox').on('click', '.cliPurHisSearchBtn', function(){
		//获得模态框上绑定的id值
		let id=$("#detailsModal").data("id");
		goGetObjectsPurHistory(id);
	});
	//调用时间控件
	datepicker();
	//点击返回按钮
	$('#coreShow').on('click', '.cliDetailsReturn', function(){
		var url="client/listUI.do?t="+Math.random(1000);//返回收货单页面
		$('#context-core').load(url);
		$("#detailsModal").removeData("id");//删除当前页,和id值
	});
});
//选择产品全简显示
function chooseProNameShow(){
	let simplOrFull = $(this).html();
	console.log(simplOrFull);
	if(simplOrFull == '全称'){//全称
		$('#proNameShow').data('simplOrFull', 'full');
	}else if(simplOrFull == "简称"){//简称
		$('#proNameShow').data('simplOrFull', '');//默认简称显示,默认为空
	}
	let clientPurHistory = $('#proNameShow').data('clientPurHistory');
	setTableRows(clientPurHistory);//重新填充表格
}
//根据id查找client对象
function doGetObjectByModify(id){
	var url="client/doFindClientById.do";
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
	$("#clientNameDetails").html(obj.name);
	$("#clientSexDetails").html(obj.sex);
	$("#clientAgeDetails").html(obj.age);
	$("#clientAddressDetails").html(obj.address);
	$('#clientPhoneDetails').html(obj.phone);
	$('#clientSkinConditionDetails').html(obj.skinCondition);
	$('#clientTimetableDetails').html(obj.timetable);
	$('#clientNoteDetails').html(obj.note);
}
//获取查询参数
function getQueryFormData(){
	let params = {
		'purchaseDate': $('#searchPurDateId').val()
	};
	return params;
}
//加载分页
function loadPaginationUI(pageObject){
	var current = pageObject.currentPage;//当前页
	var  total = pageObject.totalPage;//总页数
	$("#pagination").pagination({//分页
		currentPage: current,
		totalPage: total,
		callback: function(currentPage) {//回调函数
			$('#coreShow').data('currentPage', currentPage);
			//获得模态框上绑定的id值
			var id=$("#detailsModal").data("id");
			//查询客户购买记录
			goGetObjectsPurHistory(id);
		}
	});
}
//查询客户购买记录
function goGetObjectsPurHistory(id){
	//异步请求路径
	var url = 'clientPurHistory/doFindClientPurcHistorys.do';
	var params = getQueryFormData();
	//加入客户id
	params.clientId = id;
	//获取分页
	var currentPage = $('#coreShow').data('currentPage');
//	console.log('currentPage:'+currentPage);
	if(currentPage){//若当前页有值，加入当前页
		params.currentPage = currentPage
	};
	$.post(url,params,  function(result){
		if(result.state == 1){
			var pageObject = result.data.pageObject;
			loadPaginationUI(pageObject);
			//设置表格tabody中的内容
			setTableRows(result.data.clientPurHistory);
			//将客户购买记录信息绑定到产品全简选择框
			$('#proNameShow').data('clientPurHistory', result.data.clientPurHistory);
		}else{
			alert(result.message);
		}
		
	});
}
//设置表格内容
function setTableRows(clientPurHistorys){
	//获取客户内容区对象
	let content = $('#content');
	//清空
	content.empty();
	let template = '<th scope="row">[no]</th>' 		+
				   '<td>[purchaseDate]</td>'		+
				   '<td>[purchaseHistory]</td>';
	//获取全还是简显示
	let simplOrFull = $('#proNameShow').data('simplOrFull');
	//内容数量标识
	let token = 0;
	//追加新数据
	for(let i in clientPurHistorys){
		//记录数
		token++;
		//创建tr对象
		let tr;
		//按钮的class属性
		let btnClassName;
		if(i%2==0){
			tr = $('<tr class="rowOne"></tr>');
			btnClassName = 'rowOneBtn';
		}else{
			tr = $('<tr class="rowTwo"></tr>');
			btnClassName = 'rowTwoBtn';
		}
		//将td模板追加到tr
		tr.append(template
				.replace('[no]', token)
				.replace('[purchaseDate]', clientPurHistorys[i].purchaseDate)
				.replace('[purchaseHistory]', simplOrFull?clientPurHistorys[i].purProFull:clientPurHistorys[i].purProSimp));
		//将tr对象追加到内容区
		content.append(tr);
	}
}
