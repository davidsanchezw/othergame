<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.StatusPostTxt'%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='java.util.List'%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Otro anuncio</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">
		
	<h1>OtherGame</h1>
	<%
		Ads otherAd = (Ads) request.getAttribute("otherAd");
	%>
	<h2>
		Otro anuncio:
		<%=otherAd.getNameAd()%></h2>

	<div>
		<table>
			<tr>
				<td>Descripción:</td>
				<td><%=otherAd.getExplanation()%></td>
			</tr>
			<tr>
				<td>Estado:</td>
				<td><%=otherAd.getStatusItemTxt()%></td>
			<tr>
			<tr>
				<td>Fecha de publicación:</td>
				<td><%=otherAd.getDatePublished()%></td>
			<tr>
			<tr>
				<td>Estado del Post:</td>
				<td><%=otherAd.getStatusPostTxt().getTxt()%></td>
			<tr>
		</table>
	</div>

	<%
		Users otherUser = (Users) request.getAttribute("otherUser");
	Matches match1 = (Matches) request.getAttribute("match1");
	Matches match2 = (Matches) request.getAttribute("match2");
	Matches match3 = (Matches) request.getAttribute("match3");

	if (match1 != null) {
	%>
	<div>
		<h3>Pendiente de que el usuario te solicite un articulo a cambio</h3>
		<form action="matchView" method="get">
			<input type="hidden" name="idMatch" value=<%=match1.getId()%> /> <input
				type="submit" value="Ver propuesta" />
		</form>
	</div>
	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=match1.getId()%> /> <input type="submit"
			value="Cancelar propuesta" />
	</form>

	<%
		} else if (match2 != null) {
	%>
	<h3>Tienes pendiente confirmar o cancelar la siguiente propuesta:</h3>
	<p>
		<b>Solicitas: </b>
		<%=match2.getAd1().getNameAd()%></p>
	<p>
		<b>Te solicita: </b>
		<%=match2.getAd2().getNameAd()%></p>
	<div>
		<form action="matchView" method="get">
			<input type="hidden" name="idMatch" value=<%=match2.getId()%> /> <input
				type="submit" value="Ver propuesta" />
		</form>
	</div>
	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=match2.getId()%> /> <input type="submit"
			value="Cancelar propuesta" />
	</form>
	<%
		} else if (match3 != null) {
	%>

	<h3>A la espera de respuesta de la siguiente propuesta:</h3>
	<p>
		<b>Te solicita: </b>
		<%=match3.getAd1().getNameAd()%></p>
	<p>
		<b>Solicitas: </b>
		<%=match3.getAd2().getNameAd()%></p>

	<div>
		<form action="matchView" method="get">
			<input type="hidden" name="idMatch" value=<%=match3.getId()%> /> <input
				type="submit" value="Ver propuesta" />
		</form>
	</div>
	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=match3.getId()%> /> <input type="submit"
			value="Cancelar propuesta" />
	</form>


	<%
		} else {
	%>
	<div>
		<form action="createMatch" method="post">
			<input type="hidden" name="idAd" value=<%=otherAd.getId()%> /> <input
				type="submit" value="Proponer intercambio" />
		</form>
	</div>
	<%
		}
	%>

	<div>
		<h3>Propietario: <%=otherUser.getPublicName()%></h3>
		<form action="otherProfile" method="get">
			<input type="hidden" name="idOtherUser" value=<%=otherUser.getId()%> />
			<input type="submit" value="Ver perfil" />
		</form>
	</div>

</body>
</html>