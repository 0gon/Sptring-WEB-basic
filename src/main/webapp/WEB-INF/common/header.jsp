<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lotte Project</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
body {
	background-position: center;
	background-size: 40%;
	background-image: url("<%=request.getContextPath()%>/images/back.jpg");
	background-repeat: no-repeat;
	background-position: 50% 100px ;
}
</style>
</head>
    <div class="w3-top w3-black ">
        <button class="w3-left w3-button w3-padding-large" onclick="home()">음료관리시스템 </button>
       	  <div class="w3-dropdown-hover w3-left">
	      	<button class="w3-button w3-padding-large" onclick="beverage()">음료</button>
		     	 <div class="w3-dropdown-content w3-bar-block w3-card-4">
			        <a href="#" class="w3-bar-item w3-button" onclick="beverage()">상품보기</a>
			        <c:if test="${userVO.grade>1 }">
				        <a href="#" class="w3-bar-item w3-button" onclick="order()">이전 주문내역</a>
			        </c:if>
		     	 </div>
	   	  </div>
	   	  
        
        <div class="w3-bar-item w3-right " >
         <img src="<%= request.getContextPath() %>/images/ldcc_logo.jpg" 
          alt="이미지를표시할수없음" style="height:46px; width:180px;">
         </div>
		<c:if test="${userVO.no==0 || userVO==null }">
	        <div class="w3-bar-item w3-button w3-right w3-padding-large" onclick="join()">회원가입</div>
	        <div class="w3-bar-item w3-button w3-right w3-padding-large" onclick="login()">로그인</div>
		</c:if>
		<c:if test="${userVO.no!=0 && userVO!=null }">
		      <div class="w3-dropdown-hover w3-right">
		      	<button class="w3-button w3-padding-large" style="font-style: oblique;">${userVO.nickname} 님</button>
		     	 <div class="w3-dropdown-content w3-bar-block w3-card-4">
			        <a href="#" class="w3-bar-item w3-button" onclick="edit()">회원정보 수정</a>
			        <a href="#" class="w3-bar-item w3-button" onclick="logout()">로그아웃</a>
		     	 </div>
		   	  </div>
	        <div class="w3-right w3-padding-large">현재 등급: <font color="yellow" >${userVO.gradeVO.name}</font> </div>
	   	  	<c:if test="${userVO.grade==0}">
	   	  	  <div class="w3-dropdown-hover w3-right">
		      	<button class="w3-button w3-padding-large w3-teal">관리자</button>
		     	 <div class="w3-dropdown-content w3-bar-block w3-card-4">
			        <a href="#" class="w3-bar-item w3-button" onclick="addBeverage()">음료등록</a>
			        <a href="#" class="w3-bar-item w3-button" onclick="sellList()">판매관리</a>
			        <a href="#" class="w3-bar-item w3-button" onclick="userList()">회원관리</a>
			        <a href="#" class="w3-bar-item w3-button" onclick="gradeList()">등급관리</a>
		     	 </div>
		   	  </div>
	   	  	</c:if> 
		</c:if>
		      
    </div>
	<script>
		function home(){
			location.href="${ pageContext.servletContext.contextPath }/page/main"
		}
		function userList(){
			location.href="${ pageContext.servletContext.contextPath }/admin/userList/1"
		}
		function addBeverage(){
			location.href="${ pageContext.servletContext.contextPath }/admin/addBeverage"
		}
		function sellList(){
			location.href="${ pageContext.servletContext.contextPath }/admin/sellList/1"
		}
		function gradeList(){
			location.href="${ pageContext.servletContext.contextPath }/admin/gradeList/1"
		}
		function edit(){
			location.href="${ pageContext.servletContext.contextPath }/user/edit"
		}
		function join() {
			location.href = "${ pageContext.servletContext.contextPath }/user/join";
		}
		function login() {
			location.href = "${ pageContext.servletContext.contextPath }/user/login";
		}
		function logout() {
			location.href = "${ pageContext.servletContext.contextPath }/user/logout";
		}
		function beverage() {
			location.href = "${ pageContext.servletContext.contextPath }/page/beverageList/1";
		}
		function order() {
			location.href = "${ pageContext.servletContext.contextPath }/page/orderList/1";
		}
	</script>