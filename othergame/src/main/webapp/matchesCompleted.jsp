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
<title>OtherGame-Mis completados</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión ">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">

	<h1>OtherGame</h1>
	<h2>Mis completados</h2>
	<%
		List<Matches> matches = (List<Matches>) request.getAttribute("matches");
	Users me = (Users) session.getAttribute("me");

	if (matches.size() < 1) {
	%>
	<p>No hay propuestas disponibles</p>
	<%
		} else {
	%>

	<%
		for (Matches match : matches) {
	%>
	<div>

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
				<td><b>Anuncio solicitado</b></td>
				<td><b>Anuncio solicitado</b></td>
			</tr>
			<tr>
				
				<td><%=match.getAd2().getNameAd()%></td>			
				
				<td><%=match.getAd1().getNameAd()%></td>
			</tr>			
		</tbody>
	</table>
	<div>
		<table>
			<tbody>
				<tr>
					<td><b>Creado: </b></td>
					<td><%=match.getDateStart()%></td>
				</tr>
				<%if(match.getDatePreEnd() != null){ %>
				<tr>
					<td><b>Respondido: </b></td>
					<td><%=match.getDatePreEnd()%></td>
				</tr>
				<%} %>
				<tr>
					<td><b>Fin: </b></td>
					<td><%=match.getDateEnd()%></td>
				</tr>
				<tr>
					<td>---------- </td>
					<td> </td>
				</tr>
			</tbody>
		</table>	
	</div>
		
		
	</div>
	<%
		}
	%>
	<%
		}
	%>

</body>
</html>