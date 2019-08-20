<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
<div class="w3-display-middle w3-container w3-border w3-white">
	<div class="w3-padding-large">
		<form action="${ pageContext.servletContext.contextPath }/admin/updateBeveragePro" method="post" enctype="multipart/form-data">
			<input type="hidden" name="no" value=${beverageVO.no }>
			음료명: <input type="text" name="name" value="${beverageVO.name }"><p>
			재고량: <input type="text" name="count" value="${beverageVO.count }"><p>
			가격: <input type="text" name="price" value="${beverageVO.price }"><p>
			용량: <input type="text" name="capacity" value="${beverageVO.capacity }"><p>
			판매가능 여부: <input type="checkbox" checked="checked" name="state" value="1"> (체크 시 판매가능)<p>
			음료 이미지: <input type="file" name="file"><p>
			<div class="w3-center w3-padding">
				<input type="submit" value="수정" class="w3-button w3-black">
				<input type="reset" value="재입력" class="w3-button w3-border">
			</div>
		</form>
	</div>
</div>
</body>
