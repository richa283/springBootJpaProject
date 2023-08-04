<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
	<title>SIGN UP HERE</title>
	<link rel = "stylesheet" href = "style.css"/>
</head>

<body>
<br><br>
	<div class="signupbox" align="center">
		<h2>Sign Up Form</h2>
		<form action = "signUpHandlerMethod" method="post">
		
			<label>Name</label>
			<input type = "text" name = "username"/>
			<br><br>
			<label>Fathers Name</label>
			<input type="text" name="fathername"/>
			<br><br>
			<label>Mothers Name</label>
			<input type="text" name="mothername"/>
			<br><br>
			<label>Mobile Number</label>
			<input type="number" name="mobile"/>
			<br><br>
			<label>Residential Address</label>
			<input type="text" name="address"/>
			<br><br>
			<label>Email ID</label>
			<input type="email" name="email"/>
			<br><br>
			<label>Password</label>
			<input type="password" name="pass"/>
			<br><br>
			<label>Confirm Password</label>
			<input type="password" name="cpass"/>
			<br><br>

			<input type="submit" value="Create your account" onclick="errorMessage()"/><br><br>
			<p>Already have an account? <a href="Login">Login here</a></p><input type="reset" value ="RESET" />
		</form>
	</div>

</body>
</html>