<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
<div class="w3-display-middle w3-container w3-border w3-white">
	<div class="w3-padding-large">
	<form method="post" 
	action="${ pageContext.servletContext.contextPath }/admin/updateUserForm/${userVO.no}">
	 <input type="hidden" name="no" value="${targetVO.no }">
		이메일: <input type="text" name="email" value="${targetVO.email }"><p>
		닉네임: <input type="text" name="nickname" value="${targetVO.nickname }"><p>
		비밀번호: <input type="text" name="password" value="${targetVO.password }"><p>
		현재 등급: ${targetVO.gradeVO.name}<p>
		등급 수정:
				<select name = "grade">
					<c:forEach var="grade" items="${grades }">
			        <option value="${grade.no}">${grade.name }</option>
			        </c:forEach>
			    </select><p>
			    
		
		<div class="w3-center w3-padding">
			<input class="w3-black w3-button" type="submit" value="수정">
		</div>
	</form>
	</div>
</div>
</body>
