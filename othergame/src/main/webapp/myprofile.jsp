<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Mi perfil</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<input type="button" onclick=" window.location.href='logout' "
			value="Cerrar sesión"> <input type="button"
			onclick=" window.location.href='principal' " value="Principal">
		<input type="button"
					onclick=" window.location.href='publish' "
					value="Publicar un anuncio">
		<h1>OtherGame</h1>
	</div>
	<div class='cuerpo'>
		<h2>Mi perfil</h2>
		<p>Aquí puedes ver tus propuestas en proceso, propuestas
			finalizadas, y tus anuncios</p>
		<%
			Users me = (Users) session.getAttribute("me");
			int quantity1 = (int)request.getAttribute("quantity1");
			int quantity2 = (int)request.getAttribute("quantity2");
		%>
		<h3>Mis datos personales</h3>
		<table>
			<tr>
				<td>Nickname:</td>
				<td><%=me.getPublicName()%></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><%=me.getEmail()%></td>
			</tr>
			<tr>
				<td>Descripción:</td>
				<td><%=me.getExplanation()%></td>
			</tr>
		</table>



		<h3>Mis propuestas</h3>

		<h3>En proceso</h3>
		<form action="matchesStarted" method="get">
			<input type="submit" value="Propuestas iniciadas" />
		</form>
		<form action="matchesReceived" method="get">
			<input type="submit" value="Propuestas recibidas (<%= quantity1 %>)" />
		</form>
		<form action="matchesPendingOther" method="get">
			<input type="submit"
				value="Propuestas respondidas, pendientes de otro" />
		</form>
		<form action="matchesPendingMe" method="get">
			<input type="submit" value="Propuestas pendientes de mí  (<%= quantity2 %>)" />
		</form>
		<h3>Finalizadas</h3>
		<form action="matchesCompleted" method="get">
			<input type="submit" value="Propuestas completadas" />
		</form>
		<form action="matchesCancelled" method="get">
			<input type="submit" value="Propuestas canceladas" />
		</form>
		<form action="matchesNonAvaibles" method="get">
			<input type="submit" value="Propuestas no disponibles" />
		</form>
		<h3>Mis anuncios</h3>
		<table>			
			<tr>
				<td><input type="button"
					onclick=" window.location.href='adsAvaibles' "
					value="Ver mis anuncios disponibles">
			<tr>
				<td><input type="button"
					onclick=" window.location.href='adsRetired' "
					value="Ver mis anuncios retirados">
			<tr>
				<td><input type="button"
					onclick=" window.location.href='adsCompleted' "
					value="Ver mis anuncios Completados">
		</table>

	</div>
</body>
</html>