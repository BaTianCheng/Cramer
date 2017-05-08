Log = {}

//操作日志列表
Log.List = function (){
	$("#main-table").jqGrid({
		url : CTX_PATH + "/sys/logs/list",
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '编号', '模块', '操作类型', '操作人', '操作内容', '操作时间', 'IP地址'],
		colModel : 
			[{name : 'id',index : 'id',width : 55,fixed:true}, 
			 {name : 'moduleId',index : 'module_id asc, id',width : 100}, 
			 {name : 'operateType',index : 'operate_type',width : 100},
			 {name : 'operateBy',index : 'operate_by',width : 120},
			 {name : 'content',index : 'operate_by',width : 200},
			 {name : 'operateTime',index : 'operate_time',width : 100,datefmt:'yyyy-MM-dd HH:mm:ss'},
			 {name : 'operateIp',index : 'operate_ip',width : 100}],
		jsonReader : {   
			id: "id",
			root: "data.list",
		    page: "data.pageNum",
		    total: "data.pages",
		    records: "data.total",
		    repeatitems: true
		},
		prmNames : {
			page:"pageNum", // 表示请求页码的参数名称
			rows:"pageSize", // 表示请求行数的参数名称
			sort: "sidx", // 表示用于排序的列名的参数名称
			order: "sord", // 表示采用的排序方式的参数名称
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
		sortname : 'id',
		sortorder : "asc",
		mtype : "post",
		viewrecords : true,
		emptyrecords: "暂无任何数据"
	});

	$("#main-table").jqGrid('navGrid', '#pager', 
		{edit : false,add : false,del : false,search:false}
	);
}