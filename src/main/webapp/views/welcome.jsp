<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
		<title>Login</title>
	<link rel="stylesheet" href="style.css" />
</head>

<body>
<br><br> 
	<div class = "loginpage">
		<h1>Login</h1>
		<form action="LoginServlet" method="post">
			<label>Email </label>
			<input type="email" name="email"/>
			<br><br>
			<label>Password </label>
			<input type="password" name="password"/>
			<br><br>
			<input type="submit" value="Submit"/>
			<br><br>
			<a href="signUp">Sign up here...</a>
			<input type="reset" value ="RESET" />
		</form>
	</div>

</body>
</html>