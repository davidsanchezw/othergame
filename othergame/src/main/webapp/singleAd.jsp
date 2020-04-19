<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import='com.comweb.model.Ads' %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Anuncio</h1>
        <% Ads singleAd = (Ads)request.getAttribute("singleAd"); %>
        
        <div>
	            <form action="createMatch" method="get">
					 <input type="hidden" name="idAd" value=<%= singleAd.getId() %> />
					 <p><%= singleAd.toString() %></p>
					 <input type="submit" value="Proponer intercambio" />
				</form>                
            </div>
            <input type="button" onclick=" window.location.href='principal' " value="Principal">
        

</body>
</html>