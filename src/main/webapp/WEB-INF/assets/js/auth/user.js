User = {}

//用户列表
User.List = function (){
	$("#main-table").jqGrid({
		url : CTX_PATH + "/auth/users/list",
		datatype : "json",
		autowidth:true,
		height:'auto',
		colNames : [ '编号', '名称', '密码', '状态', '备注', '操作'],
		colModel : 
			[{name : 'id',index : 'id',width : 55,fixed:true}, 
			 {name : 'name',index : 'name asc, id',width : 100,editable :true,editoptions:{readonly:true}}, 
			 {name : 'password',index : 'password',width : 200}, 
			 {name : 'status',index : 'status',width : 80,editable :true, align:'center',
				 formatter: function(cellValue, options, rowObject) {  
					switch(cellValue){
						case 0 : return '可用';
						case 1 : return '不可用';
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
              be = "<a id=\"td-edit-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:editRow('"+ cl + "');\">编辑</a>";
              de = "<a id=\"td-del-"+cl+"\" style=\"padding-left:5px;padding-right:5px;\" href=\"javascript:delRow('"+ cl + "');\">删除</a>";
              se = "<a id=\"td-save-"+cl+"\" style=\"display:none;padding-left:5px;padding-right:5px;\" href=\"javascript:saveRow('"+ cl + "');\">保存</a>";
              ce = "<a id=\"td-canel-"+cl+"\" style=\"display:none;padding-left:5px;padding-right:5px;\" href=\"javascript:restoreRow('"+ cl + "');\">取消</a>";
              jQuery("#main-table").jqGrid('setRowData', ids[i],
                  {
            	  	actions : be + de + se + ce
                  });
            }
        }
	});

	$("#main-table").jqGrid('navGrid', '#pager', 
		{edit : true,add : true,del : true,search:false},
		{closeAfterEdit: true,closeAfterAdd: true,viewPagerButtons: false},
		{beforeShowForm:function(frm){
				frm.find("input[name='name']").attr('readonly',false);}
		}
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

//获得用户
User.Get = function (userId){
	$.post(CTX_PATH + "/auth/users/get", {
		userId : userId
	}, function(msg) {
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

