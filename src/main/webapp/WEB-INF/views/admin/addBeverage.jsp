<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
<div class="w3-display-middle w3-container w3-border w3-white">
	<div class="w3-padding-large">
		<form action="${ pageContext.servletContext.contextPath }/admin/addBeverage" method="post" enctype="multipart/form-data">
			음료명: <input type="text" name="name"><p>
			재고량: <input type="text" name="count"><p>
			가격: <input type="text" name="price"><p>
			용량: <input type="text" name="capacity"><p>
			판매가능 여부: <input type="checkbox" checked="checked" name="state" value="1"> (체크 시 판매가능)<p>
			음료 이미지: <input type="file" name="file"><p>
			<div class="w3-center w3-padding">
				<input type="submit" value="상품 등록" class="w3-button w3-black">
				<input type="reset" value="재입력" class="w3-button w3-border">
			</div>
		</form>
	</div>
</div>
</body>
