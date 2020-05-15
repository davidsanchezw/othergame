<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Mi perfil</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">


	<h1>OtherGame</h1>
	<h2>Mi perfil</h2>
	<p>Aquí puedes ver tus propuestas en proceso, propuestas finalizadas, y tus anuncios</p>
	<%
		Users me = (Users) session.getAttribute("me");
	%>
	<h2>Mis datos personales</h2>
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
			<td>Contraseña:</td>
			<td><%=me.getPass()%></td>
		</tr>

		<tr>
			<td>Descripción:</td>
			<td><%=me.getExplanation()%></td>
		</tr>
	</table>
	
	<h2>Mis propuestas</h2>
	
	<h3>En proceso</h3>
	<form action="matchesStarted" method="get">
		<input type="submit" value="Propuestas iniciadas" />
	</form>
	<form action="matchesReceived" method="get">
		<input type="submit" value="Propuestas recibidas" />
	</form>
	<form action="matchesPendingOther" method="get">
		<input type="submit"
			value="Propuestas respondidas, pendientes de otro" />
	</form>
	<form action="matchesPendingMe" method="get">
		<input type="submit" value="Propuestas pendientes de mí" />
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

	<h2>Mis anuncios</h2>
	<input type="button" onclick=" window.location.href='publish' "
		value="Publicar un anuncio">
	<input type="button" onclick=" window.location.href='adsAvaibles' "
		value="Ver mis anuncios disponibles">
	<input type="button" onclick=" window.location.href='adsRetired' "
		value="Ver mis anuncios retirados">
	<input type="button" onclick=" window.location.href='adsCompleted' "
		value="Ver mis anuncios Completados">

</body>
</html>