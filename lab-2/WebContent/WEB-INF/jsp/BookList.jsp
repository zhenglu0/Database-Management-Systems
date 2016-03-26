<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bookstore Books</title>
</head>
<body>
	<h1>Bookstore Books</h1>

	<form name="cartForm" action="${pageContext.request.contextPath}/viewBook" method="POST">
		<input type="hidden" name="isbn" value="" />
		
		<table>
			<tr>
				<th>Title</th>
				<th>Author</th>
				<th>ISBN</th>
				<th>Price</th>
				<th></th>
			</tr>
	
			<c:forEach var="book" items="${books}">
			<tr>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.isbn}</td>
				<td>${book.formattedPrice}</td>
				<td><input type="button" name="${book.isbn}" value="Add to Cart" onClick="addToCart(${book.isbn});" />
			</tr>
			</c:forEach>
		</table>
		
		<p><input type="submit" name="cart" value="View cart" onClick="form.action='${pageContext.request.contextPath}/viewCart';" />
		   <input type="submit" name="logout" value="Logout" onClick="form.action='${pageContext.request.contextPath}/logout';" /></p>
	</form>
	
	<script type="text/javascript">
		function addToCart(isbn) {
			document.cartForm.isbn.value = isbn;
			document.cartForm.action = "${pageContext.request.contextPath}/addToCart";
			document.cartForm.submit();
		}
	</script>
</body>
</html>
