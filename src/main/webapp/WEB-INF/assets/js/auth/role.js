Role = {}

//角色列表
Role.List = function (postData){
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
		editurl : CTX_PATH+"/auth/roles/update",
		gridComplete : function() {
            var ids = jQuery("#main-table").jqGrid('getDataIDs');
            for ( var i = 0; i < ids.length; i++) {
              var cl = ids[i];
              be = "<a id=\"td-edit-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:Role.OpenEdit('"+ cl + "');\">编辑</a>";
              de = "<a id=\"td-del-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:delRow('"+ cl + "');\">删除</a>";
              ae = "<a id=\"td-authority-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:Role.OpenAuthority('"+ cl + "');\">权限</a>";
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
		{url:CTX_PATH+"/auth/roles/add",closeAfterAdd: true,
			beforeSubmit : function(postdata, formid) {
				postdata.id = null;
				return [ true, '' ];
			}
		},
		{url:CTX_PATH+"/auth/roles/delete"}
	);
	
	$("#main-table").jqGrid().setGridParam({'postData':postData});
	$("#main-table").jqGrid().trigger('reloadGrid');
}

//打开添加角色
Role.OpenAdd = function(){
	var data = {};
	$.when($.post(CTX_PATH + "/auth/departments/list", {pageNum : 0, pageSize : 0})).done(function(d2){
		var result2 = JSON.parse(d2);
		if(result2.resultCode == '200'){
			data.role = {};
			var treeObj = $.fn.zTree.getZTreeObj("department-tree");
			var nodes=treeObj.getSelectedNodes();
			if(nodes != null && nodes.length > 0){
				data.role.departmentId = nodes[0].id;
			}
			data.department = result2.data.list;
			var html = template('role_tpl', data);
			layer.open({
				type: 1,
				title: '添加角色',
				skin: 'layui-layer-rim', 
				area: ['480px','auto'], 
				closeBtn: 1,
				content:html
			});
		} else {
			alert("程序异常");
		}
	});
}

//打开修改角色
Role.OpenEdit = function(roleId){
	var data = {};
	if(roleId > 0){
		$.when($.post(CTX_PATH + "/auth/roles/get", {roleId : roleId}),
			$.post(CTX_PATH + "/auth/departments/list", {pageNum : 0, pageSize : 0})).done(function(d1, d2){
				var result1 = JSON.parse(d1[0]);
				var result2 = JSON.parse(d2[0]);
				if(result1.resultCode == '200' && result2.resultCode == '200'){
					data.role = result1.data;
					data.department = result2.data.list;
					var html = template('role_tpl', data);
					layer.open({
					  type: 1,
					  title: '修改角色',
					  skin: 'layui-layer-rim', 
					  area: ['480px','auto'], 
					  closeBtn: 1,
					  content:html
					});
				} else {
					alert("程序异常");
				}
		});
	} else {
		Role.OpenAdd();
	}
}

//更新用户信息
Role.Update = function (serialize){
	$.post(CTX_PATH + "/auth/roles/update", serialize,
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

//新增用户信息
Role.Add = function (serialize){
	$.post(CTX_PATH + "/auth/roles/add", serialize,
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

//打开角色权限窗口
Role.OpenAuthority = function(roleId){
	var data = {};
	if(roleId > 0){
		$.when($.post(CTX_PATH + "/auth/roles/get", {roleId : roleId}),
			$.post(CTX_PATH + "/auth/authorities/list", {})).done(function(d1, d2){
				var result1 = JSON.parse(d1[0]);
				var result2 = JSON.parse(d2[0]);
				if(result1.resultCode == '200' && result2.resultCode == '200'){
					data.role = result1.data;
					data.authority = result2.data;
					var html = template('authorities_tpl', data);
					layer.open({
					  type: 1,
					  title: '设置权限',
					  skin: 'layui-layer-rim', 
					  area: ['480px','auto'], 
					  closeBtn: 1,
					  content:html
					});
				} else {
					alert("程序异常");
				}
		});
	}
}

//根据部门获取角色列表
Role.ListByDepartment = function (departmentId, callback){
	$.post(CTX_PATH + "/auth/departments/roles/list", {
		departmentId : departmentId
	},function(msg) {
			var result = JSON.parse(msg);
			if(result.resultCode == '200'){
				callback(result.data);
			} else {
				alert("程序异常");
				callback(null);
			}
	}).error(function(xhr,errorText,errorType){
		alert("系统错误");
		callback(null);
	});
}

//保存角色权限
Role.UpdateAuthority = function(serialize){
	$.post(CTX_PATH + "/auth/roles/authorities/update", serialize,
		function(msg) {
			var result = JSON.parse(msg);
			if(result.resultCode == '200'){
				layer.close(layer.index);
				layer.msg('修改成功', {icon: 1});
			} else {
				layer.msg('程序异常', {icon: 2});
			}
	}).error(function(xhr,errorText,errorType){
		layer.msg('系统错误', {icon: 2});
	});
}
