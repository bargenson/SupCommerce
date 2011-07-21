<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<body>
        <article>
        	<header>
	            <h1>
	            	<c:out value="${product.name}" />
	            </h1>
	        </header>
	        <p>
	        	<c:url value="/picture/display?productId=${product.id}" var="productPictureUrl" />
	        	<img alt="Product Picture" src="${productPictureUrl}" />
	        </p>
            <p>
            	<c:out value="${product.description}" />
            </p>
            <p>
            	Price: <f:formatNumber currencySymbol="$" minIntegerDigits="0" type="currency" value="${product.price}"/>
            </p>
            <div>
            	<c:url value="addToCart?productId=${product.id}" var="addToCartUrl" />
            	<form action="${addToCartUrl}" method="post">
            		<input type="button" value="Add to Basket" />
            	</form>
            </div>
        </article>        
	</body>
</html>