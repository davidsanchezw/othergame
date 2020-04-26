<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='com.comweb.model.Users'%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Propuesta ajena</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión ">

	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">

	<h1>OtherGame</h1>
	<h2>Propuesta finalizada</h2>
	<%
		Matches singleMatch = (Matches) request.getAttribute("singleMatch");
	Users me = (Users) session.getAttribute("me");
	%>
	<p><%=singleMatch.toString()%></p>
	<p>Motivo: <%=singleMatch.getStatusMatchTxt().getTxt()%></p>
	<table>

		<tbody>
			<tr>
				<td><%=singleMatch.getAd1().getNameAd()%></td>
				<%
					if (singleMatch.getStatusMatchTxt().getId() == 2) {
				%>
				<td><%=singleMatch.getAd2().getNameAd()%></td>
				<%
					}
				%>
			</tr>
			<tr>
				<td><%=singleMatch.getUsr2().getPublicName()%></td>
				<td><%=singleMatch.getUsr1().getPublicName()%></td>
			</tr>
			<tr>

				<%
					if (me.getId() == singleMatch.getUsr2().getId()) {
				%>
				<td><input type="button" onclick=" window.location.href='myprofile' "
					value="Mi perfil"></td>
				<%
					} else {
				%>
				<td><form action="otherProfile" method="get">
						<input type="hidden" name="idOtherUser"
							value=<%=singleMatch.getUsr2().getId()%> /> <input type="submit"
							value="Ver perfil" />
					</form></td>
				<%
					}
				%>
				<%
					if (me.getId() == singleMatch.getUsr1().getId()) {
				%>
				<td><input type="button" onclick=" window.location.href='myprofile' "
					value="Mi perfil"></td>
				<%
					} else {
				%>
				<td><form action="otherProfile" method="get">
						<input type="hidden" name="idOtherUser"
							value=<%=singleMatch.getUsr1().getId()%> /> <input type="submit"
							value="Ver perfil" />
					</form></td>
				<%
					}
				%>
			</tr>

		</tbody>
	</table>

</body>
</html>