<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript">
			$(document).ready( function() {
				$("#dateOfBirth").datepicker();
				$("#registerForm").validate();
			});
		</script>
	</head>
	<body>
		<form id="registerForm" class="cmxform" action="register" method="post">
			<div>
				<label for="username">Username:</label>
				<input minlength="4" class="required" type="text" name="username" id="username" />
			</div>
			<div>
				<label for="password">Password:</label>
				<input minlength="6" class="required password valid" type="password" name="password" id="password" />
			</div>
			<div>
				<label for="confirmation">Password Confirmation:</label>
				<input minlength="6" class="required valid" equalto="#password" type="password" name="confirmation" id="confirmation" />
			</div>
			<div>
				<label for="email">E-mail:</label>
				<input class="required email" type="text" name="email" id="email" />
			</div>
			<div>
				<label for="firstName">First Name:</label>
				<input class="required" type="text" name="firstName" id="firstName" />
			</div>
			<div>
				<label for="lastName">Last Name:</label>
				<input class="required" type="text" name="lastName" id="lastName" />
			</div>
			<div>
				<label for="dateOfBirth">Date of birth (mm/dd/yyyy):</label>
				<input class="required date" type="text" name="dateOfBirth" id="dateOfBirth" />
			</div>
			<div>
				<input type="submit" id="submit" value="Submit" />
			</div>
		</form>
	</body>
</html>