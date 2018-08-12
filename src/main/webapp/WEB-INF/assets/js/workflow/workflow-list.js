WorkflowList = {}

WorkflowList.GetUndoList = function() {
	$("#main-table").jqGrid({
		url : CTX_PATH + "/workflow/instances/undo/list",
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '任务编号', '流程名称', '发起人', '当前节点', '操作', '描述'],
		colModel : 
			[{name : 'taskId',width : 100,fixed:true}, 
			 {name : 'wfInstance.templateName',width : 220}, 
			 {name : 'wfInstance.creator',width : 100}, 
			 {name : 'taskName',width : 220}, 
			 {name : 'actions',width : 100, align:'center',title:false,sortable:false},
			 {name : 'description',width : 500, align:'center',title:false,sortable:false}],
		jsonReader : {   
			id: "taskId",
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
              be = "<a id=\"td-edit-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:WorkflowList.ShowForm('"+ cl + "');\">处理</a>";
              jQuery("#main-table").jqGrid('setRowData', ids[i],
                  {
            	  	actions : be 
                  });
            }
        }
	});

	$("#main-table").jqGrid('navGrid', '#pager', {
		edit : false,
		add : false,
		del : false,
		search : false,
	});
}

WorkflowList.GetDoneList = function() {
	$("#main-table").jqGrid({
		url : CTX_PATH + "/workflow/instances/done/list",
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '任务编号', '流程名称', '发起人', '处理节点', '描述'],
		colModel : 
			[{name : 'taskId',width : 100,fixed:true}, 
			 {name : 'wfInstance.templateName',width : 220}, 
			 {name : 'wfInstance.creator',width : 100}, 
			 {name : 'taskName',width : 220}, 
			 {name : 'description',width : 500, align:'center',title:false,sortable:false}],
		jsonReader : {   
			id: "taskId",
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
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#pager',
		mtype : "post",
		viewrecords : true,
		emptyrecords: "暂无任何数据"
	});

	$("#main-table").jqGrid('navGrid', '#pager', {
		edit : false,
		add : false,
		del : false,
		search : false,
	});
}

WorkflowList.GetCreatedFinishList = function() {
	$("#main-table2").jqGrid({
		url : CTX_PATH + "/workflow/instances/creator/finish/list",
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '实例编号', '流程名称', '结果', '描述'],
		colModel : 
			[{name : 'instanceId',width : 100,fixed:true}, 
			 {name : 'templateName',width : 220}, 
			 {name : 'result',width : 220,
				 formatter: function(cellValue, options, rowObject) {  
					if(cellValue==null) return '';
					switch(cellValue){
						case 'success' : return '成功';
						case 'cancel' : return '取消';
						case 'uncomplate' : return '执行中';
						default	: return cellValue;
					}
				 }
			 }, 
			 {name : 'description',width : 500, align:'center',title:false,sortable:false}],
		jsonReader : {   
			id: "taskId",
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
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#pager2',
		mtype : "post",
		viewrecords : true,
		emptyrecords: "暂无任何数据"
	});

	$("#main-table2").jqGrid('navGrid', '#pager', {
		edit : false,
		add : false,
		del : false,
		search : false,
	});
}

WorkflowList.GetCreatedUndoList = function() {
	$("#main-table").jqGrid({
		url : CTX_PATH + "/workflow/instances/creator/undo/list",
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '实例编号', '流程名称', '结果', '描述'],
		colModel : 
			[{name : 'instanceId',width : 100,fixed:true}, 
			 {name : 'templateName',width : 220}, 
			 {name : 'result',width : 220,
				 formatter: function(cellValue, options, rowObject) {  
					if(cellValue==null) return '';
					switch(cellValue){
						case 'success' : return '成功';
						case 'cancel' : return '取消';
						case 'uncomplate' : return '执行中';
						default	: return cellValue;
					}
				 }
			 }, 
			 {name : 'description',width : 500, align:'center',title:false,sortable:false}],
		jsonReader : {   
			id: "taskId",
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
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#pager',
		mtype : "post",
		viewrecords : true,
		emptyrecords: "暂无任何数据"
	});

	$("#main-table").jqGrid('navGrid', '#pager', {
		edit : false,
		add : false,
		del : false,
		search : false,
	});
}


//开启表单
WorkflowList.ShowForm = function(taskId){
	var html = WorkFlowForm.GetFormHtml(2,taskId,"进行处理");
}



