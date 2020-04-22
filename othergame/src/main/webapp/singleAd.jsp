<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import='com.comweb.model.Ads' %>
    <%@ page import='com.comweb.model.Users' %>
    
    
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
        
        <div>
	            <form action="createMatch" method="get">
					 <input type="hidden" name="idAd" value=<%= singleAd.getId() %> />
					 <p><%= singleAd.toString() %></p>
					 <input type="submit" value="Proponer intercambio" />
				</form>                
            </div>
            
            <% Users simpleUser = (Users)request.getAttribute("simpleUser"); %>
        <div>
       			<form action="otherProfile" method="get">
					 <input type="hidden" name="idOtherUser" value=<%= simpleUser.getId() %> />
					 <p><%= simpleUser.toString() %></p>
					 <input type="submit" value="Ver perfil" />
				</form>
        </div>

</body>
</html>