<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>

  <div class="w3-main" style="margin-left:300px;margin-top:50px;">
  <div class="w3-row-padding " style="width:65%;">
<c:if test="${count==0}">
   	<table class="w3-table w3-border w3-hoverable w3-center w3-small " 
   	style="width:90%">
    	<tr class="w3-light-grey">
    	<td class="w3-center">등록된 상품이 없습니다.</td>
   	</table>
</c:if>

<c:if test="${count!=0}">
      <c:forEach var="beverage" items="${beverages}">
        <div class="w3-third w3-container w3-margin-bottom w3-padding w3-small">
         <div class="w3-display-container">
          <img src="<%= request.getContextPath() %>/fileSave/${beverage.fileName}" 
          class="w3-border-top w3-border-left w3-border-right" alt="이미지를표시할수없음" style="height:150px; width:100%;">
          <c:if test="${beverage.state==1}">
          <div class="w3-display-middle w3-display-hover">
            <button class="w3-button w3-xxlarge w3-teal" 
            onclick="beverage_detail(${beverage.no})">상세보기</button>
          </div>
          </c:if>
          <c:if test="${beverage.state==0||beverage.count==0}">
          <div class="w3-display-middle ">
            <button class="w3-button w3-xxlarge w3-red" 
            onclick="alert('판매가 중단된 상품입니다.')">판매중지</button>
          </div>  
          </c:if>
        </div>
      <div class="w3-container w3-border ">
          <table style="width:100%" class="w3-light-grey w3-table">
           <tr>
           	<td ><b>상 품 명: </b></td>
            <td > ${beverage.name}</td>
           </tr>  
           <tr>
           	<td><b>가 격: </b></td>
            <td>${beverage.price} 원 / ${beverage.capacity} ml</td>
           </tr>  
           <tr>
           	<td><b>남은 수량: </b></td>
            <td>${beverage.count} 개</td>
           </tr>  
			</table>
      </div>
    </div>
</c:forEach>
   </c:if>
  </div>
  		<!-- Pagination --> 

		   <div class=" w3-small " style="margin-left:300px">
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
	</div>
	
	<script>
  	function movePage(pageNum){
  		location.href="${ pageContext.servletContext.contextPath }/page/beverageList/"+pageNum;
  	}
  	function beverage_detail(no){
  		location.href='${ pageContext.servletContext.contextPath }/page/beverage_detail/'+no;
  	}
  </script>
							
  
</body>
