<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bookstore Welcome</title>
</head>
<body>
	<h1>Bookstore Welcome</h1>
	
	<p>Welcome ${user.username}!</p>
	
	<form action="" method="POST">
		<p><input type="submit" name="shop" value="Shop" onClick="form.action='${pageContext.request.contextPath}/list';" />
		   <input type="submit" name="cart" value="Show Cart" onClick="form.action='${pageContext.request.contextPath}/viewCart';" /></p>
	</form>
</body>
</html>
