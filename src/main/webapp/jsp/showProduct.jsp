<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
        <title>SupCommerce</title>
        <meta name="description" content="A simple webapp for SUPINFO students.">
        <meta name="author" content="@bargenson">
<!--         <link rel="shortcut icon" href="favicon.ico"> -->
<!--         <link rel="stylesheet" href="main.css" type="text/css"  media="screen"> -->
	</head>

	<body>
        <article>
        	<header>
	            <h1>
	            	<c:out value="${product.name}" />
	            </h1>
	        </header>
	        <p>
	        	<c:url value="/images/noImageAvailable.jpg" var="noPictureImageUrl" />
	        	<img alt="Product Picture" src="${noPictureImageUrl}" />
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