<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Part3</title>
</head>
<body>
<form method = "POST" action = "Part3Parser">
	<input type="text" name="name" placeholder="Type name"></br>
	<input type="submit" text="add"></br>
</form>
<form method = "GET" action = "Part3Parser">
	<input type="submit" text="remove"></br>
</form>
<c:forEach items="${names}" var="name">
  <c:out value="${name}"/></br>
</c:forEach>
</body>
</html>