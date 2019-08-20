<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
  <div class="w3-display-middle w3-container w3-border w3-white">
    	<form id="mail" method="post" action="${pageContext.request.contextPath}/admin/mailSending">
    	</form>
  <c:if test="${count==0}">
   	<table class="w3-table w3-border w3-hoverable w3-center w3-small " style="width:90%">
    	<tr class="w3-light-grey">
    	<td class="w3-center">판매내역이 없습니다.</td>
   	</table>
</c:if>
  <c:if test="${count!=0}">
	<div class="w3-center w3-padding">
		<b>판매 목록</b>(총 ${count} 건)
	</div>
	<table class="w3-table  w3-centered"
		style="border: black;">
		<tr class="w3-black">
			<th class="w3-center" >이미지</th>
			<th class="w3-center" >상품명</th>
			<th class="w3-center" >가격</th>
			<th class="w3-center" >판매량</th>
			<th class="w3-center" >남은재고량</th>
			<th class="w3-center" >판매여부</th>
			<th class="w3-center" >관리</th>
		</tr>
		<c:forEach var="beverage" items="${beverages }">
			<tr class="w3-hover-white">
				<td class="w3-center" >
				 <img src="<%= request.getContextPath() %>/fileSave/${beverage.fileName}" 
          class="w3-border" alt="이미지를표시할수없음" style="height:50px; width:50px;"></td>
				<td class="w3-center" >${beverage.name }</td>
				<td class="w3-center" >${beverage.price } 원</td>
				<td class="w3-center" >${beverage.sellCount } 개</td>
				<td class="w3-center" >${beverage.count } 개</td>
				
				  <c:if test="${beverage.state==0}">
					<td class="w3-center" ><font color="red">판매금지</font></td>
				  </c:if>
				  <c:if test="${beverage.state==1}">
					<td class="w3-center" ><font color="blue">판매 중</font></td>
				  </c:if>
				<td class="w3-center" >
					<button class="w3-button w3-border" onclick="beverageUpdate(${beverage.no})">수정</button>
					<button class="w3-button w3-red" onclick="beverageDelete(${beverage.no})">삭제</button>
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
  		location.href="${ pageContext.servletContext.contextPath }/admin/sellList/"+pageNum;
  	}
	function beverageUpdate(beverageNo){
  		location.href="${ pageContext.servletContext.contextPath }/admin/updateBeverageForm/"+beverageNo
  	}
  	function beverageDelete(no){
  		var isTrue = confirm("상품을 삭제하겠습니까?");
		if(isTrue == true){
  			location.href="${ pageContext.servletContext.contextPath }/admin/deleteBeverage/"+no;
		}
  	}
  	var count=${count};
  	if(count==0){
  		$('#mail').submit();
  	}
  </script>
							
  
</body>
