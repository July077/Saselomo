$(function(){
	let url = 'client/clientUpload.do';
	let id = 'excelClient';
	//以excel导入相关信息
	importObject(url, id);
	//文件上传成功后事件
    $("#importModal").on("fileuploaded", function(event, data, previewId, index) {
    	$('#importModal').modal('hide');
    	console.log(data)
    	let response = data.response;//获取服务器响应内容
    	if(response.state == 1){
    		doGetObjects();
    	}else{
    		$('#alertInfo').html(response.message);
    		$('#alertBox').slideDown(300);
    	}
    });
});