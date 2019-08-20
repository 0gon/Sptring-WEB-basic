<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
  <div class="w3-display-middle w3-container w3-border w3-white">
  <div class="w3-right w3-black w3-button w3-margin" onclick="insertGrade()">등급추가+</div> 
  <c:if test="${count==0}">
   	<table class="w3-table w3-border w3-hoverable w3-center w3-small " style="width:90%">
    	<tr class="w3-light-grey">
    	<td class="w3-center">등급을 추가하세요.</td>
   	</table>
</c:if>
  <c:if test="${count!=0}">
	<div class="w3-center w3-padding">
		<b>등급 목록</b>(총 ${count} 가지 등급)
	</div>
	<table class="w3-table  w3-centered"
		style="width: 100%; border: black;">
		<tr class="w3-black">
			<th class="w3-center" width="25%">No.</th>
			<th class="w3-center" width="25%">등급명</th>
			<th class="w3-center" width="25%">관리</th>
		</tr>
		<c:forEach var="grade" items="${grades }">
			<tr class="w3-hover-white">
				<td class="w3-center" width="25%">${number}</td>
     		    <c:set var="number" value="${number-1}"/>
				<td class="w3-center" width="25%">${grade.name }</td>
				<td class="w3-center" width="25%">
				<button class="w3-button w3-teal" onclick="gradeUpdate(${grade.no})">수정</button>
				<button class="w3-button w3-red" onclick="gradeDelete(${grade.no})">삭제</button>
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
  		location.href="${ pageContext.servletContext.contextPath }/admin/gradeList/"+pageNum;
  	}
  	function gradeUpdate(no){
  		location.href="${ pageContext.servletContext.contextPath }/admin/updateGradeForm/"+no;
  	}
  	function gradeDelete(no){
  		var isTrue = confirm('삭제 하시겠습니까? '+'\n!주의: 해당 등급의 모든 회원이 삭제됩니다.');
		if(isTrue == true){
  			location.href="${ pageContext.servletContext.contextPath }/admin/gradeDelete/"+no;
		}
  	}
  	function insertGrade(){
  		location.href="${ pageContext.servletContext.contextPath }/admin/insertGradeForm";
  	}
  </script>
							
  
</body>
