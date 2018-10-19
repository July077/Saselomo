//加载客户模块
$('#clientNav').click(function(){
	var url="client/listUI.do?t="+Math.random(1000);
	$('#context-core').load(url);
});
//加载产品模块
$('#productNav').click(function(){
	var url="product/listUI.do?t="+Math.random(1000);
	$('#context-core').load(url);
});
//加载收获单模块
$('#receivingNav').click(function(){
	var url="receiving/listUI.do?t="+Math.random(1000);
	$('#context-core').load(url);
});
//加载售货单模块
$('#salesNav').click(function(){
	var url="sales/listUI.do?t="+Math.random(1000);
	$('#context-core').load(url);
});
//加载库存模块
$('#inventoryNav').click(function(){
	var url="inventory/listUI.do?t="+Math.random(1000);
	$('#context-core').load(url);
});
//加载用户模块
$('#userNav').click(function(){
	var url="user/listUI.do?t="+Math.random(1000);
	$('#context-core').load(url);
});
//加载权限项模块
$('#permissionNav').click(function(){
	var url="permission/listUI.do?t="+Math.random(1000);
	$('#context-core').load(url);
});
//加载角色模块
$('#roleNav').click(function(){
	var url="role/listUI.do?t="+Math.random(1000);
	$('#context-core').load(url);
});