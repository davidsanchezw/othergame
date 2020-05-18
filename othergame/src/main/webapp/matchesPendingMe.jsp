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
<title>OtherGame-Propuestas pentiendes de confirmar</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión ">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">
	<h1>OtherGame</h1>
	<h2>Propuestas pendientes de confirmar</h2>
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
			<td><b>Solicitas:</b></td>
			<td><%=match.getAd1().getNameAd()%></td>
		<tr></tr>
		<td><b>Te solicita:</b></td>
		<td><%=match.getAd2().getNameAd()%></td>
		</tr>

	</table>
	<div>
		<form action="matchViewConfirmation" method="get">
			<input type="hidden" name="idMatch" value=<%=match.getId()%> />
			<input type="submit"
				value="Revisar propuesta para confirmar o cancelar" />
		</form>
	</div>
	<%
		}
	%>
	<%
		}
	%>

</body>
</html>