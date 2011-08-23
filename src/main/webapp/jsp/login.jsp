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
    			<div class="alert-message error">
					<p><strong>Oh snap!</strong> Wrong username and/or password.</p>
				</div>
    		</c:if>
    		<fieldset>
   				<legend>Login</legend>
	    		<div class="clearfix">
		    		<label for="username">Username:</label>
		    		<div class="input">
		    			<input type="text" name="j_username" id="username" />
		    		</div>
	    		</div>
	    		<div class="clearfix">
		    		<label for="password">Password:</label>
		    		<div class="input">
		    			<input type="password" name="j_password" id="password" />
		    		</div>
	    		</div>
	    		<div class="actions">
	    			<input type="submit" value="Submit" id="submit" class="btn primary" />
	    			<img id="loader" src="/images/ajax-loader.gif" alt="Please wait..." style="display: none;" />
	    		</div>
    		</fieldset>
    	</form>
	</body>
</html>