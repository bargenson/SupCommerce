<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<link href="/scripts/uploadify/uploadify.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="/scripts/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="/scripts/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
		<script type="text/javascript">
			$(document).ready( function() {
				$("#addProductForm").validate({
					rules: {
						name: 			{ required: true, minlength: 4 },
						description: 	{ required: true },
						price: 			{ required: true, number: true, min: 0.01 },
						category: 		{ required: true }
					},
					submitHandler: function() {
						$("#loader").show();
						$.ajax({
							type: "POST",
							url: "/ajax/addProduct",
							data: { 
								name: 			$("#name").val(),
								description:	$("#description").val(),
								price: 			$("#price").val(),
								category: 		$("#category").val()
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
				$('#picture').uploadify({
				    'uploader'  	: '/scripts/uploadify/uploadify.swf',
				    'script'    	: '/picture/upload;jsessionid=<c:out value="${pageContext.session.id}" />',
				    'cancelImg' 	: '/scripts/uploadify/cancel.png',
				    'fileDataName'  : 'data',
				    'auto'      	: true,
				    'sizeLimit'		: 500000,
				    'onComplete'	: function(event, ID, fileObj, response, data) {
				    	alert('Response: ' + response);
				    }
			  	});
			});
		</script>
	</head>
	<body>
		<form id="addProductForm" class="cmxform" action="" method="post" enctype="multipart/form-data">
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
				<label for="name">Name:</label>
				<input type="text" name="name" id="name" />
			</div>
			<div>
				<label for="description">Description:</label>
				<textarea name="description" id="description"></textarea>
			</div>
			<div>
				<label for="price">Price:</label>
				<input type="text" name="price" id="price" />
			</div>
			<div>
				<label for="category">Category:</label>
				<select name="category" id="category" >
					<option>-- Select a category --</option>
					<c:forEach items="${categories}" var="c">
						<option value="${c.id}"><c:out value="${c.name}"/></option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label for="picture">Picture:</label>
				<input type="file" name="picture" id="picture" />
			</div>
			<div>
				<input type="submit" id="submit" value="Submit" />
				<img id="loader" src="/images/ajax-loader.gif" alt="Please wait..." style="display: none;" />
			</div>
		</form>
	</body>
</html>