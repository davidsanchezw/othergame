<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='com.comweb.model.Users'%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión ">

	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">



	<h1>Propuesta simple</h1>
	<form action="matchesStarted" method="get">
		<input type="submit" value="Propuestas iniciadas" />
	</form>
	<%
		Matches singleMatch = (Matches) request.getAttribute("singleMatch");
	%>
	<p><%=singleMatch.toString()%></p>
	<table>
		<tr>
			<td>
				<p><%=singleMatch.getAd1().getNameAd()%></p>
			</td>
		</tr>
		<tr>
			<td>
				<form action="adView" method="get">
					<input type="hidden" name="idAd"
						value=<%=singleMatch.getAd1().getId()%> /> <input type="submit"
						value="Ver anuncio" />
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<p><%=singleMatch.getUsr2().getPublicName()%></p>
			</td>
		</tr>
		<tr>
			<td>
				<form action="otherProfile" method="get">
					<input type="hidden" name="idOtherUser"
						value=<%=singleMatch.getUsr2().getId()%> /> <input type="submit"
						value="Ver perfil" />
				</form>
			</td>
		</tr>
	</table>
	<p>Si has cambiado de opinión puedes cancelar la propuesta:</p>
	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=singleMatch.getId()%> /> <input type="submit"
			value="Cancelar propuesta" />
	</form>

</body>
</html>