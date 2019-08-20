<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
		<div class="w3-display-middle w3-container w3-border w3-white">
		<div class="w3-center ">
			<h2>[회원가입]</h2>
		</div>
		<hr>
<form:form commandName="userVO" action="${ pageContext.servletContext.contextPath }/user/join" 
class="w3-padding-large" method="post">
	<form:errors element="div" />
	
		이메일: <input type="text" name="email" id = "email" value="${userVO.email}"
		onblur="checkEmail()">
		<div class="w3-center" id="resultEmail" style="color: red">&nbsp;
		<form:errors path="email" /></div>
           
		비밀번호: <input type="password" name="password">
		<div class="w3-center" style="color: red">&nbsp;<form:errors path="password" /></div>
		
		비밀번호 확인:<input type="password" name = "confirmPassword">
		<div class="w3-center" style="color: red">&nbsp;<form:errors path="confirmPassword" /></div>
		
		닉네임: <input type="text" name="nickname" id="nickname" value="${userVO.nickname}">
		    <a onclick="checkNickname()" class="w3-button w3-teal" style="padding: 3px">중복체크</a>
            <div class="w3-center" style="color: red">&nbsp;<form:errors path="nickname" /></div>
		
           
					
		<div class="w3-center">
			<input type="submit" class="w3-button w3-black" value="가입하기">
			<button class="w3-button w3-black">취소</button>
		</div>
</form:form>
	</div>
	<script>
	function checkNickname(){
		var blank_pattern = /[\s]/g;
		var nickname=$('#nickname').val();
		
		if( blank_pattern.test(nickname) === true){
		    alert('공백은 사용할 수 없습니다.');
		}		
		else if(nickname===''){
			alert('닉네임을 입력하세요.')
		}else{
			$.ajax({
				type:"post",
				url:"${ pageContext.servletContext.contextPath }/user/checkNickname",
				dataType:'json',
				data:{'nickname':$('#nickname').val()},
				success:function(json){
				 if(json.result===true){
					 alert('사용 할 수 있는 닉네임 입니다.');       
					 $('#nickname').attr('class','w3-blue')
				 }else{
					 alert('사용 할 수 없는 닉네임 입니다.');       
					 $('#nickname').attr('class','w3-red')
				 }
	            }
			});
		}
	}
	function checkEmail(){
		var email=$('#email').val();
			$.ajax({
				type:"post",
				url:"${ pageContext.servletContext.contextPath }/user/checkEmail",
				dataType:'json',
				data:{'email':email},
				success:function(json){
					 if(json.result===true){
						 $('#resultEmail').attr('style','color: blue')
						 $('#resultEmail').text('등록되지 않은 이메일 입니다.')
					 }else{
						 $('#resultEmail').attr('style','color: red')
						 $('#resultEmail').text('등록된 이메일 입니다.')
					 }
	    	      //  JSON.parse(JSON.stringify(result));
	            }
			});
	}
	</script>
</body>
