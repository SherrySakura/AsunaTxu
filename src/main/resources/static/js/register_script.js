// JavaScript Document
window.onload = function(){
	
	
	//验证码
	var oGetCodeBtn = document.getElementById('getcheckcode');
	var oCodeSpan = document.getElementById('autoRandom');
	var oRegBtn = document.getElementById('btnReg');
	
 	oGetCodeBtn.onclick=createCode;
	oRegBtn.onclick=check_regist;
		
	function createCode()  
	{  
		var seed = new Array(  
				'abcdefghijklmnopqrstuvwxyz',  
				'ABCDEFGHIJKLMNOPQRSTUVWXYZ',  
				'0123456789'  
		);               
		var idx,i;  
		var result = '';   
		for (i=0; i<4; i++) 
		{  
			idx = Math.floor(Math.random()*3); //获得随机数据的整数部分-获取一个随机整数  
			result += seed[idx].substr(Math.floor(Math.random()*(seed[idx].length)), 1);//根据随机数获取数据中一个值  
		}  
		oCodeSpan.innerHTML=result; //返回随机结果  
	}  

	/*验证并提交ajax*/
	function check_regist(){
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
			document.getElementById('repassword').value='';
			document.getElementById('password').focus();
			return false;
		}
		else if(/.*[\u4e00-\u9fa5]+.*$/.test(password)){
			showModalBox('密码不能含有汉字');
			document.getElementById('password').value='';
			document.getElementById('repassword').value='';
			document.getElementById('password').focus();
			return false;
		}
		
		//3.重复密码
		var repassword = document.getElementById('repassword').value;
		if(repassword!==password){
			showModalBox('两次输入的密码不一致');
			document.getElementById('repassword').focus();
			document.getElementById('password').value='';
			document.getElementById('repassword').value='';
			return false;
			
		}
		
		//4.手机号
		var phone = document.getElementById('phone').value;
		if(!/^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/.test(phone)){
						
			showModalBox('手机号码不合法');
			document.getElementById('phone').focus();
			document.getElementById('phone').value='';
			return false;
			
		}
		// //5.邮箱
		var mail = document.getElementById('mail').value;
		// if(!/^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/.test(mail)){
		//
		// 	showModalBox('邮箱地址不合法');
		// 	document.getElementById('mail').focus();
		// 	document.getElementById('mail').value='';
		// 	return false;
		// }

		var gender = $('input[name="gender"]:checked').val()
		console.log(gender);

		
		var inputRandom=document.getElementById("check_code").value;  
		var autoRandom=document.getElementById("autoRandom").innerHTML;  
		if(!(inputRandom!=''&&(inputRandom.toLowerCase()==autoRandom.toLowerCase()))) {  
			showModalBox('验证码错误');
			document.getElementById('check_code').focus();
			document.getElementById('check_code').value='';
			return false;
		}
		
		var oAgree=document.getElementById('agree');
		if(!oAgree.checked){
			showModalBox("请阅读并同意说明");
		}
		
		/*发送ajax请求*/
		ajax('post','/register','account='+name+'&password='+password+'&phone='+phone+'&mail='+mail+'&gender='+gender,function(data){
			//服务器成功返回
			var obj = JSON.parse(data);
			console.log(obj);
			if(data==''){ //注册失败
				showModalBox('注册失败，用户已存在');
				//gotoLoginPage();
			}else if (obj.code === 407){
                showModalBox(obj.msg);
                var timer = setTimeout(gotoLoginPage,2000);
			}
			else{
				showModalBox('注册成功，2秒后自动跳转到登录页面...');
				var timer = setTimeout(gotoLoginPage,2000);		//执行一次
			}
			
		});	
		
		return true;
	}
	
	
	//js跳转到login页面
	function gotoLoginPage(){
		window.location.href='login.html';
	}
	

	
	/*模态框函数*/
	function showModalBox(info){
		var oModalBox = document.getElementById('modal_info_box');
		oModalBox.innerHTML=info;
		$('#myModal').modal('show');
		//showModalBox('test');
	}
};
