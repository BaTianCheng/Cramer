Workflow = {}

//获取可发起流程列表
Workflow.GetTemplates = function(){
	var $selFlow = $("#sel-flow");
	var items = [];
	$.post(CTX_PATH+'/workflow/templates/list',
		{},
		function(result){
			var code = JSON.parse(result).resultCode;
			if(code == "200"){
				var str = '';
				items = JSON.parse(result).data;
				
				if(items !=null){
					// 加载可发起的
					var html = "<option value=''>选择流程</option>"
					for (var i = 0; i < items.length; i++) {
						html += "<option value='" + items[i].templateKey + "'>"
								+ items[i].templateName + "</option>"
					}
					$selFlow.html(html);
					$selFlow.select2();
				}

			} else {
				layer.msg('程序异常', {icon: 2});
			}
		}
	);
	
	// 选择流程后，显示流程模板详情
	$selFlow.on("change", function() {
		for (var i = 0; i < items.length; i++) {
			if (items[i].templateKey == $selFlow.val() ) {
				$("#description").text(items[i].description);
				$("#workflow-image").attr("src","files/workflow/images/"+items[i].templateKey+".png?"+Date.parse(new Date()));
			}
		}
	});
}

//重新部署
Workflow.Deploy = function(){
	$.post(CTX_PATH+'/workflow/manager/deploy',
		{},
		function(result){
			var code = JSON.parse(result).resultCode;
			if(code == "200"){
				layer.msg('部署成功', {icon: 1});
				location.reload();
			} else {
				layer.msg('部署失败', {icon: 2});
			}
		}
	);
}

//开启表单
Workflow.ShowForm = function(){
	var html = WorkFlowForm.GetFormHtml(1,$('#sel-flow').val(),$("#sel-flow").find("option:selected").text());
}
