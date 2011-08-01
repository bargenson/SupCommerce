<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<script type="text/javascript">
// 			$(document).ready( function() {
// 				$("#loginForm").validate({
// 					rules: {
// 						username: { required: true },
// 						password: { required: true }
// 					},
// 					submitHandler: function() {
// 						$("#loader").show();
// 						$.ajax({
// 							type: "POST",
// 							url: "j_security_check",//"/ajax/login",
// 							data: { 
// 								username: $("#username").val(), 
// 								password: $("#password").val()
// 							},
// 							success: function(result, textStatus, jqXHR) {
// 								var redirect = jqXHR.getResponseHeader("Location");
// 								if(redirect) {
// 									window.location.replace(redirect);
// 								} else {
// 									$("section").html(result);
// 								}
// 							},
// 							error: function(result, text, error) {
// 								alert("e:" + result + " " + text + " " + error);
// 							},
// 							complete: function() {
// 								$("#loader").hide();
// 							}
// 						});
// 						return false;
// 					}
// 				});
// 			});
		</script>
	</head>
	<body>
    	<form class="cmxform" id="loginForm" action="j_security_check" method="post">
    		<c:if test="${param.error == 1}">
   				<ul>
   					<li>
   						Wrong username and/or password.
   					</li>
   				</ul>
    		</c:if>
    		<div>
	    		<label for="username">Username:</label>
	    		<input type="text" name="j_username" id="username" />
    		</div>
    		<div>
	    		<label for="password">Password:</label>
	    		<input type="password" name="j_password" id="password" />
    		</div>
    		<div>
    			<input type="submit" value="Submit" id="submit" />
    			<img id="loader" src="/images/ajax-loader.gif" alt="Please wait..." style="display: none;" />
    		</div>
    	</form>
	</body>
</html>