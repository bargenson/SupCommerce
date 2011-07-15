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
		<header>
			<h1>
				<c:out value="${category.name}" />
			</h1>
		</header>
		<div>
			<c:choose>
				<c:when test="${not empty category.products}">
					<c:forEach items="${category.products}" var="p">
				        <article>
				        	<header>
					            <h1>
					            	<c:out value="${p.name}" />
					            </h1>
					        </header>
					        <p>
					        	<c:url value="product?id=${p.id}" var="productUrl" />
					        	<a href="${productUrl}">
					        		<c:url value="/images/noImageAvailable.jpg" var="noPictureImageUrl" />
	        						<img alt="Product Picture" src="${noPictureImageUrl}" />
					        	</a>
					        </p>
				            <p>
				            	<c:out value="${p.description}" />
				            </p>
				            <p>
				            	<f:formatNumber currencySymbol="$" minIntegerDigits="0" type="currency" value="${p.price}"/>
				            </p>
				        </article>
			        </c:forEach>
				</c:when>
				<c:otherwise>
					<p>No articles for this category.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>