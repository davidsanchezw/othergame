<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import='com.comweb.model.Matches' %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Propuesta simple</h1>
        <% Matches singleMatch = (Matches)request.getAttribute("singleMatch"); %>
        <p><%= singleMatch.toString()  %></p>
        
            <input type="button" onclick=" window.location.href='principal' " value="Principal">
            <input type="button" onclick=" window.location.href='logout' " value="Cerrar sesión ">
        

</body>
</html>