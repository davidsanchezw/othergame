<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='com.comweb.model.Users'%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Confirmación</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<input type="button" onclick=" window.location.href='logout' "
			value="Cerrar sesión "> <input type="button"
			onclick=" window.location.href='principal' " value="Principal">
		<input type="button" onclick=" window.location.href='myprofile' "
			value="Mi perfil">
		<h1>OtherGame</h1>
	</div>
	<div class='cuerpo'>
		<h2>Confirmación de propuesta</h2>
		<%
			Matches match = (Matches) request.getAttribute("match");
		Users me = (Users) session.getAttribute("me");
		%>

		<table>
			<thead>
				<tr>
					<td><b>Inicia propuesta</b></td>
					<td><b>Recibe propuesta</b></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><%=match.getUsr1().getPublicName()%></td>
					<td><%=match.getUsr2().getPublicName()%></td>
				</tr>
				<tr>
					<%
						if (me.getId() == match.getUsr1().getId()) {
					%>
					<td><input type="button"
						onclick=" window.location.href='myprofile' " value="Mi perfil"></td>
					<%
						} else {
					%>
					<td><form action="otherProfile" method="get">
							<input type="hidden" name="idOtherUser"
								value=<%=match.getUsr1().getId()%> /> <input type="submit"
								value="Ver perfil" />
						</form></td>
					<%
						}
					%>
					<%
						if (me.getId() == match.getUsr2().getId()) {
					%>
					<td><input type="button"
						onclick=" window.location.href='myprofile' " value="Mi perfil"></td>
					<%
						} else {
					%>
					<td><form action="otherProfile" method="get">
							<input type="hidden" name="idOtherUser"
								value=<%=match.getUsr2().getId()%> /> <input type="submit"
								value="Ver perfil" />
						</form></td>
					<%
						}
					%>

				</tr>
				<tr>
					<td>Anuncio solicitado</td>
					<td>Anuncio solicitado</td>
				</tr>
				<tr>
					<%
						if (match.getStatusMatchTxt().getId() == 2) {
					%>
					<td><%=match.getAd2().getNameAd()%></td>
					<%
						} else {
					%>
					<td>Pendiente</td>
					<%
						}
					%>
					<td><%=match.getAd1().getNameAd()%></td>
				</tr>
				<tr>


					<%
						if (match.getStatusMatchTxt().getId() == 2) {
					%>
					<td>
						<form action="adView" method="get">
							<input type="hidden" name="idAd"
								value=<%=match.getAd2().getId()%> /> <input type="submit"
								value="Ver" />
						</form>
					</td>
					<%
						} else {
					%>
					<td></td>
					<%
						}
					%>
					<td><form action="adView" method="get">
							<input type="hidden" name="idAd"
								value=<%=match.getAd1().getId()%> /> <input type="submit"
								value="Ver" />
						</form></td>
				</tr>
			</tbody>
		</table>
		<table>
			<tr>
				<td><form action="matchtocompleted" method="post">
						<input type="hidden" name="idMatchToCompleted"
							value=<%=match.getId()%> /> <input type="submit"
							value="Confirmar" />
					</form></td>
				<td><form action="matchtocancelled" method="post">
						<input type="hidden" name="idMatchToCancelled"
							value=<%=match.getId()%> /> <input type="submit"
							value="Cancelar" />
					</form></td>
			</tr>
		</table>


	</div>
</body>
</html>