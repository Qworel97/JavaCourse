<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Part1</title>
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
<% for(int i = 0; i< 10; i++){
out.print("<tr>"); 
	for(int j = 0; j<10; j++){
		if(j==0 && i ==0){
				out.print("<th></th>");
			}
		else if(j==0){
			out.print("<th>" + i + "</th>");
		}	
		else if(i==0){
			out.print("<th>" + j + "</th>");
		}	
		else{
			out.print("<td>" + i*j + "</td>");
		}
	}
}%>
</table>
</body>
</html>