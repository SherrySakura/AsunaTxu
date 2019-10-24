// JavaScript Document	

function ajax(method, url, data, backCallFunc){   //   ajax('get','http://127.0.0.1/ajax-getNews.html,'',func(data){});
		var xhr = null;
		try{
			xhr = new XMLHttpRequest();
		} catch(e){
			
			xhr = new ActiveXObject('Microsoft.XMLHTTP');   //兼容ie6
		}

		if(method == 'get' && data){
			
			url = url + '?' + data +'&' + Math.random();   //配置成动态URL，消除缓存影响
			//url = url + '?' + data;   //配置成动态URL，消除缓存影响
		}
	
	
		xhr.open(method,url,true);

		if(method == 'get'){
			xhr.send();
		}else{          				//post方式
			xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
			xhr.send(data);
		}
		

		xhr.onreadystatechange = function(){
			
			if(xhr.readyState == 4){       //ajax的请求状态
				
				if(xhr.status == 200){
					backCallFunc &&	backCallFunc(xhr.responseText);    //成功接收到后端响应时，利用回调函数返回参数（如果存在）
					//alert(xhr.responseText);
				}else{ 
					alert('出错了,Err:'+xhr.status);	
				}
			}
		}
		
			/*模态框函数*/
		function showModalBox(info){
			var oModalBox = document.getElementById('modal_info_box');
			oModalBox.innerHTML=info;
			$('#myModal').modal('show');
			//showModalBox('test');
		}
}