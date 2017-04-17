User = {}

//用户列表
User.List = function (){
	var ii = layer.load();
	$.post(CTX_PATH + "/auth/users/list", {
		pageSize : $('#page-size').val(),
		pageNum : $('#page-num').val()
	}, function(msg) {
		var result = JSON.parse(msg);
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
				}
			});
		}
		layer.close(ii);
	}).error(function(xhr,errorText,errorType){
		layer.close(ii);
		alert("系统错误");
	});
}