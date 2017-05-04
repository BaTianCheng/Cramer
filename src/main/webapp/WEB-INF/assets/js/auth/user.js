User = {}

//用户列表
User.List = function (){
	$("#main-table").jqGrid({
		url : CTX_PATH + "/auth/users/list",
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '编号', '名称', '密码', '部门', '角色', '状态', '备注', '操作'],
		colModel : 
			[{name : 'id',index : 'id',width : 55,fixed:true}, 
			 {name : 'name',index : 'name asc, id',width : 100}, 
			 {name : 'password',index : 'password',width : 200,editrules:{required:true}}, 
			 {name : 'departmentName',index : 'department_name',width : 80}, 
			 {name : 'roleNames',index : 'role_name',width : 100,
				 formatter:function(cellvalue, options, rowObject){
					 if(cellvalue != null){
						 var temp = '';
						 for(var i=0;i<cellvalue.length;i++){
							 temp += cellvalue[i];
							 if(i <cellvalue.length-1){
								 temp += ',';
							 }
						 }
						 return temp;
					 } else {
						 return '';
					 }
				 }
			 }, 
			 {name : 'status',index : 'status',width : 80,editable :true, align:'center',
				 formatter: function(cellValue, options, rowObject) {  
					switch(cellValue){
						case 1 : return '可用';
						case 2 : return '锁定';
						default	: return cellValue;
					}
				 } ,
			 	 edittype:'select', editoptions:{value:{0:'可用', 1:'不可用'}}
			 },
			 {name : 'remarks',index : 'remarks',width : 120,editable :true},
			 {name : 'actions',index : 'actions',width : 80, align:'center'}],
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
		editurl : CTX_PATH+"/auth/users/update/info",
		gridComplete : function() {
            var ids = jQuery("#main-table").jqGrid('getDataIDs');
            for ( var i = 0; i < ids.length; i++) {
              var cl = ids[i];
              be = "<a id=\"td-edit-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:User.OpenEdit('"+ cl + "');\">编辑</a>";
              de = "<a id=\"td-del-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:delRow('"+cl+"');\">删除</a>";
              jQuery("#main-table").jqGrid('setRowData', ids[i],
                  {
            	  	actions : be + de 
                  });
            }
        }
	});

	$("#main-table").jqGrid('navGrid', '#pager', {
		edit : true,
		add : true,
		del : true,
		search : false,
		addfunc : User.OpenAdd,
		editfunc : User.OpenInfo
	}, {
		closeAfterEdit : true,
		viewPagerButtons : false
	}, {
		url : CTX_PATH+'/auth/users/add',
		recreateForm : true,
		closeAfterAdd : true,
		beforeInitData : function(formid){
			var cols = $("#main-table").jqGrid('getGridParam','colModel');
			cols[1].editable = true;
			cols[2].editable = true;
			return true;
		},
		onClose : function(formid){
			var cols = $("#main-table").jqGrid('getGridParam','colModel');
			cols[1].editable = false;
			cols[2].editable = false;
			return true;
		},
		beforeSubmit : function(postdata, formid) {
			postdata.id = null;
			return [ true, '' ];
		}
	},{
		url : CTX_PATH+'/auth/users/delete'
	});
}

function delRow(cl){
	$("#main-table").jqGrid('delGridRow', cl);
	$("#main-table").trigger("reloadGrid");
}

//打开添加用户
User.OpenAdd = function(){
	$.when($.post(CTX_PATH + "/auth/departments/list", {pageNum : 0, pageSize : 0})).done(function(d2){
		var result2 = JSON.parse(d2);
		if(result2.resultCode == '200'){
			data.user = {};
			data.department = result2.data.list;
			var html = template('user_info_tpl', data);
			layer.open({
				type: 1,
				title: '添加用户',
				skin: 'layui-layer-rim', 
				area: ['480px','auto'], 
				closeBtn: 1,
				content:html
			});
			User.ShowDepartmentRoles($('#user-department').val(),[]);
		} else {
			alert("程序异常");
		}
	});
}

//打开修改用户
User.OpenEdit = function(userId){
	var data = {};
	if(userId > 0){
		$.when($.post(CTX_PATH + "/auth/users/get", {userId : userId}),
			$.post(CTX_PATH + "/auth/departments/list", {pageNum : 0, pageSize : 0})).done(function(d1, d2){
				var result1 = JSON.parse(d1[0]);
				var result2 = JSON.parse(d2[0]);
				if(result1.resultCode == '200' && result2.resultCode == '200'){
					data.user = result1.data;
					data.department = result2.data.list;
					var html = template('user_info_tpl', data);
					layer.open({
					  type: 1,
					  title: '修改用户',
					  skin: 'layui-layer-rim', 
					  area: ['480px','auto'], 
					  closeBtn: 1,
					  content:html
					});
					User.ShowDepartmentRoles($('#user-department').val(),data.user.roleIds);
				} else {
					alert("程序异常");
				}
		});
	} else {
		User.OpenAdd();
	}
}

//显示用户信息中部门的角色
User.ShowDepartmentRoles = function (departmentId, roleIds){
	Role.ListByDepartment(departmentId, function(result){
		var data = {};
		data.role = result;
		data.roleIds = roleIds;
		var html = template('department_role_tpl', data);
		$('#departmentRoles').html(html);
	});
}

//更新用户信息
User.UpdateInfo = function (serialize){
	$.post(CTX_PATH + "/auth/users/update/info", serialize,
		function(msg) {
			var result = JSON.parse(msg);
			if(result.resultCode == '200'){
				console.log(result.data);
			} else {
				alert("程序异常");
			}
	}).error(function(xhr,errorText,errorType){
		alert("系统错误");
	});
}

//修改本人密码
User.UpdatePassword = function (newPassword){
	$.post(CTX_PATH + "/auth/users/update/current/password", {
			newPassword : newPassword
	},function(msg) {
			var result = JSON.parse(msg);
			if(result.resultCode == '200'){
				console.log(result.data);
			} else {
				alert("程序异常");
			}
	}).error(function(xhr,errorText,errorType){
		alert("系统错误");
	});
}

//删除用户
User.Delete = function (userId){
	layer.confirm('确定要删除用户吗?', {icon: 3, title:'提示'}, function(index){
		$.post(CTX_PATH + "/auth/users/delete", {
			userId : userId
		},function(msg) {
			var result = JSON.parse(msg);
			if(result.resultCode == '200'){
				console.log(result.data);
			} else {
				alert("程序异常");
			}
		}).error(function(xhr,errorText,errorType){
			alert("系统错误");
		});
		layer.close(index);
	});

}
