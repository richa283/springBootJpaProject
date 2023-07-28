<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 	
<title>Login</title>
</head>
<body>
<br><br> 
	<div align="center">
		<h1>Login</h1>
		<form action="LoginServlet" method="post">
			<label>Email </label>
			<input type="email" name="email"/>
			<br><br>
			<label>Password </label>
			<input type="password" name="password"/>
			<br><br>
			<input type="submit" value="Submit"/>
			<input type="reset" />
			<br><br>
			<a href="signUp">Sign Up Here</a>
		</form>
	</div>

</body>
</html>