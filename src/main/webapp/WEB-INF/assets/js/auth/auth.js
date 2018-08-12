Auth = {}

//验证登录
Auth.CheckLogin = function(userName, passWord){
	$.post(CTX_PATH+'/login',
		{
			userName:userName,
			passWord:passWord
		},
		function(result){
			var code = JSON.parse(result).resultCode;
			if(code == "200"){
				var str = '';
				var data = JSON.parse(result).data;

				if(data !=null){
					//载入权限
					if(data.auth != null){
						for(var i=0;i<data.auth.length;i++){
							str+='['+data.auth[i]+']';
						}
						sessionStorage.setItem('auth', str);
					}
					sessionStorage.setItem('username', data.userName);
					
					//载入系统参数
					if(data.params != null){
						sessionStorage.setItem('ESB_PATH', data.params.ESB_PATH);
					}
				}

				location.href=CTX_PATH+'/index';
			} else {
				alert('Login fail!');
			}
		}
	);
}

//获取权限
Auth.GetAuthorities = function(){
	$.post(CTX_PATH+'/auth/authorities/current',
		{},
		function(result){
			var code = JSON.parse(result).resultCode;
			if(code == "200"){
				var str = '';
				var data = JSON.parse(result).data;
				if(data !=null){
					for(var i=0;i<data.length;i++){
						str+='['+data[i]+']';
					}
				}
				sessionStorage.setItem('auth', str);
			} else {
				sessionStorage.clear();
			}
		}
	);
}
