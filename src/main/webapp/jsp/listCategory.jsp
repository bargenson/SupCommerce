<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>	
	<body>
		<header>
			<h1>Categories</h1>
		</header>
		<table class="common-table zebra-striped">
			<c:forEach items="${categories}" var="c">
        		<tr>
        			<td>
	        			<a class="cell-link" href="category?id=${c.id}" title="${c.name}">${c.name}</a>
	        		</td>
	        	</tr>
        	</c:forEach>
		</table>
	</body>
</html>