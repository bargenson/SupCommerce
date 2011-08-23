<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<script src="scripts/jquery.tablesorter.min.js"></script>
		<script >
			$(document).ready( function() { 
				$("table.sortable").tablesorter(); 
			}); 
		</script>
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
					<table class="common-table zebra-striped sortable">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Price</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${category.products}" var="p">
						        <tr>
						        	<td class="product-name-cell">
						        		<c:url value="product?id=${p.id}" var="productUrl" />
							        	<a href="${productUrl}">
							            	<c:url value="/picture/display?productId=${p.id}" var="productPictureUrl" />
			        						<img alt="Product Picture" src="${productPictureUrl}" />
							            	<c:out value="${p.name}" />
							            </a>
							        </td>
							        <td class="product-description-cell">
						            	<c:out value="${p.description}" />
						            </td>
						            <td class="product-price-cell">
						            	<f:formatNumber currencySymbol="$" minIntegerDigits="0" type="currency" value="${p.price}"/>
						            </td>
						        </tr>
					        </c:forEach>
				        </tbody>
					</table>
				</c:when>
				<c:otherwise>
					<p>No articles for this category.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>