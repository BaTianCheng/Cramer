User = {}

//用户列表
User.List = function (){
	var ii = layer.load();
	$.post(CTX_PATH + "/auth/users/list", {
		pageSize : $('#page-size').val(),
		pageNum : $('#page-num').val()
	}, function(msg) {
		var result = JSON.parse(msg);
		if(result.resultCode == '200'){
			if (result.data.total > 0) {
				var html = template('user_list_tpl', result.data);
				document.getElementById('table-content').innerHTML = html;
	
				$('#pager').jqPaginator({
					totalPages : result.data.pages,
					visiblePages : result.data.pageSize,
					currentPage : result.data.pageNum,
					onPageChange : function(num, type) {
						if (num != $('#page-num').val()) {
							$('#page-num').val(num);
							User.List();
						}
					}});
			}
		} else {
			alert("程序异常");
		}
		layer.close(ii);
	}).error(function(xhr,errorText,errorType){
		layer.close(ii);
		alert("系统错误");
	});
	
}

//角色列表
//Role.List = function (postData){
//	$("#main-table").jqGrid({
//		url : CTX_PATH + "/auth/roles/list",
//		postData : postData,
//		datatype : "json",
//		autowidth:true,
//		height:'auto',
//		colNames : [ '编号', '名称', '部门', '备注', '操作'],
//		colModel : 
//			[{name : 'id',index : 'id',width : 55,fixed:true}, 
//			 {name : 'name',index : 'name',width : 100,editable :true}, 
//			 {name : 'departmentName',index : 'departmentName',width : 80,editable :true},
//			 {name : 'remarks',index : 'remarks',width : 120,editable :true},
//			 {name : 'actions',width : 80, align:'center', title:false,sortable:false}],
//		jsonReader : {   
//			id: "id",
//			root: "data.list",
//		    page: "data.pageNum",
//		    total: "data.pages",
//		    records: "data.total",
//		    repeatitems: true
//		},
//		prmNames : {
//			page:"pageNum", // 表示请求页码的参数名称
//			rows:"pageSize", // 表示请求行数的参数名称
//			sort: "sortId", // 表示用于排序的列名的参数名称
//			order: "sortType", // 表示采用的排序方式的参数名称
//			search:"_search", // 表示是否是搜索请求的参数名称
//			nd:"nd", // 表示已经发送请求的次数的参数名称
//			id:"id", // 表示当在编辑数据模块中发送数据时，使用的id的名称
//			oper:"oper", // operation参数名称
//			editoper:"edit", // 当在edit模式中提交数据时，操作的名称
//			addoper:"add", // 当在add模式中提交数据时，操作的名称
//			deloper:"del", // 当在delete模式中提交数据时，操作的名称
//			subgridid:"id", // 当点击以载入数据到子表时，传递的数据名称
//			npage: null,
//			totalrows:"totalrows" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
//		},
//		rowNum : 10,
//		rowList : [ 10, 20, 30 ],
//		pager : '#pager',
//		mtype : "post",
//		viewrecords : true,
//		emptyrecords: "暂无任何数据",
//		editurl : CTX_PATH+"/auth/roles/update",
//		gridComplete : function() {
//            var ids = jQuery("#main-table").jqGrid('getDataIDs');
//            for ( var i = 0; i < ids.length; i++) {
//              var cl = ids[i];
//              be = "<a id=\"td-edit-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:editRow('"+ cl + "');\">编辑</a>";
//              de = "<a id=\"td-del-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:delRow('"+ cl + "');\">删除</a>";
//              se = "<a id=\"td-save-"+cl+"\" style=\"display:none;padding-left:5px;padding-right:5px;\" href=\"javascript:saveRow('"+ cl + "');\">保存</a>";
//              ce = "<a id=\"td-canel-"+cl+"\" style=\"display:none;padding-left:5px;padding-right:5px;\" href=\"javascript:restoreRow('"+ cl + "');\">取消</a>";
//              ae = "<a id=\"td-authority-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:Role.OpenAuthority('"+ cl + "');\">权限</a>";
//              jQuery("#main-table").jqGrid('setRowData', ids[i],
//                  {
//            	  	actions : be + de + se + ce + ae
//                  });
//            }
//        }
//	});
//
//	$("#main-table").jqGrid('navGrid', '#pager', 
//		{edit : true,add : true,del : true,search:false},
//		{closeAfterEdit: true,viewPagerButtons: false},
//		{url:CTX_PATH+"/auth/roles/add",closeAfterAdd: true,
//			beforeSubmit : function(postdata, formid) {
//				postdata.id = null;
//				return [ true, '' ];
//			}
//		},
//		{url:CTX_PATH+"/auth/roles/delte"}
//	);
//	
//	$("#main-table").jqGrid().setGridParam({'postData':postData});
//	$("#main-table").jqGrid().trigger('reloadGrid');
//}


