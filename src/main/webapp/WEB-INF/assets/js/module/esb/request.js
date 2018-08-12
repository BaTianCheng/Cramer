Request = {}

//搜索请求
Request.Search = function(){
	var condition = {};
	if(GetQueryString("identification") != null){
		condition.identification = GetQueryString("identification");
	}
	if($("#sel-service").val() != ""){
		condition.serviceCode = $("#sel-service").val();
	}
	if($("#sel-service").val() == "" && $("#sel-module").val() != ""){
		condition.module = $("#sel-module").val();
	}
	if($("#beginTime").datepicker("getDate") != null){
		condition.beginTime = Date.parse($("#beginTime").datepicker("getDate"));
	}
	if($("#endTime").datepicker("getDate") != null){
		condition.endTime = Date.parse($("#endTime").datepicker("getDate"))+24*60*60*1000;
	}
	
	Request.List({"condition":JSON.stringify(condition)});
}

//请求信息列表
Request.List = function(postData){
	$("#main-table").jqGrid({
		url : ESB_PATH + "/manger/requests/list",
		postData : postData,
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '状态', '编号', '服务名称', '启动时间', '结束时间', '操作'],
		colModel : 
			[{name : 'status',index : 'status',width : 60,align:'center',sortable:false,
				 formatter:function(cellvalue, options, rowObject){
						switch(cellvalue){
							case '1201':return "<sapn class='label label-info'>进入队列</span>";
							case '1202':return "<sapn class='label label-info'>正在处理</span>";
							case '1203':return "<sapn class='label label-success'>执行完成</span>";
							case '1301':return "<sapn class='label label-warning'>执行失败</span>";
							case '1400':return "<sapn class='label label-danger'>请求失败</span>";
					}
				 }},
			 {name : 'questId',index : 'quest_id',width : 120,sortable:false}, 
			 {name : 'serviceName',index : 'service_name',width : 80,sortable:false},
			 {name : 'requestTime',index : 'request_time',width : 80,datefmt:'yyyy-MM-dd HH:mm:ss', align:'center',sortable:false,
				 	formatter:function(cellvalue, options, rowObject){
				 		return getTimeDesc(cellvalue);
				 	}},
			 {name : 'responseTime',index : 'response_time',width : 80,datefmt:'yyyy-MM-dd HH:mm:ss', align:'center',sortable:false,
					 formatter:function(cellvalue, options, rowObject){
						 return getTimeDesc(cellvalue);
					 }},
			 {name : 'actions',width : 80, align:'center', title:false,sortable:false}
			],
		jsonReader : {   
			id: "questId",
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
		gridComplete : function() {
            var ids = jQuery("#main-table").jqGrid('getDataIDs');
            for ( var i = 0; i < ids.length; i++) {
              var cl = ids[i];
              ae = "<a id=\"td-view-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:Request.OpenTraceView('"+ cl + "');\">记录</a>";
              ie = "<a id=\"td-view-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:Request.OpenInfoView('"+ cl + "');\">日志</a>";
              jQuery("#main-table").jqGrid('setRowData', ids[i],
                  {
            	  	actions : ae + ie
                  });
            }
        }
	});

	$("#main-table").jqGrid('navGrid', '#pager', 
		{edit : false,add : false,del : false,search:false}
	);
	
	$("#main-table").jqGrid().setGridParam({'postData':postData, 'page':1,});
	$("#main-table").jqGrid().trigger('reloadGrid');
}

//打开查看轨迹信息
Request.OpenTraceView = function(questId) {
	if (questId != "") {
		$.when($.post(ESB_PATH + "/results/tracks/get", {
			questId : questId
		})).done(function(d1) {
			var messages =  JSON.parse(d1).data;
			var text = "";
			for(var i=0;i<messages.length;i++){
				text += getTimeDesc(messages[i].time) +"    "+ messages[i].message + "\r\n";
			}
			var track ={};
			track.text = text;
			var html = template('track_view_tpl', track);
			layer.open({
				type : 1,
				title : '查看轨迹信息',
				skin : 'layui-layer-rim',
				area : [ '600px', '510px' ],
				closeBtn : 1,
				content : html
			});
		});
	}
}

//打开日志信息信息
Request.OpenInfoView = function(questId) {
	if (questId != "") {
		$.when($.post(ESB_PATH + "/results/get", {
			questId : questId
		})).done(function(d1) {
			var messages =  JSON.parse(d1).data;
			var info ={};
			info.text = JSON.stringify(messages, null, 2);
			var html = template('info_view_tpl', info);
			layer.open({
				type : 1,
				title : '查看日志信息',
				skin : 'layui-layer-rim',
				area : [ '600px', '510px' ],
				closeBtn : 1,
				content : html
			});
		});
	}
}

//获得服务信息列表
Request.getServices = function(){
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
				var html = "<option value=''>全部模块</option>"
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
				var html = "<option value=''>全部服务</option>"
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
		var html = "<option value=''>全部服务</option>"
		for (var i = 0; i < services.length; i++) {
			if (services[i].module == $selModule.val() || $selModule.val()=="") {
				html += "<option value='" + services[i].serviceCode + "'>"
						+ services[i].serviceName + "</option>"
			}
		}
		$selService.html(html);
		$selService.select2();
	});
}

