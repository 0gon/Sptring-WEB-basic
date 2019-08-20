<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
<div class="w3-display-middle w3-container w3-border w3-white">
	<div class="w3-padding-large">
	<form method="post" action="${ pageContext.servletContext.contextPath }/user/editForm">
		이메일: <input type="text" name="email" disabled="disabled" value="${userVO.email }"><br>
		닉네임: <input type="text" name="nickname" value="${userVO.nickname }"><br>
		비밀번호: <input type="text" name="password" value="${userVO.password }"><br>
		<div class="w3-center w3-padding">
			<input class="w3-black w3-button" type="submit" value="수정">
		</div>
	</form>
	</div>
</div>
</body>
