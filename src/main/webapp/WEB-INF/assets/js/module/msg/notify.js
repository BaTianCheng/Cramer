Notify = {}

//通知公告列表
Notify.List = function (postData){
	$("#main-table").jqGrid({
		url : CTX_PATH + "/auth/roles/list",
		postData : postData,
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '编号', '名称', '部门', '状态', '备注', '操作'],
		colModel : 
			[{name : 'id',index : 'id',width : 55,fixed:true}, 
			 {name : 'name',index : 'name',width : 100,editable :true}, 
			 {name : 'departmentName',index : 'departmentName',width : 80,editable :true},
			 {name : 'status',index : 'user.status',width : 80,editable :true, align:'center',
				 formatter: function(cellValue, options, rowObject) {  
					switch(cellValue){
						case -1 : return '已删除';
						case 0 : return '不可用';
						case 1 : return '可用';
						case 2 : return '锁定';
						default	: return cellValue;
					}
				 } ,
			 	 edittype:'select', editoptions:{value:{0:'不可用', 1:'可用', 2:'锁定'}}
			 },
			 {name : 'remarks',index : 'remarks',width : 120,editable :true},
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
              be = "<a id=\"td-edit-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:Role.OpenEdit('"+ cl + "');\">编辑</a>";
              de = "<a id=\"td-del-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:delRow('"+ cl + "');\">删除</a>";
              ae = "<a id=\"td-authority-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:Role.OpenAuthority('"+ cl + "');\">查看</a>";
              jQuery("#main-table").jqGrid('setRowData', ids[i],
                  {
            	  	actions : be + de + ae
                  });
            }
        }
	});

	$("#main-table").jqGrid('navGrid', '#pager', 
		{edit : true,add : true,del : true,search:false,addfunc:Role.OpenAdd,editfunc:Role.OpenUpdate},
		{closeAfterEdit: true,viewPagerButtons: false},
		{url:CTX_PATH+"/msg/notifys/add",closeAfterAdd: true,
			beforeSubmit : function(postdata, formid) {
				postdata.id = null;
				return [ true, '' ];
			}
		},
		{url:CTX_PATH+"/msg/notifys/delete",
			onclickSubmit : function(params, postdata) {
				return {notifyId : postdata};
			}
		}
	);
	
	$("#main-table").jqGrid().setGridParam({'postData':postData});
	$("#main-table").jqGrid().trigger('reloadGrid');
}

//打开添加通知公告
Notify.OpenAdd = function(){
	layer.open({
		type: 1,
		title: '添加通知公告',
		skin: 'layui-layer-rim', 
		area: ['800px','auto'], 
		closeBtn: 1,
		content:html
	});
}

//打开修改通知公告
Notify.OpenEdit = function(notifyId){
	var data = {};
	if(id > 0){
		$.when($.post(CTX_PATH + "/msg/notifys/get", {notifyId : notifyId})).done(function(d1){
				var result1 = JSON.parse(d1[0]);
				if(result1.resultCode == '200' && result2.resultCode == '200'){
					data = result1.data;
					var html = template('notify_tpl', data);
					layer.open({
					  type: 1,
					  title: '修改通知公告',
					  skin: 'layui-layer-rim', 
					  area: ['800px','auto'], 
					  closeBtn: 1,
					  content:html
					});
				} else {
					alert("程序异常");
				}
		});
	} else {
		Notify.OpenAdd();
	}
}

//更新通知公告
Notify.Update = function (serialize){
	$.post(CTX_PATH + "/msg/notifys/update", serialize,
		function(msg) {
			var result = JSON.parse(msg);
			if(result.resultCode == '200'){
				layer.close(layer.index);
				layer.msg('修改成功', {icon: 1});
				$("#main-table").trigger("reloadGrid");
			} else {
				layer.msg('程序异常', {icon: 2});
			}
	}).error(function(xhr,errorText,errorType){
		layer.msg('系统错误', {icon: 2});
	});
}

//新增通知公告
Notify.Add = function (serialize){
	$.post(CTX_PATH + "/msg/notifys/add", serialize,
		function(msg) {
			var result = JSON.parse(msg);
			if(result.resultCode == '200'){
				layer.close(layer.index);
				layer.msg('添加成功', {icon: 1});
				$("#main-table").trigger("reloadGrid");
			} else {
				layer.msg('程序异常', {icon: 2});
			}
	}).error(function(xhr,errorText,errorType){
		layer.msg('系统错误', {icon: 2});
	});
}
