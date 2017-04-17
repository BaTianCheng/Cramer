Auth = {}

//验证登录
Auth.CheckLogin = function(userName, passWord){
	$.post('/cramer/login',
		{
			userName:userName,
			passWord:passWord
		},
		function(result){
			var code = JSON.parse(result).resultCode;
			if(code == "200"){
				location.href='/cramer/index';
			} else {
				alert('Login fail!');
			}
		}
	);
}
