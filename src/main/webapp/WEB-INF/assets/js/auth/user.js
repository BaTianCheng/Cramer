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

