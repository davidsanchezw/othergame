<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='com.comweb.model.Users'%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Anuncio</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">
	<%
		Ads singleAd = (Ads) request.getAttribute("singleAd");
	%>
	<h1>
		Anuncio:
		<%=singleAd.getNameAd()%></h1>

	<div>
		<table>
			<tr>
				<td>Descripción:</td>
				<td><%=singleAd.getExplanation()%></td>
			</tr>
			<tr>
				<td>Estado:</td>
				<td><%=singleAd.getStatusItemTxt()%></td>
			<tr>
			<tr>
				<td>Fecha de publicación:</td>
				<td><%=singleAd.getDatePublished()%></td>
			<tr>
		</table>
	</div>
	<div>
		<form action="createMatch" method="post">
			<input type="hidden" name="idAd" value=<%=singleAd.getId()%> /> <input
				type="submit" value="Proponer intercambio" />
		</form>
	</div>

	<%
		Users simpleUser = (Users) request.getAttribute("simpleUser");
	%>
	<div>
		<form action="otherProfile" method="get">
			<input type="hidden" name="idOtherUser"
				value=<%=simpleUser.getId()%> />
			<h2>
				Usuario:
				<%=simpleUser.getPublicName()%></h2>
			<input type="submit" value="Ver perfil" />
		</form>
	</div>

</body>
</html>