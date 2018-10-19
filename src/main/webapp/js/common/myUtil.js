$(function(){
	console.log('myUtil-主要进行输入规则的判定,和Excel导入的初始化...')
});
//以excel导入相关信息
function importObject(url, id){
	//初始化Excel导入的文件
    $("#"+id).fileinput({
        uploadUrl: url,//上传的地址
        uploadAsync: true,              //异步上传
        language: "zh",                 //设置语言
        showPreview: true,             	//是否显示预览区域
        showUpload: false,				//是否显示上传按钮
        showRemove: false,				//是否显示删除按钮
        browseClass: "btn btn-primary", //按钮样式 
        dropZoneEnabled: false,         //是否显示拖拽区域
        fileActionSettings: {			
        	showUpload: true,			//是否显示上传按钮
       		showZoom: false				//是否在缩略图中显示缩放按钮
       	},
        allowedFileExtensions: ["xls", "xlsx"], //接收的文件后缀
        maxFileCount: 1,                        //最大上传文件数限制
        autoReplace: true,				//是否自动替换当前文件，设置为true时，再次选择文件， 会将当前的文件替换掉
        uploadExtraData: {  //上传的时候，增加的附加参数
        	
        }
    });
}
//判定输入框value是否为空
function inputNullValueJudge(id, alertInfo){
	console.log('inputNullValueJudge')
	let input = document.getElementById(id);//获取input对象
	let inputValue = input.value;//获取输入值
	if(!inputValue){
		$('#alertInfo').html(alertInfo);
		$('#alertBox').slideDown(300);
		return true;
	}
	return false;
}
//判定数量是否输入为>1&<99999的数
function countRegEx(id, infoOutputId, alertInfo){
	console.log('countRegEx')
	let input = document.getElementById(id);//获取input对象
	let infoOutput = document.getElementById(infoOutputId);//获取信息输出对象
	let inputValue = input.value;//获取输入值
	let reg = /^[1-9]\d{0,4}$/;//正则
	if(!reg.test(inputValue)){//不匹配
		infoOutput.innerHTML = alertInfo;
		return true;
	}
	infoOutput.innerHTML = '';
	return false;
}
//判定年龄是否合法,为1~999
function ageRegEx(id, alertInfo){
	console.log('ageRegEx')
	let input = document.getElementById(id);//获取input对象
	let inputVal = input.value;//获取输入值
	let reg = /^[1-9]\d{0,2}$/;//正则
	if(!reg.test(inputVal)){//不匹配
		$('#alertInfo').html(alertInfo);
		$('#alertBox').slideDown(300);
		return true;
	}
	return false;
}
//判定时间输入时是否合法,为hh:mm:ss
function timeRegEx(id, alertInfo){
	console.log('timeRegEx')
	let input = document.getElementById(id);//获取input对象
	let inputVal = input.value;//获取输入值
	let reg = /^[0-2][0-9][:]\d{2}[:]\d{2}$/;//正则
	if(inputVal && !reg.test(inputVal)){//不匹配
		$('#alertInfo').html(alertInfo);
		$('#alertBox').slideDown(300);
		return true;
	}
	return false;
}
//判定价格输入控制
function priceRegEx(id, alertInfo){
	console.log('priceRegEx')
	let input = document.getElementById(id);//获取input对象
	let inputVal = input.value;//获取输入值
	let reg1 = /^\d{1,7}/;//先判定是否为7位数字
	let reg2 = /^\d{7}[.]\d{1,2}$/;//在进行判定是否为浮点型
	if(!reg1.test(inputVal)){//不匹配
		if(!reg2.test(inputVal)){
			$('#alertInfo').html(alertInfo);
			$('#alertBox').slideDown(300);
			return true;
		}
	}
	return false;
}
function datepicker(){
	//设置日期控件
	$(".datepicker").datetimepicker({
		format: 'yyyy-mm-dd',
		minView:'month',
		clearBtn:true,
		autoclose:true,
		todayBtn:true
	});
}
function timepicker(){
	//设置时间控件
	$(".timepicker").datetimepicker({
		format:'hh:00:00',
		startView:1,
		minView:1,
		maxView:1,
		keyboardNavigation:false,
		autoclose:true,
		todayBtn:true,
		clearBtn:true,
        endDate:new Date(new Date().getTime() + 23*60*60*1000),
        startDate:new Date()
	});
}



