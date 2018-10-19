$(document).ready(function() {
	$('#newModifyModalBills').on('click', '.userOk', doSaveOrUpdate);
	//获取绑定在模态框的id
	let id = $('#newModifyModalBills').data('id');
	//若id存在,表示更新,先根据id查询到此用户信息,初始化模态框数据
	if(id) doGetObjectById(id); 
	//当模态框隐藏时在.userOk上绑定的事件执行解绑动作
	var count = 0;//计数器,强制模态框组件事件监听只出现一次
	$("#newModifyModalBills").on("hidden.bs.modal",function() {
		if (++count == 1) {
			$(this).off('click', '.userOk').removeData("id");
		}
	});
});
//初始化表单数据
function doFillFormData(user) {
	$('#userUNameModify').val(user.username);
	$('#userPasswordModify').val(user.password).attr('readonly',true);//修改时,将密码框只读
	$('#userNewPasswordModify').parent().parent().parent().css('display','');//显示新密码输入框
	$('#userNameModify').val(user.name);
	$('#userEmailModify').val(user.email);
	$('#userMobileModify').val(user.mobile);
}
//根据id查询一条用户信息
function doGetObjectById(id) {
	let url = 'user/doFindUserById.do';
	let params = {'id':id};
	$.post(url, params, function(result){
		if(result.state == 1){
			//初始化表单数据信息
			doFillFormData(result.data);
		}else {
			alert(result.message);
		}
	});
}
//获取储存 | 更新的表单数据
function doGetEditFormData(){
	//判定是新建还是修改,进行密码的选择
	let password = $('#newModifyModalBills').data('id')?$('#userNewPasswordModify').val():$('#userPasswordModify').val();
	let params = {
		'id':$('#newModifyModalBills').data('id'),
		'username':$('#userUNameModify').val(),
		'password':password,
		'name':$('#userNameModify').val(),
		'email':$('#userEmailModify').val(),
		'mobile':$('#userMobileModify').val(),
		'valid':1
	};
	if (params.id) {
		params.modifiedUser = $('#CurrentLoginUser').html();
	} else {
		params.createdUser = $('#CurrentLoginUser').html();
	}
	return params;
}
//存储或更新用户信息
function doSaveOrUpdate(){
	//进行必填数据是否为空的判定
	let boo = inputNullValueJudge('userUNameModify', '亲,用户名不能为空...');
	if(boo) return;//用户名为空,结束存储 | 更新
	if($('#newModifyModalBills').data('id')){
		let boo1 = inputNullValueJudge('userNewPasswordModify', '亲, 新密码不能为空...');
		if (boo1) return;//新密码为空结束此方法
	}else{
		let boo2 = inputNullValueJudge('userPasswordModify', '亲, 密码不能为空...');
		if (boo2) return;//密码为空结束此方法
	}
	let boo3 = inputNullValueJudge('userMobileModify', '亲,手机号不能为空...');
	if(boo3) return;//手机号为空结束此方法
	//1.获得表单数据
	var params = doGetEditFormData();
	//2.将数据提交到服务端
	var id=$("#newModifyModalBills").data("id");//若有id值表示更新
	var url=id?"user/doUpdateUser.do":"user/doSaveUser.do";
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