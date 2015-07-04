<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registration</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700|Archivo+Narrow:400,700" rel="stylesheet" type="text/css">
<link href="register.css" rel="stylesheet" type="text/css" media="all" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>

<body>
	<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
				<a href="/FamilyTree/welcome.jsp"> <img id="logoPic"
					src="images/logo.png">
				</a>
			</div>
			<div id="menu">
				<ul>
					<li><a href="/FamilyTree/welcome.jsp" accesskey="1" title="">Home</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div id="banner-wrapper">

		<div id="banner" class="regForm" style="width: 500px; float: left; padding-top: 50px;">
			<div style="width: 450px;">
				<form method="GET" action="AccountController"
					name="registrationForm" class="register">
					<p>
						<label for="first_name">First Name:</label> <input type="text"
							name="first_name" id="first_name" value="" />
					</p>
					<p>
						<label for="last_name">Last Name:</label> <input type="text"
							name="last_name" id="last_name" value="" />
					</p>
					<p>
						<label for="email">Email:</label> <input type="email" name="email"
							id="email" value="" />
					</p>
					<p>
						<label for="password">Password:</label> <input type="password"
							name="password" id="password" value="">
					</p>
					<p>
						<label for="confirmPassword">Re-enter:</label> <input
							type="password" name="confirmPassword" id="confirmPassword"
							value="">
					</p>
					${message}
					<p id="regSubmit">
						<button type="submit" name="register" class="button-register">Register</button>
					</p>
				</form>
			</div>
		</div>
		<div id="treePicture" style="float: right;">
			<img src="images/familytree.png" width="623" />
			<p>
				Your Family Ancestry just <strong>a click away</strong>.
			</p>
		</div>
	</div>

	<div id="copyright" class="container">
		<p>
			Copyright (c) 2013 <a href="http://www.familytree.com">FamilyTree.com</a>.
			All rights reserved.
		</p>
	</div>
</body>
</html>

<script type="text/javascript">
	function checkPassword() {

		var password = document.getElementById("password").value;
		var confirm = document.getElementById("confirmPassword").value;

		if (password != confirm) {
			alert("Password Do Not Match\nPlease Try Again");
		} else
			document.forms["registrationForm"].submit();
	}
</script>