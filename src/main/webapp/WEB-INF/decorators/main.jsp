<%@page import="com.supinfo.supcommerce.security.LoginModule"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
        <title>SupCommerce - <sitemesh:write property='title'/></title>
        <meta name="description" content="A simple webapp for SUPINFO students." />
        <meta name="author" content="@bargenson" />
        <link rel="stylesheet" href="/styles/supcommerce.css" type="text/css"  media="screen"/>
		<link rel="stylesheet" href="http://twitter.github.com/bootstrap/assets/css/bootstrap-1.0.0.min.css">
        <link rel="stylesheet" href="/styles/start/jquery-ui-1.8.14.css" type="text/css"  media="screen"/>
		<script type="text/javascript" src="/scripts/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="/scripts/jquery.validate.js"></script>
		<script type="text/javascript" src="/scripts/jquery-ui-1.8.14.js"></script>
		<sitemesh:write property='head'/>
	</head>
	<body>
		<header class="topbar-wrapper" style="z-index: 5;">
		    <div class="topbar">
		      	<div class="fill">
		        	<div class="container">
		          		<h3><a href="/">SupCommerce</a></h3>
						<ul>
						  	<li><a href="/categories">Category List</a></li>
						  	<c:choose>
						 		<c:when test="${empty pageContext.request.userPrincipal}">
						 			<li><a href="/register">Register</a></li>
						 			<li><a href="/login">Login</a></li>
						 		</c:when>
						 		<c:otherwise>
						 			<% if (request.isUserInRole(LoginModule.ADMIN_ROLE)) { %>
								 		<li><a href="/addProduct">Add Product</a></li>
								 	<% } else if (request.isUserInRole(LoginModule.CUSTOMER_ROLE)) { %>
						 				<li><a href="/cart">Cart</a></li>
						 			<% } %>
						 			<li><a href="/logout">Logout</a></li>
						 		</c:otherwise>
						 	</c:choose>
						</ul>
		          		<form action="">
		            		<input type="text" placeholder="Search">
		          		</form>
		          		<c:if test="${not empty pageContext.request.userPrincipal}">
			          		<ul class="nav secondary-nav">
								<li class="menu">
									<a href="#"><c:out value="${pageContext.request.userPrincipal.name}" /></a>
								</li>
							</ul>
						</c:if>
	        		</div>
	      		</div>
	    	</div>
		</header>
		
		<section class="container" style="margin-top: 50px">
			<sitemesh:write property='body'/>
		</section>
				
		<footer>
			<em>Powered by Servlet 3</em>
		</footer>
	</body>
	
</html>