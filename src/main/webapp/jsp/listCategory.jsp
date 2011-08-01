<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>	
	<body>
		<c:if test="${not empty pageContext.request.userPrincipal}">
			Welcome <c:out value="${pageContext.request.userPrincipal.name}" />
		</c:if>
        <ul>
        	<c:forEach items="${categories}" var="c">
        		<li>
	        		<a href="category?id=${c.id}" title="${c.name}">${c.name}</a>
	        	</li>
        	</c:forEach>
        </ul>
	</body>
</html>