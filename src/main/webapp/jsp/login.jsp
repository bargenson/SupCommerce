<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<script type="text/javascript">
			$(document).ready( function() {
				$("#loginForm").validate({
					rules: {
						username: { required: true },
						password: { required: true }
					}
				});
				$("#loginForm").submit( function() {
					$("#loader").show();
					$.ajax({
						type: "POST",
						url: "/ajax/login",
						data: { 
							username: $("#username").val(), 
							password: $("#password").val()
						},
						success: function(result, textStatus, jqXHR) {
							var redirect = jqXHR.getResponseHeader("Location");
							if(redirect) {
								window.location.replace(redirect);
							} else {
								$("section").html(result);
							}
						},
						error: function(result, text, error) {
							alert("e:" + result + " " + text + " " + error);
						},
						complete: function() {
							$("#loader").hide();
						}
					});
					return false;
				});
			});
		</script>
	</head>
	<body>
    	<form class="cmxform" id="loginForm" action="login" method="post">
    		<c:if test="${not empty errors}">
   				<ul>
    				<c:forEach items="${errors}" var="e">
    					<li>
    						<c:out value="${e}" />
    					</li>
	    			</c:forEach>
   				</ul>
    		</c:if>
    		<div>
	    		<label for="username">Username:</label>
	    		<input type="text" name="username" id="username" />
    		</div>
    		<div>
	    		<label for="password">Password:</label>
	    		<input type="password" name="password" id="password" />
    		</div>
    		<div>
    			<input type="submit" value="Submit" id="submit" />
    			<img id="loader" src="/images/ajax-loader.gif" alt="Please wait..." style="display: none;" />
    		</div>
    	</form>
	</body>
</html>