<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bookstore Login</title>
</head>
<body>
	<h1>Bookstore Login</h1>

	<form action="${pageContext.request.contextPath}/login" method="POST">
		<p>Username: <input type="text" name="username" /></p>
		<p>Password: <input type="password" name="password" /></p>
		<p><input type="submit" name="submit" value="Submit" /></p>
	</form>
</body>
</html>
