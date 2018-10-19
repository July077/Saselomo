$(function(){
//	var viewHeight = window.innerHeight;
//	$('#userNav').css('height',viewHeight*0.08);
//	$('#userCon').css('height',viewHeight*0.92);
//	$('.menu-hide').hide();//隐藏子导航栏
	//禁止滚动条
	$(document.body).css({
	   "overflow-x":"hidden",
	});
	// 当前设备为屏幕大于750时,隐藏滚动条
	 /*if (window.screen.availWidth>750 && window.screen.availHeight>670) {
			$(document.body).css({
			   "overflow-y":"hidden",
			});
	   }*/
	//启用滚动条
	// $(document.body).css({
	  // "overflow-x":"auto",
	//   "overflow-y":"auto"
	// });

	// 修改鼠标移动到导航上的样式，父导航不变
	$('.parentNav').hover(function(){
		// 父导航背景色不变,相当于第一个li
		$(this).children().css({'background-color':'#000'});
	});

	// 点击父导航栏栏,显示对应的子导航栏,同时子导航打开后,将父导航下拉图标改为上拉图标;反之,相反
	$('.parentNav').click(function(){

		var boo = $(this).next().attr('style');//判断是否已经打开
		if(boo == undefined){//初始化时
			$(this).children().children().last().toggleClass().toggleClass('glyphicon glyphicon-menu-up pull-right dropdown-icon');//改为上拉图标
			$(this).nextAll().slideDown();//显示当前父导航下的子导航
			// 关闭除当前子导航的其余子导航前,将其余打开子导航的父导航图标更改为下拉
			var subNavs = $('.parentNav').not($(this)).next();
			for (var i = subNavs.length - 1; i >= 0; i--) {
				var boo = $(subNavs[i]).attr('style');
				$(subNavs[i]).prev().children().children().last().toggleClass().toggleClass('glyphicon glyphicon-menu-down pull-right dropdown-icon');
			}
			$('.menu-hide').not($(this).nextAll()).slideUp();//关闭除当前子导航的其余子导航
		}else if (boo == 'display: none;') {//未打开时
			$(this).children().children().last().toggleClass().toggleClass('glyphicon glyphicon-menu-up pull-right dropdown-icon');//改为上拉图标
			$(this).nextAll().slideDown();//显示当前父导航下的子导航
			// 关闭除当前子导航的其余子导航前,将其余打开子导航的父导航图标更改为下拉
			var subNavs = $('.parentNav').not($(this)).next();
			for (var i = subNavs.length - 1; i >= 0; i--) {
				var boo = $(subNavs[i]).attr('style');
				$(subNavs[i]).prev().children().children().last().toggleClass().toggleClass('glyphicon glyphicon-menu-down pull-right dropdown-icon');
			}
			$('.menu-hide').not($(this).nextAll()).slideUp();//关闭除当前子导航的其余子导航
			if (true) {

			}
		}else if(boo=='display: list-item;'){//已打开
			$(this).children().children().last().toggleClass().toggleClass('glyphicon glyphicon-menu-down pull-right dropdown-icon');//改为下拉图标
			$(this).nextAll().slideUp();//关闭子导航
		}
	});
	
	//设置新建修改模态框取消按钮事件
	$('#newModifyModal').on('click', '.cancelBtn', function(){
		$('#newModifyModal').modal('hide');
	});
	//设置删除模态框取消按钮事件
	$('#deleteModal').on('click', '.cancelBtn', function(){
		$('#deleteModal').modal('hide');
	});
	//设置正常大小新建 | 修改 Modal 收货单、售货单模块方便使用 模态框取消按钮事件
	$('#newModifyModalBills').on('click', '.cancelBtn', function(){
		$('#newModifyModalBills').modal('hide');
	});
	// 设置下拉菜单框选择
	$('.dropdownBox').on('click', 'a', function(){
		$(this).parent().parent().prev().html($(this).html()).css({'color':'#555'});

	});
	//设置点击关闭按钮,隐藏警告框
	$('#alertBox').on('click', '.close', function(){
		$('#alertInfo').html('');
		$('#alertBox').slideUp(400);
	});

});

