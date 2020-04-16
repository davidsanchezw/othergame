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
<h1>Hola</h1>
<% 
Users me = (Users)session.getAttribute("me");
 %>
<p><%= 
me.toString()
%></p>


    <% 
List<Ads> ads = (List<Ads>)request.getAttribute("principalAds");
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
                <p><%= ad.toString() %></p>
            </div>
            <% 
    }
%>
<% } %>


    <input type="button" onclick=" window.location.href='logout' " value="Cerrar sesión ">

</body>
</html>