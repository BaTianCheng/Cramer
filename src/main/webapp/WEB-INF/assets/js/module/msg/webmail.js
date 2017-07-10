WebMail = {}

//站内信列表
WebMail.List = function (postData){
	$("#main-table").jqGrid({
		url : CTX_PATH + "/msg/webmails/list",
		postData : postData,
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '类型', '标题', '发件人','发件时间', '操作'],
		colModel : 
			[{name : 'type',index : 'web_mail_storage_type',width : 80}, 
			 {name : 'title',index : 'web_mail_storage_title',width : 400,editable :true}, 
			 {name : 'msgWebMail.senderName',index : 'web_mail_sender',width : 80},
			 {name : 'msgWebMail.sendTime',index : 'web_mail_send_time',width : 80,datefmt:'yyyy-MM-dd HH:mm:ss', align:'center'},
			 {name : 'actions',width : 80, align:'center', title:false,sortable:false}],
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
		editurl : CTX_PATH+"/msg/notifys/update",
		gridComplete : function() {
            var ids = jQuery("#main-table").jqGrid('getDataIDs');
            for ( var i = 0; i < ids.length; i++) {
              var cl = ids[i];
              ae = "<a id=\"td-view-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:WebMail.OpenView('"+ cl + "');\">查看</a>";
              jQuery("#main-table").jqGrid('setRowData', ids[i],
                  {
            	  	actions : ae
                  });
            }
        }
	});

	$("#main-table").jqGrid('navGrid', '#pager', 
		{edit : false,add : true,del : false,search:false,addfunc:WebMail.OpenSend}
	);
	
	$("#main-table").jqGrid().setGridParam({'postData':postData});
	$("#main-table").jqGrid().trigger('reloadGrid');
}

// 打开发送站内信
WebMail.OpenSend = function() {
	var html = template('webmail_tpl', {});
	layer.open({
		type : 1,
		title : '发送站内信',
		skin : 'layui-layer-rim',
		area : [ '800px', 'auto' ],
		closeBtn : 1,
		content : html
	});
}

// 发送站内信
WebMail.Send = function(serialize) {
	$.post(CTX_PATH + "/msg/webmails/send", serialize, function(msg) {
		var result = JSON.parse(msg);
		if (result.resultCode == '200') {
			layer.close(layer.index);
			layer.msg('发送成功', {
				icon : 1
			});
			$("#main-table").trigger("reloadGrid");
		} else {
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

// 打开查看站内信
WebMail.OpenView = function(notifyId) {
	var data = {};
	if (notifyId > 0) {
		$.when($.post(CTX_PATH + "/msg/webmails/get", {
			notifyId : notifyId
		})).done(function(d1) {
			var result = JSON.parse(d1);
			if (result.resultCode == '200') {
				data = result.data;
				var html = template('webmail_view_tpl', data);
				layer.open({
					type : 1,
					title : '查看站内信',
					skin : 'layui-layer-rim',
					area : [ '800px', 'auto' ],
					closeBtn : 1,
					content : html
				});
			} else {
				alert("程序异常");
			}
		});
	} else {
		WebMail.OpenSend();
	}
}
