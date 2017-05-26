<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Part2</title>
<style type="text/css">
   TABLE {
    width: 300px; 
   }
   TD, TH {
    padding: 3px; 
    border: 1px solid black; 
   }
   TH {
    background: #b0e0e6; 
   }
</style>
</head>
<body>
<table>
<c:forEach var = "i" begin = "0" end = "9">
	<tr>
	<c:forEach var = "j" begin = "0" end = "9">
    	<c:choose>
  			<c:when test="${i==0 && j==0}">
    			<th></th>
  			</c:when>
  			<c:when test="${j==0}">
    			<th><c:out value="${i}"/></th>
  			</c:when>
  			<c:when test="${i==0}">
    			<th><c:out value="${j}"/></th>
  			</c:when>
  			<c:otherwise>
    			<td><c:out value="${i*j}"/></td>
  			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:forEach>
</table>
</body>
</html>