<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8">
        <title>SupCommerce - <sitemesh:write property='title'/></title>
        <meta name="description" content="A simple webapp for SUPINFO students." />
        <meta name="author" content="@bargenson" />
<!--         <link rel="shortcut icon" href="favicon.ico"> -->
<!--         <link rel="stylesheet" href="main.css" type="text/css"  media="screen"> -->
		<script type="text/javascript" src="/scripts/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="/scripts/jquery.validate.js"></script>
		<sitemesh:write property='head'/>
	</head>

	<body>
		<header>
			<h1>SupCommerce !</h1>
		</header>
		
		<section>
			<sitemesh:write property='body'/>
		</section>
		
		<footer>
			<i>Powered by Servlet 3 + Sitemesh</i>
		</footer>
	</body>
	
</html>