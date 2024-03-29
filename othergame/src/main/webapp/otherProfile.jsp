<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Perfil de otro usuario</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<input type="button" onclick=" window.location.href='logout' "
			value="Cerrar sesi�n"> <input type="button"
			onclick=" window.location.href='principal' " value="Principal">
		<input type="button" onclick=" window.location.href='myprofile' "
			value="Mi perfil">

		<%
			Users otherUser = (Users) request.getAttribute("otherUser");
		%>
		<h1>OtherGame</h1>
	</div>
	<div class='cuerpo'>
		<h2>
			Perfil de
			<%=otherUser.getPublicName()%></h2>


		<p>
			Descripci�n:
			<%=otherUser.getExplanation()%></p>

		<h2>Propuestas finalizadas</h2>

		<form action="otherMatches" method="get">
			<input type="hidden" name="idUser" value=<%=otherUser.getId()%> /> <input
				type="submit" value="Propuestas completadas" />

		</form>

		<h2>Anuncios disponibles</h2>

		<%
			List<Ads> ads = (List<Ads>) request.getAttribute("otherAds");
		if (ads.size() < 1) {
		%>
		<p>No hay anuncios disponibles</p>

		<%
			} else {
		%>

		<%
			for (Ads ad : ads) {
		%>
		<div class='linea'>
			<form action="adView" method="get">
				<input type="hidden" name="idAd" value=<%=ad.getId()%> />
				<p><%=ad.getNameAd()%></p>
				<input type="submit" value="Ver" />
			</form>
		</div>
		<%
			}
		%>
		<%
			}
		%>
	</div>
</body>
</html>