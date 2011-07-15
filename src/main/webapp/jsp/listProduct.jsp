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
		<header>
	        <nav>
		        <ul>
		        	<c:forEach items="${categories}" var="c">
		        		<li>
			        		<a href="category?id=${c.id}" title="${c.name}">${c.name}</a>
			        	</li>
		        	</c:forEach>
		        </ul>
		    </nav>
		</header>
	</body>
</html>