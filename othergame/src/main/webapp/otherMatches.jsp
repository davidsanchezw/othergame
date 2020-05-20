<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.StatusMatchTxt'%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<%@ page import='java.text.SimpleDateFormat'%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Propuestas otros</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<%
			Users otherUser = (Users) request.getAttribute("otherUser");
		%>
		<input type="button" onclick=" window.location.href='logout' "
			value="Cerrar sesión"> <input type="button"
			onclick=" window.location.href='principal' " value="Principal">
		<input type="button" onclick=" window.location.href='myprofile' "
			value="Mi perfil">
		<h1>OtherGame</h1>
	</div>
	<div class='cuerpo'>
		<h2>
			Propuestas completadas de
			<%=otherUser.getPublicName()%></h2>
		<form action="otherProfile" method="get">
			<input type="hidden" name="idOtherUser" value=<%=otherUser.getId()%> />
			<input type="submit"
				value="Volver al perfil de <%=otherUser.getPublicName()%>" />
		</form>

		<%
			List<Matches> matches = (List<Matches>) request.getAttribute("matches");
		if (matches.size() < 1) {
		%>
		<p>No hay propuestas disponibles</p>
		<%
			} else {
		%>
		<table>
			<tr>
				<td><h3>Anuncios de <%=otherUser.getPublicName()%></h3> </td>
				<td><h3>Anuncios de otros</h3></td>
			</tr>
			<%
				for (Matches match : matches) {
				if (match.getUsr1().getId() == otherUser.getId()) {
			%>

			<tr>
				<td><%=match.getAd2().getNameAd()%></td>
				<td><%=match.getAd1().getNameAd()%></td>
			</tr>
			<%
				} else {
			%>
			<tr>
				<td><%=match.getAd1().getNameAd()%></td>
				<td><%=match.getAd2().getNameAd()%></td>
			</tr>

			<%
				}
			}
			%>
		</table>
		<%
			}
		%>
	</div>
</body>
</html>