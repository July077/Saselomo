$(function(){
	$("#newModifyModal").on('click','.clientOk', doSaveOrUpdate);
	$('#clientNameModify').on('blur', clientNameBlur);
	$('#clientAgeModify').on('change', clientAgeChange);
	$('#clientTimetableModify').on('change', clientTimetableChange);
	
	//调用时间控件
	timepicker();
	//获得模态框上绑定的id值
	var id=$("#newModifyModal").data("id");
	//假如id有值,说明这是修改,然后根据id获得对象,初始化模态框数据
	if(id) doGetObjectByModify(id);
	//当模态框隐藏时在.clientOk上绑定的事件执行解绑动作
	var count = 0;//计数器,强制模态框组件事件监听只出现一次
	$("#newModifyModal").on("hidden.bs.modal",function() {
		if (++count == 1) {
			$(this).off('click', '.clientOk').removeData("id");
		}
	});
});
//时间框值改变事件,进入输入判定
function clientTimetableChange(){
	let boo = timeRegEx('clientTimetableModify', '请输入或选择正确的时间,格式为HH:mm:ss');
	if(boo){
		$('#clientTimetableModify').val('');
	}
}
//年龄框值改变事件,进行输入的判定
function clientAgeChange(){
	let boo = ageRegEx('clientAgeModify', '请输入正确年龄,取值为:1~999');
	if(boo){
		$('#clientAgeModify').val('');
	}
}
//姓名框失去焦点事件,如进行是否为空的判断
function clientNameBlur(){
	inputNullValueJudge('clientNameModify', '亲,姓名为必输项,不能为空');
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
	//性别
	var sex = obj.sex;
	if(sex == ''){
		sex = '请选择性别';
	}
	$("#clientNameModify").val(obj.name);
	$("#clientSexModify").val(sex);
	$("#clientAgeModify").val(obj.age);
	$("#clientAddressModify").val(obj.address);
	$('#clientPhoneModify').val(obj.phone);
	$('#clientSkinConditionModify').val(obj.skinCondition);
	$('#clientTimetableModify').val(obj.timetable);
	$('#clientNoteModify').val(obj.note);
}
//保存或更新客户信息
function doSaveOrUpdate(){
	let boo = inputNullValueJudge('clientNameModify', '亲,姓名为必输项,不能为空');
	if(boo){//若姓名为空,结束此方法
		return;
	}
	//1.获得表单数据
	var params = doGetEditFormData();
	//2.将数据提交到服务端
	var id=$("#newModifyModal").data("id");
	var url=id?"client/doUpdateClient.do":"client/doSaveClient.do";
	$.post(url,params,function(result){
		if(result.state==1){
			//1)隐藏模态框
			$("#newModifyModal").modal("hide");
			//2)重新查询列表数据
			doGetObjects();
		}else{
		   alert(result.message);
		}
	});
}
//获取表单数据
function doGetEditFormData(){
	//性别
	var sex = $("#clientSexModify option:selected").val();
	if(sex == '请选择性别'){
		sex = '';
	}
	var params={
			"id":$("#newModifyModal").data("id"),//修改时需要
			"name":$("#clientNameModify").val(),
			"valid":'1',
			"sex":sex,
			"age":$("#clientAgeModify").val(),
			"address":$("#clientAddressModify").val(),
			"phone":$('#clientPhoneModify').val(),
			"skinCondition":$('#clientSkinConditionModify').val(),
			"timetable":$('#clientTimetableModify').val(),
			"note":$('#clientNoteModify').val(),
			"createdUser":'花花',
			"modifiedUser":'花花'};
	//检测获得的结果
//	console.log(JSON.stringify(params));
	return params;
}