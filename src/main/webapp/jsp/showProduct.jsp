<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript">
			$(document).ready( function() {
				<c:if test="${not empty pageContext.request.userPrincipal}">
					$("#addToCartForm").validate({
						rules: {
							username: { required: true },
							password: { required: true }
						},
						submitHandler: function() {
							$("#loader").show();
							$.ajax({
								type: "POST",
								url: "addToCart?productId=${product.id}",
								data: { 
									productId: <c:out value="${product.id}" />
								},
								success: function(result, textStatus, jqXHR) {
									$("#alert-message").show();
								},
								error: function(result, text, error) {
									alert("e:" + result + " " + text + " " + error);
								},
								complete: function() {
									$("#loader").hide();
								}
							});
							return false;
						}
					});
            	</c:if>
			});
		</script>
	</head>
	<body>
  		<div id="alert-message" class="alert-message success" <c:if test="${param.add != 1}">style="display: none;"</c:if>>
       		<p><strong>Well done!</strong> You successfully added the product to your cart.</p>
     	</div>
        <article>
        	<header>
	            <h1>
	            	<c:out value="${product.name}" />
	            </h1>
	        </header>
	        <aside class="product-picture">
	        	<c:url value="/picture/display?productId=${product.id}" var="productPictureUrl" />
	        	<img alt="Product Picture" src="${productPictureUrl}" />
	        </aside>
	        <section class="product-description">
	            <p>
	            	<c:out value="${product.description}" />
	            </p>
	            <p>
	            	Price: <f:formatNumber currencySymbol="$" minIntegerDigits="0" type="currency" value="${product.price}"/>
	            </p>
	            <div>
	            	<c:choose>
	            		<c:when test="${not empty pageContext.request.userPrincipal}">
	            			<form id="addToCartForm" action="addToCart" method="post">
	            				<input type="hidden" name="productId" value="${product.id}" />
	            				<input type="submit" value="Add to cart" />
			            		<img id="loader" src="/images/ajax-loader.gif" alt="Please wait..." style="display: none;" />
			            	</form>
	            		</c:when>
	            		<c:otherwise>
	            			<a href="addToCart?productId=1">Login and add to cart</a>
	            		</c:otherwise>
	            	</c:choose>
	            </div>
            </section>
        </article>        
	</body>
</html>