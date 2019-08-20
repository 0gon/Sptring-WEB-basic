<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body>
<div class="w3-display-middle w3-container w3-border w3-white">
	<div class="w3-center w3-padding">
		<h2>[로그인]</h2>
	</div>
	<hr>
	
<form action="${ pageContext.servletContext.contextPath }/user/login" class="w3-padding-large" method="post">
	이메일: <input type="text" name="email" id="email">
        <div class="w3-center" style="color: red">&nbsp;</div>
                    
	비밀번호: <input type="password" name="password">
		   <div class="w3-center" style="color: red">&nbsp;</div>
				
	<div class="w3-center">
		<input type="submit" class="w3-button w3-black" value="로그인">
	</div>
</form>


	<div class="w3-center w3-padding">
		<button class="w3-button w3-black" onclick="join()">회원가입</button>
		<a href="${apiURL}">
		 <img height="40" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
	</div>

	<script>
		function join() {
			location.href = "${ pageContext.servletContext.contextPath }/user/join";
		};

		var isLog=${isLog};
		if(isLog===false){
			alert("로그인 후 이용하세요.")			
		};

		
		
	</script>
</div>
</body>
