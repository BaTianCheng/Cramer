Department = {}

//部门列表
Department.List = function (){
	$("#main-table").jqGrid({
		url : CTX_PATH + "/auth/departments/list",
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '编号', '名称', '备注', '操作'],
		colModel : 
			[{name : 'id',index : 'id',width : 55,fixed:true, sortable:false}, 
			 {name : 'name',index : 'name asc, id',width : 100,editable :true,sortable:false},
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
              be = "<a id=\"td-edit-"+cl+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:editRow('"+ cl + "');\">编辑</a>";
              de = "<a id=\"td-del-"+cl+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:delRow('"+ cl + "');\">删除</a>";
              se = "<a id=\"td-save-"+cl+"\" style=\"display:none;margin-left:5px;margin-right:5px;\" href=\"javascript:saveRow('"+ cl + "');\">保存</a>";
              ce = "<a id=\"td-canel-"+cl+"\" style=\"display:none;margin-left:5px;margin-right:5px;\" href=\"javascript:restoreRow('"+ cl + "');\">取消</a>";
              ae = "<a id=\"td-role-"+cl+"\" style=\"margin-left:5px;margin-right:5px;\" href=\"javascript:Department.OpenRole('"+ cl + "');\">角色</a>";
              jQuery("#main-table").jqGrid('setRowData', ids[i],
                  {
            	  	actions : be + de + se + ce + ae
                  });
            }
        }
	});

	$("#main-table").jqGrid('navGrid', '#pager', 
		{edit : true,add : true,del : true,search:false},
		{closeAfterEdit: true,viewPagerButtons: false},
		{url:CTX_PATH+"/auth/departments/add",closeAfterAdd: true,
			beforeSubmit : function(postdata, formid) {
				postdata.id = null;
				return [ true, '' ];
			}
		},
		{url:CTX_PATH+"/auth/departments/delte"}
	);
}

function editRow(cl){
	$("#td-edit-"+cl).hide();
	$("#td-del-"+cl).hide();
	$("#td-save-"+cl).show();
	$("#td-canel-"+cl).show();
	$("#main-table").editRow(cl);
}

function saveRow(cl){
	$("#td-edit-"+cl).show();
	$("#td-del-"+cl).show();
	$("#td-save-"+cl).hide();
	$("#td-canel-"+cl).hide();
	$("#main-table").saveRow(cl);
	$("#main-table").trigger("reloadGrid");
}

function restoreRow(cl){
	$("#td-edit-"+cl).show();
	$("#td-del-"+cl).show();
	$("#td-save-"+cl).hide();
	$("#td-canel-"+cl).hide();
	$("#main-table").restoreRow(cl);
}

function delRow(cl){
	$("#td-edit-"+cl).hide();
	$("#td-del-"+cl).hide();
	$("#td-save-"+cl).show();
	$("#td-canel-"+cl).show();
	$("#main-table").jqGrid('delGridRow', cl);
	$("#main-table").trigger("reloadGrid");
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

