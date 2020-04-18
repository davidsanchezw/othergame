<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.Users' %>
<%@ page import='com.comweb.model.Ads' %>
<%@ page import='java.util.List' %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Mi perfil de usuario</h1>
<% 
Users me = (Users)session.getAttribute("me");
 %>
<p><%= 
me.toString()
%></p>


    <% 
List<Ads> ads = me.getAds(); 
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
				<form action="singleAd" method="get">
					 <input type="hidden" name="idAd" value=<%= ad.getId() %> />
					 <p><%= ad.toString() %></p>
					 <input type="submit" value="Ver" />
				</form>             </div>
            <% 
    }
%>
<% } %>
    <input type="button" onclick=" window.location.href='principal' " value="Principal ">
    <input type="button" onclick=" window.location.href='publish' " value="Publicar un anuncio ">
    <input type="button" onclick=" window.location.href='logout' " value="Cerrar sesión ">

</body>
</html>