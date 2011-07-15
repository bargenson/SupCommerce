<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		<div>
			<c:forEach items="${category.products}" var="p">
		        <article>
		            <h1>
		            	<c:out value="${p.name}" />
		            </h1>
		            <p>
		            	<c:out value="${p.description}" />
		            </p>
		        </article>
	        </c:forEach>
		</div>
	</body>
</html>