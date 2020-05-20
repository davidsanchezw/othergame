<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.StatusPostTxt'%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='java.util.List'%>
<%@ page import='java.text.SimpleDateFormat'%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Anuncio Completado</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<input type="button" onclick=" window.location.href='logout' "
			value="Cerrar sesión"> <input type="button"
			onclick=" window.location.href='principal' " value="Principal">
		<input type="button" onclick=" window.location.href='myprofile' "
			value="Mi perfil">

		<h1>OtherGame</h1>
	</div>
	<div class='cuerpo'>
		<input type="button" onclick=" window.location.href='adsCompleted' "
			value="Volver a anuncios completados">
		<%
			Ads adRetired = (Ads) request.getAttribute("adRetired");
		%>
		<h2>
			Anuncio completado:
			<%=adRetired.getNameAd()%></h2>

		<div>
			<table>
				<tr>
					<td>Descripción:</td>
					<td><%=adRetired.getExplanation()%></td>
				</tr>
				<tr>
					<td>Estado:</td>
					<td><%=adRetired.getStatusItemTxt()%></td>
				</tr>
				<tr>
					<td>Fecha de publicación:</td>
					<td><%=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(adRetired.getDatePublished())%></td>
				</tr>
				<tr>
					<td>Estado del Post:</td>
					<td><%=adRetired.getStatusPostTxt().getTxt()%></td>
				</tr>
				<tr>
					<td>Fecha completado:</td>
					<td><%=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(adRetired.getDateEnd())%></td>
				</tr>
			</table>
		</div>

		<form action="matchesCompleted" method="get">
			<input type="submit" value="Ver propuestas completadas" />
		</form>
	</div>
</body>
</html>