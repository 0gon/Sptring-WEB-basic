<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
<div class="w3-display-middle w3-container w3-border w3-white">
	<div class="w3-padding-large">
	<form method="post" action="${ pageContext.servletContext.contextPath }/admin/insertGradeForm">
		등급명: <input type="text" name="name" value="${gradeVO.name }"><br>
		<div class="w3-center w3-padding">
			<input class="w3-black w3-button" type="submit" value="등급추가">
		</div>
	</form>
	</div>
</div>
</body>
