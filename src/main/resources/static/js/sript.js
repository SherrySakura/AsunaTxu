// JavaScript Document

window.onload = function () {

	
		//1.网页加载后先检查cookie，若有账号和密码则进入已登录状态
		upDateLoginStatus();
		function upDateLoginStatus(){  //根据cookie中是否有用户名来更新登录、注册界面
			var ologin_span = document.getElementById('login_register');  //登录&注册span
			var oAccountBox = document.getElementById('myAccount');  		  //我的账号Div
			var oUserName = document.getElementById('user_name');
			

			var oLoginPhoneBox = document.getElementById('login_phone');  //手机端登录
			var oRegisterPhoneBox = document.getElementById('register_phone');  //手机端注册
			var oAccountPhoneBox = document.getElementById('account_phone');  //手机端我的账号
			var oQuitPhoneBox = document.getElementById('quit_phone');			 //手机端注销
			if(getCookie('account_name')){
				ologin_span.style.display="none";
				oAccountBox.style.cssText="display:inline-block;";
				oUserName.innerHTML=getCookie('account_name');



				oLoginPhoneBox.style.display="none";
				oRegisterPhoneBox.style.display="none";
				oAccountPhoneBox.style.cssText="display:block;";
				oQuitPhoneBox.style.cssText="display:block;";
			}
			else{

				//showModalBox("未检查到cookie");
				ologin_span.style.cssText="display:inline-block;";
				oAccountBox.style.display="none";
				
				oLoginPhoneBox.style.cssText="display:block;";
				oRegisterPhoneBox.style.cssText="display:block;";
				oAccountPhoneBox.style.display="none";
				oQuitPhoneBox.style.display="none";
				var log =
                    "<ol>" +
                        "<li>" +
                            "<span style='color: #ff3f77'>[重要更新]</span> 完善用户后台管理控制台，新增等级系统，详见我的账户管理页面" +
                        "</li>" +
                        "<li>" +
                            "<span style='color: #52cd00'>[常规更新]</span> 修复文本处理行尾标点没有空格的bug，修复账号注册是可能出现的bug" +
                        "</li>" +
                        "<li>" +
                            "<span style='color: #52cd00'>[常规更新]</span> 修复任务摘要页面多余的图表，优化显示方案" +
                        "</li>" +
                        "<li>" +
                            "<span style='color: #a396f5'>目前所有功能免费开放</span>" +
                        "</li>" +
                        "<li>" +
                            "<span style='color: #52cd00'>[bug反馈邮箱]</span> 854300805@qq.com" +
                        "</li>" +
                    "</ol>";
				showModalBox(log);
			}
		}
		
	//2.注销，清除保留的Cookie，并刷新登录状态界面
	
	var oQuit = document.getElementById('quit');  //登录&注册span
	var oQuitPhoneBox = document.getElementById('quit_phone');			 //手机端注销
	oQuit.onclick = quit_account;
	oQuitPhoneBox.onclick = quit_account;
	
	function quit_account(){
		deleteCookie('name');
		deleteCookie('password');
		upDateLoginStatus();
	}
	//动态样式
	
	var getmore = document.getElementById('get_more');
	//收缩内容
	var accord_cont1 = document.getElementById('accord_cont1');
	var accord_cont2 = document.getElementById('accord_cont2');
	var accord_cont3 = document.getElementById('accord_cont3');
	var accord_cont4 = document.getElementById('accord_cont4');
	var h3_cont1 = document.getElementById('h3_cont1');
	var h3_cont2 = document.getElementById('h3_cont2');
	var h3_cont3 = document.getElementById('h3_cont3');
	var h3_cont4 = document.getElementById('h3_cont4');
	var accord_cont = new Array(accord_cont1,accord_cont2,accord_cont3,accord_cont4);
	var h3_cont = new Array(h3_cont1,h3_cont2,h3_cont3,h3_cont4);
	
	var aProductImg=document.getElementsByClassName('real_product_img');

	//给每个元素添加自定义属性
	//alert(!true);
	for(var i=0;i<accord_cont.length;i++){
		accord_cont.displayFlag=false;
	}
	for(i=0;i<h3_cont.length;i++){
		h3_cont[i].num=i;
	}
	accord_cont[0].displayFlag=true;
	upsetDisplay(accord_cont);
	
	//根据displayFlagArr来更新显示状态
	function upsetDisplay(obj){
		for(var i=0;i<obj.length;i++){
			if(obj[i].displayFlag===true){
				obj[i].style.display="block";
				h3_cont[i].firstChild.style.background="url(img/acminus.png) no-repeat left top";
			}
			else{
				obj[i].style.display="none";
				h3_cont[i].firstChild.style.background="url(img/acplus.png) no-repeat left top";
			}
		}
	}
	//某个标题点击后更新其子内容的状态，并更新显示模式
	function change_displayFlag(){	
		accord_cont[this.num].displayFlag=!accord_cont[this.num].displayFlag;
		upsetDisplay(accord_cont);
	}
	
	//添加点击事件
	for(i=0;i<accord_cont.length;i++){
		h3_cont[i].onclick = change_displayFlag;
	}

	
	
	//显示更多，隐藏
	getmore.show_flag=false;
	var extend_info1 = document.getElementById('extend_info1');
	extend_info1.style.display="none";
	getmore.onclick = function(){
		if(getmore.show_flag===false){
			extend_info1.style.display="inline";
			getmore.innerHTML="[隐藏]";
			getmore.show_flag=true;
		}
		else{
			extend_info1.style.display="none";
			getmore.innerHTML="[显示更多]";
			getmore.show_flag=false;
		}
		
	};
	
	
	//图片悬浮变透明
	function ImageOverOpacity(){
		
		this.style.opacity='0.6';
	}	
	function ImageOutOpacity(){
		
		this.style.opacity='1';
	}	
	for(i=0;i<aProductImg.length;i++){
		aProductImg[i].onmouseover =ImageOverOpacity;
		aProductImg[i].onmouseout =ImageOutOpacity;	
		}
	

	
	
	
	
		/*cookie操作*/
	//1.设置cookie
	function setCookie(cname,cvalue,exdays)
	{
	  var d = new Date();
	  d.setTime(d.getTime()+(exdays*24*60*60*1000));
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