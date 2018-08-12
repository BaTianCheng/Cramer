RegularJob = {}

//实时任务列表
RegularJob.List = function(){
	$("#main-table").jqGrid({
		url : ESB_PATH + "/jobs/regularJobs/list",
		postData : {condition:"{}"},
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '任务名称','服务编码','服务名称','状态','时间间隔(秒)','最近执行时间','操作'],
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
			 {name : 'intervalSecond',index : 'intervalSecond',width : 45,align:'center',sortable:false},
			 {name : 'lastTime',index : 'lastTime',width : 80,datefmt:'yyyy-MM-dd HH:mm:ss', align:'center',sortable:false,
				 	formatter:function(cellvalue, options, rowObject){
				 		return getTimeDesc(cellvalue);
				 	}},
			 {name : 'actions',width : 80, align:'center', title:false,sortable:false,sortable:false,
				 	formatter:function(cellvalue, options, rowObject){
				 		be = "<a id=\"td-view-"+rowObject.serviceCode+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:RegularJob.OpenEdit('"+ rowObject.jobCode + "');\">编辑</a>";
				 		if(rowObject.status == 0){
				 			ce = "<a id=\"td-view-"+rowObject.serviceCode+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:RegularJob.OpenDelete('"+ rowObject.jobCode + "');\">删除</a>";
				        } else {
				        	ce = "";
				        }
				 		return be+ce;
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
		gridComplete : function() { }
	});

	$("#main-table").jqGrid('navGrid', '#pager', 
		{edit : true,add : true,del : false,search:false,addfunc:RegularJob.OpenAdd,editfunc:RegularJob.OpenUpdate}
	);
	
	$("#main-table").jqGrid().trigger('reloadGrid');
}

//打开添加实时任务
RegularJob.OpenAdd = function() {
	$.post(ESB_PATH + "/manger/services/list", 
		function(msg) {
			var html = template('regularjob_tpl', {});
			var result = JSON.parse(msg);
			layer.open({
				type : 1,
				title : '添加实时任务',
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

//打开修改实时任务
RegularJob.OpenEdit = function(jobCode) {
	var data = {};
	if (jobCode != "") {
		$.when($.post(ESB_PATH + "/jobs/regularJobs/get", {jobCode : jobCode}),
				$.post(ESB_PATH + "/manger/services/list")).done(function(d1, d2) {
			var result = JSON.parse(d1[0]);
			var serviceList = JSON.parse(d2[0]);
			if (result.statusCode == '200') {
				data = result.data;
				data.jobRuleValues = JSON.stringify(data.jobRuleValues)
				var html = template('regularjob_tpl', data);
				layer.open({
					type : 1,
					title : '修改实时任务',
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

//添加实时任务
RegularJob.Insert = function(serialize) {
	if($("#jobCode").val()=="" || $("#jobName").val()=="" || $("#serviceCode").val()=="" || $("#intervalSecond").val()==""){
		layer.msg('存在必填项未填写', {icon : 2});
		return;
	}
	serialize.serviceName = $("#serviceCode").find("option:selected").text();
	var entityStr = JSON.stringify(serialize);
	$.post(ESB_PATH + "/jobs/regularJobs/insert", {entity:entityStr}, function(msg) {
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

//修改实时任务
RegularJob.Update = function(serialize) {
	if($("#jobCode").val()=="" || $("#jobName").val()=="" || $("#serviceCode").val()=="" || $("#intervalSecond").val()==""){
		layer.msg('存在必填项未填写', {icon : 2});
		return;
	}
	serialize.serviceName = $("#serviceCode").find("option:selected").text();
	var entityStr = JSON.stringify(serialize);
	$.post(ESB_PATH + "/jobs/regularJobs/update", {entity:entityStr}, function(msg) {
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

//删除实时任务
RegularJob.OpenDelete = function (jobCode){
	layer.confirm('确定要删除实时任务吗?', {icon: 3, title:'提示'}, function(index){
		$.post(ESB_PATH + "/jobs/regularJobs/delete", {
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

