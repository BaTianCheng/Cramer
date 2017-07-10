Platform = {}

Platform.NoticeList = function(){
	var ii = layer.load();
	$.post(CTX_PATH + "/msg/notifys/list", {
		pageSize : 2,
		pageNum : 1
	}, function(msg) {
		var result = JSON.parse(msg);
		if(result.resultCode == '200'){
			if (result.data.total > 0) {
				//格式化
				for(var i=0;i<result.data.list.length;i++){
					var createTime = result.data.list[i].createTime;
					result.data.list[i].month = createTime.split('-')[1];
					result.data.list[i].day = createTime.split('-')[2];
					if(result.data.list[i].content.length > 200){
						result.data.list[i].content = result.data.list[i].content.substr(0,200) + "...";
					}
				}
				
				var html = template('notice_list_tpl', result.data);
				document.getElementById('notice_list_panel').innerHTML = html;
			}
		} else {
			alert("程序异常");
		}
		layer.close(ii);
	}).error(function(xhr,errorText,errorType){
		layer.close(ii);
		alert("系统错误");
	});
}