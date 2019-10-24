// JavaScript Document
window.onload = function(){
	
	var oRegBtn = document.getElementById('btnReg');
	var oAForget = document.getElementById('forget');
	var oRemenber = document.getElementById('remenber');
	var oName = document.getElementById('name');
	var oPassword = document.getElementById('password');
	oRegBtn.onclick=check_login;
	oAForget.onclick=function(){
		
		showModalBox("找回密码功能尚不支持.");
	}
	
	//页面初始化时，如果帐号密码cookie存在则填充
    if(getCookie('account_name') && getCookie('account_pswd')){
      	oName.value = getCookie('account_name');
      	oPassword.value = getCookie('account_pswd');
		oRemenber.checked=true;
    }
	
	
	
	/*检查并提交登录表单*/
    function check_login(){
		//1.用户名
		var name = document.getElementById('name').value;
		
		if(/.*[ ]+.*/.test(name)){
			showModalBox('用户名不能包含空字符');
			document.getElementById('name').focus();
			return false;
		}
		else if(name.length>20||name.length<5){
			showModalBox('用户名的长度应为5-20位');
			document.getElementById('name').value='';
			document.getElementById("name").focus();
			return false;
		}
		else if(/.*[\u4e00-\u9fa5]+.*$/.test(name)){
			showModalBox('用户名不能含有汉字');
			document.getElementById('name').focus();
			return false;
		}
		
		

		
		//2.密码
		var password = document.getElementById('password').value;
		if(/.*[ ]+.*/.test(password)){
			showModalBox('密码不能包含空字符');
			document.getElementById('password').value='';
			document.getElementById('repassword').value='';
			document.getElementById('password').focus();
			return false;
		}
		else if(password.length>20||password.length<5){
			showModalBox(password.length+'密码的长度应为5-20位');
			document.getElementById('password').value='';
			document.getElementById('password').focus();
			return false;
		}
		else if(/.*[\u4e00-\u9fa5]+.*$/.test(password)){
			showModalBox('密码不能含有汉字');
			document.getElementById('password').focus();
			return false;
		}
		
		//表单格式正确。发送ajax请求

		if(oRemenber.checked){ //如果选择记住密码，则添加cookie,期限1天
			
			setCookie('account_name',oName.value,1); //保存帐号到cookie，有效期7天
			setCookie('account_pswd',oPassword.value,1); //保存密码到cookie，有效期7天

			//showModalBox('保存');
		}
		else{                 //否则清除cookie
			
			deleteCookie('account_name');
			deleteCookie('account_pswd');
			//showModalBox('清除');
		}

		//gotoIndexPage();		//执行一次
		
		/*发送ajax请求*/
		ajax('post','/login','name='+name+'&password='+password,function(data){
			//服务器成功返回
			
			if(data==''){ //登录失败
				showModalBox('登录失败，用户名不存在');
			}
			else{		//登录成功
				//插入cookie
				var accountData = JSON.parse(data);
				console.log(accountData);
				switch (accountData.code) {
					case 105:
						showModalBox(accountData.msg);
						break;
					case 106:
						showModalBox(accountData.msg);
						break;
					case 200:
						setCookie('account_name',oName.value,1); //保存帐号到cookie，有效期7天
						setCookie("uuid", accountData.data.id,1);
						gotoIndexPage();
						break;
				}

			}
			
		});	
		
		return true;
	}
		
	//js跳转到index页面
	function gotoIndexPage(){
		window.location.href='index.html';
	}
	
	/*cookie操作*/
	//1.设置cookie
	function setCookie(cname,cvalue,exdays)
	{
	  var d = new Date();
	  d.setTime(d.getTime()+(exdays*1000*60*60));
	  var expires = "expires="+d.toGMTString();
	  document.cookie = cname + "=" + cvalue + "; " + expires;
	}
	//2.获取cookie
	function getCookie(cname)
	{
	  var name = cname + "=";
	  var ca = document.cookie.split(';');
	  for(var i=0; i<ca.length; i++) 
	  {
		var c = ca[i].trim();
		if (c.indexOf(name)==0) return c.substring(name.length,c.length);
	  }
	  return "";
	}
	
	//3.清除指定cookie
	  function deleteCookie(cname){
		setCookie(cname,null,-1);
	  }

	
	
	/*模态框函数*/
	function showModalBox(info){
		var oModalBox = document.getElementById('modal_info_box');
		oModalBox.innerHTML=info;
		$('#myModal').modal('show');
		//showModalBox('test');
	}
};
