<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Principal</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión ">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">
	<h1>OtherGame</h1>
	<%
		Users me = (Users) session.getAttribute("me");
	%>
	<p>
		¿Qué estás buscando hoy,
		<%=me.getPublicName()%>?
	</p>

	<form method="get" action="search">
		<input type="text" name="search" size="20" required> <input
			type="submit" value="Buscar">
	</form>


	<p>¿Algo que ofrecer?</p>
	<input type="button" onclick=" window.location.href='publish' "
		value="Publicar un anuncio ">
	<h2>Últimos anuncios disponibles</h2>
	
	<%	
		List<Ads> ads = (List<Ads>) request.getAttribute("principalAds");
	if (ads.size() < 1) {
	%>
	<p>No hay anuncios disponibles</p>
	<%
		} else {
	%>
	<% 		int quantity = (int) request.getAttribute("quantity"); %>
	<p>Número de anuncios existentes: <%= quantity %></p>
	<table>
		<%
			for (Ads ad : ads) {
		%>
		<div>
			<form action="singleAd" method="get">
				<input type="hidden" name="idAd" value=<%=ad.getId()%> />

				<tr>
					<td><%=ad.getNameAd()%></td>
					<td><input type="submit" value="Ver" /></td>
				</tr>

			</form>
		</div>
		<%
			}
		%>
	</table>

	<%
		
	if (quantity > 10) {
	%>
	<input type="button" onclick=" window.location.href='seeMore' "
		value="Ver más anuncios">
	<%
		}
		}
	%>

</body>
</html>