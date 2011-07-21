<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript">
			$(document).ready( function() {
				$("#dateOfBirth").datepicker();
				$("#registerForm").validate({
					rules: {
						username: 		{ required: true, minlength: 4 },
						password: 		{ required: true, minlength: 6 },
						confirmation: 	{ required: true, equalTo: "#password" },
						email: 			{ required: true, email: true },
						firstName: 		{ required: true },
						lastName: 		{ required: true },
						dateOfBirth: 	{ required: true, date: true }
					},
					submitHandler: function() {
						$("#loader").show();
						$.ajax({
							type: "POST",
							url: "/ajax/register",
							data: { 
								username: 		$("#username").val(),
								password: 		$("#password").val(),
								confirmation:	$("#confirmation").val(),
								email: 			$("#email").val(),
								firstName: 		$("#firstName").val(),
								lastName: 		$("#lastName").val(),
								dateOfBirth: 	$("#dateOfBirth").val()
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
					}
				});
			});
		</script>
	</head>
	<body>
		<form id="registerForm" class="cmxform" action="" method="post">
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
				<label for="confirmation">Password Confirmation:</label>
				<input type="password" name="confirmation" id="confirmation" />
			</div>
			<div>
				<label for="email">E-mail:</label>
				<input type="email" name="email" id="email" />
			</div>
			<div>
				<label for="firstName">First Name:</label>
				<input type="text" name="firstName" id="firstName" />
			</div>
			<div>
				<label for="lastName">Last Name:</label>
				<input class="required" type="text" name="lastName" id="lastName" />
			</div>
			<div>
				<label for="dateOfBirth">Date of birth (mm/dd/yyyy):</label>
				<input type="text" name="dateOfBirth" id="dateOfBirth" />
			</div>
			<div>
				<input type="submit" id="submit" value="Submit" />
				<img id="loader" src="/images/ajax-loader.gif" alt="Please wait..." style="display: none;" />
			</div>
		</form>
	</body>
</html>