<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bookstore Shopping Cart</title>
</head>
<body>
	<h1>Bookstore Shopping Cart</h1>
	
	<form name="cartForm" action="" method="POST">
		<input type="hidden" name="isbn" value="" />
	
		<table>
			<tr>
				<th>Title</th>
				<th>Author</th>
				<th>ISBN</th>
				<th>Price</th>
				<th>Quantity</th>
				<th></th>
			</tr>
	
			<c:forEach var="item" items="${cart.items}">
			<tr>
				<td>${item.book.title}</td>
				<td>${item.book.author}</td>
				<td>${item.book.isbn}</td>
				<td>${item.book.formattedPrice}</td>
				<td>${item.quantity}</td>
				<td><input type="button" name="${item.book.isbn}" value="Remove from Cart" onClick="removeFromCart('${item.book.isbn}');" />
			</tr>
			</c:forEach>
		</table>
		
		<p><input type="submit" name="back" value="Back" onClick="form.action='${pageContext.request.contextPath}/list';" />
		   <input type="submit" name="logout" value="Logout" onClick="form.action='${pageContext.request.contextPath}/logout';" /></p>
	</form>
	
	<script type="text/javascript">
		function removeFromCart(isbn) {
			document.cartForm.isbn.value = isbn;
			document.cartForm.action = "${pageContext.request.contextPath}/removeFromCart";
			document.cartForm.submit();
		}
	</script>
</body>
</html>