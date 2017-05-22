Department = {}

//部门列表
Department.List = function (postData){
		$("#main-table").jqGrid({
			url : CTX_PATH + "/auth/departments/list",
			postData : postData,
			datatype : "json",
			autowidth:true,
			height:'auto',
			colNames : [ '编号', '名称', '状态', '备注', '操作'],
			colModel : 
				[{name : 'id',index : 'id',width : 55,fixed:true, sortable:false}, 
				 {name : 'name',index : 'name asc, id',width : 100,editable :true,sortable:false},
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
				 {name : 'remarks',index : 'remarks',width : 120,editable :true,sortable:false},
				 {name : 'actions',index : 'actions',width : 80, align:'center',title:false,sortable:false}],
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
			emptyrecords: "暂无任何数据",
			editurl : CTX_PATH+"/auth/departments/update",
			gridComplete : function() {
	            var ids = jQuery("#main-table").jqGrid('getDataIDs');
	            for ( var i = 0; i < ids.length; i++) {
	              var cl = ids[i];
	              be = "<a id=\"td-edit-"+cl+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:Department.OpenEdit('"+ cl + "');\">编辑</a>";
	              de = "<a id=\"td-del-"+cl+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:delRow('"+ cl + "');\">删除</a>";
	              ae = "<a id=\"td-role-"+cl+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:Department.OpenRole('"+ cl + "');\">角色</a>";
	              jQuery("#main-table").jqGrid('setRowData', ids[i],
	                  {
	            	  	actions : be + de + ae
	                  });
	            }
	        }
		});
	
		$("#main-table").jqGrid('navGrid', '#pager', 
			{edit : true,add : true,del : true,search:false,addfunc:Department.OpenAdd,editfunc:Department.OpenUpdate},
			{closeAfterEdit: true,viewPagerButtons: false},
			{url:CTX_PATH+"/auth/departments/add",closeAfterAdd: true,
				beforeSubmit : function(postdata, formid) {
					postdata.id = null;
					return [ true, '' ];
				}
			},
			{url:CTX_PATH+"/auth/departments/delte"}
		);
		
		$("#main-table").jqGrid().setGridParam({'postData':postData});
		$("#main-table").jqGrid().trigger('reloadGrid');
	
}

//打开添加部门
Department.OpenAdd = function(){
	var data = {};
	$.when($.post(CTX_PATH + "/auth/departments/list", {pageNum : 0, pageSize : 0})).done(function(d2){
		var result2 = JSON.parse(d2);
		if(result2.resultCode == '200'){
			data.department = {};
			var treeObj = $.fn.zTree.getZTreeObj("department-tree");
			var nodes=treeObj.getSelectedNodes();
			if(nodes != null && nodes.length > 0){
				data.department.parentId = nodes[0].id;
			}
			data.parentDepartment = result2.data.list;
			var html = template('department_tpl', data);
			layer.open({
				type: 1,
				title: '添加部门',
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

//打开修改部门
Department.OpenEdit = function(departmentId){
	var data = {};
	if(roleId > 0){
		$.when($.post(CTX_PATH + "/auth/departments/get", {departmentId : departmentId}),
			$.post(CTX_PATH + "/auth/departments/list", {pageNum : 0, pageSize : 0})).done(function(d1, d2){
				var result1 = JSON.parse(d1[0]);
				var result2 = JSON.parse(d2[0]);
				var list = [];
				
				//上级部门中去除本身
				for(var i=0;i<result2.data.list.length;i++){
					if(result2.data.list[i].id != result1.data.id){
						list.add(result2.data.list[i]);
					}
				}
				
				if(result1.resultCode == '200' && result2.resultCode == '200'){
					data.department = result1.data;
					data.parentDepartment = list;
					var html = template('department_tpl', data);
					layer.open({
					  type: 1,
					  title: '修改部门',
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
		Department.OpenAdd();
	}
}

//更新部门信息
Department.Update = function (serialize){
	$.post(CTX_PATH + "/auth/departments/update", serialize,
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

//新增部门信息
Department.Add = function (serialize){
	$.post(CTX_PATH + "/auth/departments/add", serialize,
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

//获取全部部门
Department.GetAllList = function (){
	$.post(CTX_PATH + "/auth/departments/list", {
		pageNum : 0, 
		pageSize : 0
	}, function(msg) {
		var result = JSON.parse(msg);
		if(result.resultCode == '200'){
			return result.data;
		} else {
			alert("程序异常");
			return [];
		}
	}).error(function(xhr,errorText,errorType){
		alert("系统错误");
		return [];
	});
}

//打开部门角色窗口
Department.OpenRole = function(departmentId){
	var data = {};
	if(departmentId > 0){
		$.when($.post(CTX_PATH + "/auth/departments/get", {departmentId : departmentId}),
			$.post(CTX_PATH + "/auth/departments/level/list", {departmentId : departmentId, sort : 'desc'})).done(function(d1, d2){
				var result1 = JSON.parse(d1[0]);
				var result2 = JSON.parse(d2[0]);
				if(result1.resultCode == '200' && result2.resultCode == '200'){
					data.department = result1.data;
					data.departmentLevel = result2.data;
					var html = template('roles_tpl', data);
					layer.open({
					  type: 1,
					  title: '设置角色',
					  skin: 'layui-layer-rim', 
					  area: ['520px','auto'], 
					  closeBtn: 1,
					  content:html
					});
				} else {
					alert("程序异常");
				}
		});
	}
}

//保存部门角色
Department.UpdateRole = function(serialize){
	$.post(CTX_PATH + "/auth/departments/roles/update", serialize,
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
