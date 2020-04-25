<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import='com.comweb.model.Ads' %>
    <%@ page import='com.comweb.model.Users' %>
        <%@ page import='com.comweb.model.Matches' %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
            <input type="button" onclick=" window.location.href='principal' " value="Principal">
            <input type="button" onclick=" window.location.href='logout' " value="Cerrar sesión">

<h1>Anuncio</h1>
        <% Ads singleAd = (Ads)request.getAttribute("singleAd"); %>
        <% Matches match = (Matches)request.getAttribute("match"); %>
        
        <div>
	            <form action="matchToPending" method="post">
					 <input type="hidden" name="idAdToPending" value=<%= singleAd.getId() %> />
					 <input type="hidden" name="idMatchToPending" value=<%= match.getId() %> />
					 <p><%= singleAd.toString() %></p>
					 <p><%= match.getStatusMatchTxt().getTxt() %></p>
					 <input type="submit" value="Proponer intercambio" />
				</form>                
            </div>
            <form action="matchtocancelled" method="post">
					 <input type="hidden" name="idMatchToCancelled" value=<%= match.getId() %> />
					 <input type="submit" value="Cancelar" />
				</form>  


</body>
</html>