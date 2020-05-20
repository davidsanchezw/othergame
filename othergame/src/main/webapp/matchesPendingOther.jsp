<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.StatusMatchTxt'%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Pendiente de otro usuario</title>
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
		<h2>Propuestas pendientes de respuesta del otro usuario</h2>
		<%
			List<Matches> matches = (List<Matches>) request.getAttribute("matches");
		if (matches.size() < 1) {
		%>
		<p>No hay propuestas disponibles</p>
		<%
			} else {
		%>
		<%
			for (Matches match : matches) {
		%>
		<table>
			<tr>
				<td><b>Te solicita:</b></td>
				<td><%=match.getAd1().getNameAd()%></td>
			<tr></tr>
			<td><b>Solicitas:</b></td>
			<td><%=match.getAd2().getNameAd()%></td>
			</tr>
		</table>
		<table class='linea'>
			<tr>
				<td><form action="matchView" method="get">
						<input type="hidden" name="idMatch" value=<%=match.getId()%> /> <input
							type="submit" value="Ver propuesta" />
					</form></td>
				<td><form action="matchtocancelled" method="post">
						<input type="hidden" name="idMatchToCancelled"
							value=<%=match.getId()%> /> <input type="submit"
							value="Cancelar propuesta" />
					</form></td>
			</tr>
		</table>
		<%
			}
		}
		%>
	</div>

</body>
</html>