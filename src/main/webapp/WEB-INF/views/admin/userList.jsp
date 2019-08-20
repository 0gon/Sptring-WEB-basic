<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
  <div class="w3-display-middle w3-container w3-border w3-white">
  <c:if test="${count==0}">
   	<table class="w3-table w3-border w3-hoverable w3-center w3-small " style="width:90%">
    	<tr class="w3-light-grey">
    	<td class="w3-center">등록된 상품이 없습니다.</td>
   	</table>
</c:if>
  <c:if test="${count!=0}">
	<div class="w3-center w3-padding">
		<b>회원 목록</b>(총 ${count}명)
	</div>
	<table class="w3-table  w3-centered"
		style="border: black;">
		<tr class="w3-black">
			<th class="w3-center" >가입일</th>
			<th class="w3-center" >이메일</th>
			<th class="w3-center" >비밀번호</th>
			<th class="w3-center" >닉네임</th>
			<th class="w3-center" >등급</th>
			<th class="w3-center" >관리</th>
		</tr>
		<c:forEach var="user" items="${users }">
			<tr class="w3-hover-white">
				<td class="w3-center" >
				<fmt:parseDate var="parsedDate" value="${user.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate  value="${parsedDate}" pattern="yyyy.MM.dd HH:mm"/>
				</td>
				<td class="w3-center" >${user.email }</td>
				<td class="w3-center" >${user.password }</td>
				<td class="w3-center" >${user.nickname }</td>
				<td class="w3-center" >${user.gradeVO.name }</td>
				<td class="w3-center" >
					<button class="w3-button w3-teal" onclick="userUpdate(${user.no},${userVO.no})">수정</button>
					<button class="w3-button w3-red" onclick="userDelete(${user.no})">삭제</button>
				</td>
	     	</tr>
		</c:forEach>
	</table>
	</c:if> 
	
	<!-- Pagination --> 

	<c:if test="${count>0 }">
		<div class="w3-center w3-padding-16">
			<div class="w3-bar">
				<c:if test="${startPage>bottomLine }">
					<a	onclick="movePage(${startPage-bottomLine})"
						class="w3-bar-item w3-button w3-hover-black">«</a>
				</c:if>

				<c:forEach var="i" begin="${startPage }" end="${endPage}">
					<c:if test="${i!=currentPage }">
						<a	onclick="movePage(${i})"
							class="w3-bar-item w3-button w3-hover-black">${i}</a>
					</c:if>
					<c:if test="${i==currentPage }">
						<a  onclick="movePage(${i})"
							class="w3-bar-item w3-black w3-button">${i}</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage<pageCount }">
					<a	onclick="movePage(${startPage+bottomLine})"
						class="w3-bar-item w3-button w3-hover-black">»</a>
				</c:if>
			</div>
		</div>
	</c:if>
	
  </div> 
  <script>
  	function movePage(pageNum){
  		location.href="${ pageContext.servletContext.contextPath }/admin/userList/"+pageNum;
  	}
  	function userUpdate(targetNo,sessionNo){
  		location.href=
  			"${ pageContext.servletContext.contextPath }/admin/updateUserForm/"+targetNo+"/"+sessionNo
  	}
  	function userDelete(no){
  		var isTrue = confirm("탈퇴 시키겠습니까?");
		if(isTrue == true){
  			location.href="${ pageContext.servletContext.contextPath }/admin/deleteUser/"+no;
		}
  	}
  </script>
							
  
</body>
