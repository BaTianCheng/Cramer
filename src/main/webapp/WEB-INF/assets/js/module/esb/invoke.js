Invoke = {};

//定时器编号
Invoke.timerId = 0;

//获得服务信息列表
Invoke.getServices = function(){
	var $selModule = $("#sel-module");
	var $selService = $("#sel-service");
	var modules, services;
	$.ajax({
		type : "GET",
		url : ESB_PATH + "/manger/modules/list",
		async : false,
		dataType : "json",
		success : function(result) {
			if (result.statusCode == '200') {
				modules = result.data;
				var html = "<option value=''>选择模块</option>"
				for (var i = 0; i < modules.length; i++) {
					html += "<option value='" + modules[i].module + "'>"
							+ modules[i].name + "</option>"
				}
				$selModule.html(html);
				$selModule.select2();
			} else {
				layer.msg('系统错误', {
					icon : 2
				});
				return;
			}
		}
	});
	$.ajax({
		type : "GET",
		url : ESB_PATH + "/manger/services/list",
		async : false,
		dataType : "json",
		success : function(result) {
			if (result.statusCode == '200') {
				services = result.data;
				var html = "<option value=''>选择服务</option>"
				for (var i = 0; i < services.length; i++) {
					html += "<option value='" + services[i].serviceCode + "'>"
							+ services[i].serviceName + "</option>"
				}
				$selService.html(html);
				$selService.select2();
			} else {
				layer.msg('系统错误', {
					icon : 2
				});
				return;
			}
		}
	});

	//选择模块后事件
	$selModule.on("change", function() {
		var html = "<option value=''>选择服务</option>"
		for (var i = 0; i < services.length; i++) {
			if (services[i].module == $selModule.val() || $selModule.val()=="") {
				html += "<option value='" + services[i].serviceCode + "'>"
						+ services[i].serviceName + "</option>"
			}
		}
		$selService.html(html);
		$selService.select2();
	});
	
	//选择服务，设置参数
	$("#sel-service").change(function(){
		if($("#sel-service").val()==""){
			$("#form-params").html("");
		}
		for(var i=0;i<services.length;i++){
			if(services[i].serviceCode == $("#sel-service").val()){
				var paramsHtml = template('paramters_tpl', services[i]);
				$("#form-params").html(paramsHtml);
			}
		}
	});
}

//调用服务
Invoke.sendService = function (){
	$("#questId").text("");
	$("#list").text("");
	$("#requestTime").text("");
	$("#excuteTime").text("");
	$("#responseTime").text("");
	lockInput();
	
	if($("#sel-service").val()==""){
		layer.alert('请选择要调用的服务');
		allowInput();
		return;
	}
	
	var params = {};
	for(var i=0; i<$(".input-param").length;i++){
		params[$(".input-param")[i].id.toString()]=$(".input-param")[i].value;
	}
	
	$.post(ESB_PATH + "/services/access",
		{
			"serviceCode" : $("#sel-service").val(),
			"data" : JSON.stringify(params),
			"async" : true
		},
		function(msg){
			var result = JSON.parse(msg);
			if (result.statusCode == '200') {
				$("#status").html("执行中<i class='fa fa-refresh fa-spin fa-fw margin-bottom'></i>");
				$("#questId").text(result.questId);
				Invoke.timerId = setInterval(Invoke.getTraces,2000);
				setTimeout(Invoke.getResult, 2000);
			} else {
				$("#status").text("调用失败");
				allowInput();
			}
		}
	);
}

//查询轨迹
Invoke.getTraces = function (){
	$.post(ESB_PATH + "/results/tracks/get",
			{questId : $("#questId").text()},
			function(msg){
				var messages = JSON.parse(msg).data;
				var text = "";
				
				for(var i=0;i<messages.length;i++){
					text += getTimeDesc(messages[i].time) +"    "+ messages[i].message + "\r\n";
				}
				$("#list").text(text);
				$("#list").scrollTop($("#list")[0].scrollHeight);
				if(messages[messages.length-1].no == '200'){
					clearInterval(Invoke.timerId);
					Invoke.getResult();
					allowInput();
					setTimeout(100,function(){});
				}
			}
		);
}

//查询结果
Invoke.getResult = function (){
	$.post(ESB_PATH + "/results/get",
			{questId : $("#questId").text()},
			function(msg){
				var result = JSON.parse(msg);
				if (result.statusCode == '200') {
					data = result.data;
					$("#requestTime").text(getTimeDesc(data.requestTime));
					$("#excuteTime").text(getTimeDesc(data.excuteTime));
					$("#responseTime").text(getTimeDesc(data.responseTime));
					
					switch(data.status){
						//case '1201':$("#status").text("进入队列");break;
						//case '1202':$("#status").text("正在处理");break;
						case '1203':$("#status").text("执行完成");break;
						case '1301':$("#status").text("执行失败");break;
						case '1400':$("#status").text("请求失败");break;
					}
					
					if(data.result != null && data.result != "" && $("#list").text().indexOf("##########返回结果##########：")<0){
						$("#list").text($("#list").text()+"##########返回结果##########：\r\n"+data.result.toString()+"\r\n");
						$("#list").scrollTop($("#list")[0].scrollHeight);
					}
				} else {
					layer.msg('系统错误', {icon : 2});
				}
			}
		);
}

//锁定，禁止输入
function lockInput(){
	$("#btn-start").attr("disabled",true);
	$("#sel-module").attr("disabled",true);
	$("#sel-service").attr("disabled",true);
	$(".input-param").attr("disabled",true);
}

//取消锁定，允许输入
function allowInput(){
	$("#btn-start").attr("disabled",false);
	$("#sel-module").attr("disabled",false);
	$("#sel-service").attr("disabled",false);
	$(".input-param").attr("disabled",false);
}
