<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<script type="text/javascript">
			$(document).ready( function() {
				$("#login-form").validate();
				$("#login-form").submit( function() {
					$.ajax({
						type: "POST",
						url: "/ajax/login",
						data: { 
							username: $("#username").val(), 
							password: $("#password").val()
						},
						success: function(result) {
							$("section").html(result);
						},
						error: function(result, text, error) {
							alert("e:" + result + " " + text + " " + error);
						}
					});
					return false;
				});
			});
		</script>
	</head>
	<body>
    	<form class="cmxform" id="login-form" action="login" method="post">
    		<div>
	    		<label for="username">Username:</label>
	    		<input class="required" type="text" name="username" id="username" />
    		</div>
    		<div>
	    		<label for="password">Password:</label>
	    		<input class="required" type="password" name="password" id="password" />
    		</div>
    		<div>
    			<input type="submit" value="Submit" id="submit" />
    		</div>
    	</form>
	</body>
</html>