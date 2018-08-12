PlanningJob = {}

//计划任务列表
PlanningJob.List = function(){
	$("#main-table").jqGrid({
		url : ESB_PATH + "/jobs/planningJobs/list",
		postData : {condition:"{}"},
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '任务名称','服务编码','服务名称', '状态', '类型', '计划描述', '最近执行时间', '下次执行时间','操作'],
		colModel : 
			[{name : 'jobName',index : 'jobName',width : 80,sortable:false}, 
			 {name : 'serviceCode',index : 'serviceCode',width : 80,hidden:true}, 
			 {name : 'serviceName',index : 'serviceName',width : 80,sortable:false}, 
			 {name : 'status',index : 'status',width : 45,align:'center',sortable:false,
				 formatter:function(cellvalue, options, rowObject){
						switch(cellvalue){
							case 1:return "<sapn class='label label-success'>启用中</span>";
							case 0:return "<sapn class='label label-warning'>停用中</span>";
					}
			 }},
			 {name : 'jobRule',index : 'jobRule',width : 45,align:'center',sortable:false,
				 formatter:function(cellvalue, options, rowObject){
						switch(cellvalue){
							case 1:return "每天";
							case 2:return "每周";
							case 3:return "每月";
					}
			 }},
			 {name : 'jobRuleValues',index : 'jobRuleValues',width : 150,align:'left',sortable:false,
				 formatter:function(cellvalue, options, rowObject){
					 var desc = "";
					switch(rowObject.jobRule){
						case 1:desc="每天";
							for(var i=0; i<rowObject.jobRuleValues.length;i++){
								desc += parseInt(rowObject.jobRuleValues[i]/60) + '时'+rowObject.jobRuleValues[i]%60+'分';
								if(i < rowObject.jobRuleValues.length-1){
									desc+="，";
								}
							}
							break;
						case 2:desc="每周";
							for(var i=0; i<rowObject.jobRuleValues.length;i++){
								desc += rowObject.jobRuleValues[i]+'，';
							}
							desc += "时间："+parseInt(rowObject.jobRuleTime/60) + '时'+rowObject.jobRuleTime%60+'分;';
							break;
						case 3:desc="每月";
							for(var i=0; i<rowObject.jobRuleValues.length;i++){
								desc += rowObject.jobRuleValues[i]+'日，';
							}
							desc += "时间："+parseInt(rowObject.jobRuleTime/60) + '时'+rowObject.jobRuleTime%60+'分;';
							break;
					}
					return desc;
			 }},
			 {name : 'lastTime',index : 'lastTime',width : 80,datefmt:'yyyy-MM-dd HH:mm:ss', align:'center',sortable:false,
				 	formatter:function(cellvalue, options, rowObject){
				 		return getTimeDesc(cellvalue);
				 	}},
			 {name : 'nextTime',index : 'nextTime',width : 80,datefmt:'yyyy-MM-dd HH:mm:ss', align:'center',sortable:false,
					 formatter:function(cellvalue, options, rowObject){
						 return getTimeDesc(cellvalue);
					 }},
			 {name : 'actions',width : 80, align:'left', title:false,sortable:false,
					formatter:function(cellvalue, options, rowObject){
						 ae = "<a id=\"td-view-"+rowObject.serviceCode+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:PlanningJob.OpenHistory('"+ rowObject.serviceCode + "');\">历史记录</a>";
			             be = "<a id=\"td-view-"+rowObject.serviceCode+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:PlanningJob.OpenEdit('"+ rowObject.jobCode + "');\">编辑</a>";
			             if(rowObject.status == 0){
			            	 ce = "<a id=\"td-view-"+rowObject.serviceCode+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:PlanningJob.OpenDelete('"+ rowObject.jobCode + "');\">删除</a>";
			             } else {
			            	 ce = "";
			             }
			             
			        	 return ae+be+ce;
					}}
			],
		jsonReader : {   
			id: "jobCode",
			root: "data.list",
		    page: "data.pageNum",
		    total: "data.pages",
		    records: "data.total",
		    repeatitems: true
		},
		prmNames : {
			page:"pageNum", // 表示请求页码的参数名称
			rows:"pageSize", // 表示请求行数的参数名称
			sort: "sortId", // 表示用于排序的列名的参数名称
			order: "sortType", // 表示采用的排序方式的参数名称
			search:"_search", // 表示是否是搜索请求的参数名称
			nd:"nd", // 表示已经发送请求的次数的参数名称
			id:"id", // 表示当在编辑数据模块中发送数据时，使用的id的名称
			oper:"oper", // operation参数名称
			editoper:"edit", // 当在edit模式中提交数据时，操作的名称
			addoper:"add", // 当在add模式中提交数据时，操作的名称
			deloper:"del", // 当在delete模式中提交数据时，操作的名称
			subgridid:"id", // 当点击以载入数据到子表时，传递的数据名称
			npage: null,
			totalrows:"totalrows" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
		},
		rowNum : $("#page-size").val(),
		rowList : [ 10, 20, 30, 50, 100 ],
		pager : '#pager',
		mtype : "post",
		viewrecords : true,
		emptyrecords: "暂无任何数据",
		gridComplete : function() {}
	});

	$("#main-table").jqGrid('navGrid', '#pager', 
		{edit : true,add : true,del : false,search:false,addfunc:PlanningJob.OpenAdd,editfunc:PlanningJob.OpenUpdate}
	);
	
	$("#main-table").jqGrid().trigger('reloadGrid');
}

//打开历史记录
PlanningJob.OpenHistory = function(serviceCode){
	var url = window.location.href.split('#')[0] + '#' + 'esb/history?serviceCode='+serviceCode+'&identification=SYS_JOB';
	window.open(url);
}

//打开添加计划任务
PlanningJob.OpenAdd = function() {
	$.post(ESB_PATH + "/manger/services/list", 
		function(msg) {
			var html = template('plannningjob_tpl', {});
			var result = JSON.parse(msg);
			layer.open({
				type : 1,
				title : '添加计划任务',
				skin : 'layui-layer-rim',
				area : [ '700px', 'auto' ],
				closeBtn : 1,
				content : html
			});
			if (result.statusCode == '200') {
				var html = template('sel_service_tpl', result);
				$("#serviceCode").html(html);
			}
	});
}

//打开修改计划任务
PlanningJob.OpenEdit = function(jobCode) {
	var data = {};
	if (jobCode != "") {
		$.when($.post(ESB_PATH + "/jobs/planningJobs/get", {jobCode : jobCode}),
				$.post(ESB_PATH + "/manger/services/list")).done(function(d1, d2) {
			var result = JSON.parse(d1[0]);
			var serviceList = JSON.parse(d2[0]);
			if (result.statusCode == '200') {
				data = result.data;
				data.jobRuleValues = JSON.stringify(data.jobRuleValues)
				var html = template('plannningjob_tpl', data);
				layer.open({
					type : 1,
					title : '修改计划任务',
					skin : 'layui-layer-rim',
					area : [ '700px', 'auto' ],
					closeBtn : 1,
					content : html
				});
				if (serviceList.statusCode == '200') {
					var html = template('sel_service_tpl', serviceList);
					$("#serviceCode").html(html);
				}
				$("#serviceCode").val(data.serviceCode);
			} else {
				alert("程序异常");
			}
		});
	} 
}

//添加计划任务
PlanningJob.Insert = function(serialize) {
	if($("#jobCode").val()=="" || $("#jobName").val()=="" || $("#serviceCode").val()=="" || $("#jobRuleValues").val()==""){
		layer.msg('存在必填项未填写', {icon : 2});
		return;
	}
	serialize.jobRuleValues = JSON.parse(serialize.jobRuleValues);
	serialize.serviceName = $("#serviceCode").find("option:selected").text();
	var entityStr = JSON.stringify(serialize);
	$.post(ESB_PATH + "/jobs/planningJobs/insert", {entity:entityStr}, function(msg) {
		var result = JSON.parse(msg);
		if (result.statusCode == '200') {
			layer.closeAll();
			layer.msg('添加成功', {
				icon : 1
			});
			$("#main-table").trigger("reloadGrid");
		} else if(result.statusCode == '400'){
			layer.msg(result.message, {
				icon : 2
			});
		}else {
			layer.msg('程序异常', {
				icon : 2
			});
		}
	}).error(function(xhr, errorText, errorType) {
		layer.msg('系统错误', {
			icon : 2
		});
	});
}

//修改计划任务
PlanningJob.Update = function(serialize) {
	if($("#jobCode").val()=="" || $("#jobName").val()=="" || $("#serviceCode").val()=="" || $("#jobRuleValues").val()==""){
		layer.msg('存在必填项未填写', {icon : 2});
		return;
	}
	serialize.jobRuleValues = JSON.parse(serialize.jobRuleValues);
	serialize.serviceName = $("#serviceCode").find("option:selected").text();
	var entityStr = JSON.stringify(serialize);
	$.post(ESB_PATH + "/jobs/planningJobs/update", {entity:entityStr}, function(msg) {
		var result = JSON.parse(msg);
		if (result.statusCode == '200') {
			layer.closeAll();
			layer.msg('修改成功', {
				icon : 1
			});
			$("#main-table").trigger("reloadGrid");
		} else if(result.statusCode == '400'){
			layer.msg(result.message, {
				icon : 2
			});
		}else {
			layer.msg('程序异常', {
				icon : 2
			});
		}
	}).error(function(xhr, errorText, errorType) {
		layer.msg('系统错误', {
			icon : 2
		});
	});
}

//删除计划任务
PlanningJob.OpenDelete = function (jobCode){
	layer.confirm('确定要删除计划任务吗?', {icon: 3, title:'提示'}, function(index){
		$.post(ESB_PATH + "/jobs/planningJobs/delete", {
			jobCode : jobCode
		},function(msg) {
			var result = JSON.parse(msg);
			if(result.statusCode == '200'){
				$("#main-table").trigger("reloadGrid");
			} else {
				alert("程序异常");
			}
		}).error(function(xhr,errorText,errorType){
			alert("系统错误");
		});
		layer.close(index);
	});
}
