<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.Users' %>
<%@ page import='com.comweb.model.Matches' %>
<%@ page import='com.comweb.model.Ads' %>
<%@ page import='java.util.List' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Articulos del otro usuario</h1>
    <input type="button" onclick=" window.location.href='myprofile' " value="Mi perfil">
<% 
Matches match = (Matches)request.getAttribute("currentMatch");
 %>
<p><%= 
match.toString()
%></p>


    <% 
List<Ads> ads = (List<Ads>)request.getAttribute("otherAds");
if (ads.size() < 1) {
%>
    <p>No hay anuncios disponibles</p>
    <%   
}else{
%>

<%
    for (Ads ad: ads) { 
%>
            <div>
	            <form action="matchSimpleAd" method="post">
					 <input type="hidden" name="idAd" value=<%= ad.getId() %> />
					 <input type="hidden" name="idMatch" value=<%= match.getId() %> />
				
					 <p><%= ad.toString() %></p>
					 <input type="submit" value="Ver artículo" />
				</form>                
            </div>
            <% 
    }
%>
<% } %>

    <input type="button" onclick=" window.location.href='publish' " value="Publicar un anuncio ">
    <input type="button" onclick=" window.location.href='logout' " value="Cerrar sesión ">

</body>
</html>