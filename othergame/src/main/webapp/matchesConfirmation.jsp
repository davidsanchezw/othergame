<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import='com.comweb.model.Matches' %>
        <%@ page import='com.comweb.model.Users' %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <input type="button" onclick=" window.location.href='principal' " value="Principal">
            <input type="button" onclick=" window.location.href='logout' " value="Cerrar sesión ">
<h1>Propuesta simple</h1>
        <% Matches singleMatch = (Matches)request.getAttribute("match"); %>
        <p><%= singleMatch.toString()  %></p>
        
        		<form action="matchtocompleted" method="post">
					 <input type="hidden" name="idMatchToCompleted" value=<%= singleMatch.getId() %> />
					 <input type="submit" value="Confirmar" />
				</form>       
				<form action="matchtocancelled" method="post">
					 <input type="hidden" name="idMatchToCancelled" value=<%= singleMatch.getId() %> />
					 <input type="submit" value="Cancelar" />
				</form>  
   
</body>
</html>