<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
<div class="w3-display-middle w3-container w3-border w3-white ">
	<div class="w3-padding-large">
		이메일: ${userVO.email }<br>
		비밀번호: ${userVO.password }<br>
		닉네임: ${userVO.nickname }<br>
		등급: ${userVO.grade }<br>
		<div class="w3-padding w3-center">
			<button class="w3-button w3-black" onclick="updateUser()">수정하기</button>
			<button class="w3-button w3-black" onclick="deleteUser(${userVO.no})">탈퇴하기</button>
		</div>
	</div>
</div>
<script>
	function deleteUser(no){
		var isTrue = confirm("탈퇴 하시겠습니까?");
		if(isTrue == true){
		  location.href="${ pageContext.servletContext.contextPath }/user/delete/"+no
		}
	}
	function updateUser(){
		  location.href="${ pageContext.servletContext.contextPath }/user/editForm"
	}
</script>
</body>
