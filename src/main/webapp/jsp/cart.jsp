<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<script type="text/javascript">
// 			$(document).ready( function() {
// 				$("input[type=number]").change
				
// 				$("#loginForm").validate({
// 					rules: {
// 						username: { required: true },
// 						password: { required: true }
// 					},
// 					submitHandler: function() {
// 						$("#loader").show();
// 						$.ajax({
// 							type: "POST",
// 							url: "j_security_check",
// 							data: {
// 								username: $("#username").val(), 
// 								password: $("#password").val()
// 							},
// 							success: function(result, textStatus, jqXHR) {
// 								var redirect = jqXHR.getResponseHeader("Location");
// 								if(redirect) {
// 									window.location.replace(redirect);
// 								} else {
// 									$("section").html(result);
// 								}
// 							},
// 							error: function(result, text, error) {
// 								alert("e:" + result + " " + text + " " + error);
// 							},
// 							complete: function() {
// 								$("#loader").hide();
// 							}
// 						});
// 						return false;
// 					}
// 				});
// 			});
		</script>
	</head>
	<body>
		<header>
			<h1>Cart</h1>
		</header>
		<table class="common-table zebra-striped">
			<c:choose>
				<c:when test="${not empty cartItems}">
					<form action="/updateCart" method="post">
						<span>Make any changes below?</span>
						<input type="submit" value="Update" />
						<c:forEach items="${cartItems}" var="i" varStatus="status">
			        		<tr>
			        			<td class="product-name-cell">
				        			<c:url value="/picture/display?productId=${i.product.id}" var="productPictureUrl" />
						        	<img alt="Product Picture" src="${productPictureUrl}" />
				        		</td>
				        		<td>
				        			${i.product.name} [${i.product.category.name}]
				        		</td>
				        		<td>
				        			<input type="number" name="quantity.${status.index}" step="1" size="3" value="${i.quantity}" />
				        		</td>
				        	</tr>
			        	</c:forEach>
			        </form>
				</c:when>
				<c:otherwise>
					<p>Nothing in your cart.</p>
				</c:otherwise>
			</c:choose>
		</table>
	</body>
</html>