<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
  <div class="w3-display-middle w3-container w3-border w3-white ">
  <c:if test="${count==0}">
   	<table class="w3-table w3-border w3-hoverable w3-center w3-small " style="width:90%">
    	<tr class="w3-light-grey">
    	<td class="w3-center">구매 내역이 없습니다.</td>
   	</table>
</c:if>
  <c:if test="${count!=0}">
	<div class="w3-center w3-padding">
		<b>이전 내역</b>(총 ${count} 건)
	</div>
	
	<table class="w3-table  w3-centered"
		style="border: black;">
		<tr class="w3-black">
			<th class="w3-center" >구매일</th>
			<th class="w3-center" >음료명</th>
			<th class="w3-center" >가격</th>
			<th class="w3-center" >구매수량</th>
			<th class="w3-center" >재구매 여부</th>
		</tr>
		<c:forEach var="order" items="${orders }">
			<tr class="w3-hover-white">
				<td class="w3-center" >
				<fmt:parseDate var="parsedDate" value="${order.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate  value="${parsedDate}" pattern="yyyy.MM.dd"/>
				</td>
				<td class="w3-center" >${order.beverageName }</td>
				<td class="w3-center" >${order.beveragePrice } 원</td>
				<td class="w3-center" >
				
				<input type="number" id="count" name="count" value="${order.count }" min="1"
       			 style="width:20%" > 개
				</td>
				<td class="w3-center" >
				<button class="w3-button w3-black" onclick="addOrder(${order.no},${order.beveragePrice })">
				재구매</button></td>
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
  		location.href="${ pageContext.servletContext.contextPath }/page/orderList/"+pageNum;
  	}
  	function userUpdate(targetNo,sessionNo){
  		location.href=
  			"${ pageContext.servletContext.contextPath }/admin/updateUserForm/"+targetNo+"/"+sessionNo
  	}
  	
  	function addOrder(no,price){
  		var val = $('#count').val();
  		var buyCount = parseInt(val);
  		var totalPrice = price*buyCount;
  		var isTrue = confirm('구매수량: '+buyCount+'\n총 결제금액: '+totalPrice+' 원 \n\n 다시 주문하시겠습니까?');
		if(isTrue == true){
			alert('주문이 완료되었습니다.')
			location.href="${ pageContext.servletContext.contextPath }/page/addReOrder/"+no+"/"+buyCount;
		}else{
			$('form').attr('method','get');
		}
  	}
  	
  </script>
							
  
</body>
