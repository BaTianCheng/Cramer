Auth = {}

//验证登录
Auth.CheckLogin = function(userName, passWord){
	$.post('/cramer/login',
		{
			userName:userName,
			passWord:passWord
		},
		function(result){
			if(result == "1"){
				location.href='/cramer/';
			} else {
				alert('Login fail!');
			}
		}
	);
}
