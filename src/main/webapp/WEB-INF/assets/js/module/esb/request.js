Request = {}

var ESB_PATH = "http://localhost:16060/esb";

//请求信息列表
Request.list = function(postData){
	$("#main-table").jqGrid({
		url : ESB_PATH + "/manger/requests/list",
		postData : postData,
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '编号', '服务名称', '状态', '启动时间', '结束时间', '操作'],
		colModel : 
			[{name : 'questId',index : 'title',width : 120}, 
			 {name : 'serviceName',index : 'create_by',width : 80},
			 {name : 'status',index : 'create_by',width : 80},
			 {name : 'requestTime',index : 'create_time',width : 80,datefmt:'yyyy-MM-dd HH:mm:ss', align:'center'},
			 {name : 'responseTime',index : 'create_time',width : 80,datefmt:'yyyy-MM-dd HH:mm:ss', align:'center'},
			 {name : 'actions',width : 80, align:'center', title:false,sortable:false}],
		jsonReader : {   
			id: "questId",
			root: "data",
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
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#pager',
		mtype : "post",
		viewrecords : true,
		emptyrecords: "暂无任何数据",
		gridComplete : function() {
            var ids = jQuery("#main-table").jqGrid('getDataIDs');
            for ( var i = 0; i < ids.length; i++) {
              var cl = ids[i];
              ae = "<a id=\"td-view-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:Notify.OpenView('"+ cl + "');\">查看</a>";
              jQuery("#main-table").jqGrid('setRowData', ids[i],
                  {
            	  	actions : ae
                  });
            }
        }
	});

	$("#main-table").jqGrid('navGrid', '#pager', 
		{edit : false,add : false,del : false,search:false}
	);
	
	$("#main-table").jqGrid().setGridParam({'postData':postData});
	$("#main-table").jqGrid().trigger('reloadGrid');
}