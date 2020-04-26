<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import='com.comweb.model.Matches' %>
        <%@ page import='com.comweb.model.Users' %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Error Registro</title>
</head>
<body>
<h1>Error en el registro</h1>
        <% String errorText = (String)request.getAttribute("errorText"); %>
        <p><%= errorText  %></p>
        
        		<input type="button" onclick=" window.location.href='registro.jsp' "
		value="Registro"> 
   
</body>
</html>