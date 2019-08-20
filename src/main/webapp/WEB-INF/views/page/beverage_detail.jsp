<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body> 
<div class=" w3-container w3-border w3-white" style="width:500px;margin:10% auto;">
    <form method="POST">
    <input type="hidden" name="userNo" value="${userVO.no}">
    <input type="hidden" name="beverageNo" value="${beverageVO.no}">
    <input type="hidden" name="beveragePrice" value="${beverageVO.price}">
    <input type="hidden" name="beverageName" value="${beverageVO.name}">
    <div class="w3-half">
        <img src="<%= request.getContextPath() %>/fileSave/${beverageVO.fileName}"  class="w3-border w3-margin" style="height: 300px;width: 200px">
     </div>
    <div class="w3-half">
        <h5 class="w3-center" style="margin: 4px"><b>${beverageVO.name}</b></h5>
        <hr style="margin: 10px">
        <div class="w3-padding">
	       	<div class=" w3-center">
	      	 	가격 : ${beverageVO.price} 원
	         </div>
        </div>
        <div class="w3-right">
        남은 수량 : ${beverageVO.count} 개<br>
        구매  수량 : <input type="number" id="count" name="count" value="1" min="1"
        style="width:40%"> <p>
        </div>
    </div>
    <div class="w3-center w3-padding w3-margin">
    	<a href="${ pageContext.servletContext.contextPath }/page/main" 
    	class="w3-button w3-border">메인으로</a>
        <button id="cart" class="w3-button w3-border w3-blue">구매</button>
    </div>    
</form>
</div>
<script>
	var beverName= '${beverageVO.name}';
	var price = ${beverageVO.price};
	
	
 	$('#cart').click(function(){
		var val = $('#count').val();
		var buyCount = parseInt(val);
		var totalPrice= price*buyCount;
 		var isTrue = confirm('음료명: '+beverName+'\n구매수량: '+buyCount+
 				'\n총 결제금액: '+totalPrice+' 원 \n\n구매하시겠습니까?');
		if(isTrue == true){
			alert('주문이 완료되었습니다.')
			$('form').attr('action','${ pageContext.servletContext.contextPath }/page/insertOrder');
			$('form').submit();
		}else {
			$('form').attr('method','get');
		}
	});
 	
 
	
</script>
</body>
