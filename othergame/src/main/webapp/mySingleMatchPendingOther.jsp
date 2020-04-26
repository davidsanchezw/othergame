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


	<h1>OtherGame</h1>
	<h2>Propuesta pendiente de respuesta del otro usuario</h2>
	<form action="matchesPendingOther" method="get">
		<input type="submit" value="Propuestas pendientes de otros" />
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
			<td>
				<p><%=singleMatch.getAd2().getNameAd()%></p>
			</td>
		</tr>
		<tr>
			<td>
				<form action="mySingleAd" method="post">
					<input type="hidden" name="idAd"
						value=<%=singleMatch.getAd1().getId()%> /> <input type="submit"
						value="Ver mi anuncio" />
				</form>
			</td>

			<td>
				<form action="singleAd" method="get">
					<input type="hidden" name="idAd"
						value=<%=singleMatch.getAd2().getId()%> /> <input type="submit"
						value="Ver anuncio" />
				</form>
			</td>
		</tr>
		<tr>			
			<td>
				<p><%=singleMatch.getUsr2().getPublicName()%></p>
			</td>
			<td>
				<p><%=singleMatch.getUsr1().getPublicName()%></p>
			</td>
		</tr>
		<tr>
			<td><input type="button"
				onclick=" window.location.href='myprofile' " value="Mi perfil">

			</td>
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