<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.StatusPostTxt'%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Mi anuncio</title>
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
		Mi anuncio:
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
			<tr>
				<td>Estado del Post:</td>
				<td><%=singleAd.getStatusPostTxt().getTxt()%></td>
			<tr>
		</table>
	</div>
	<%
		if (singleAd.getStatusPostTxt().getId() == 1) {
	%>
	<div>
		<form action="adToRetired" method="post">
			<input type="hidden" name="idAdToRetired" value=<%=singleAd.getId()%> /> <input
				type="submit" value="Retirar anuncio" />
		</form>
	</div>

	<div>
		<input type="button" onclick=" window.location.href='adsAvaibles' "
			value="Mis anuncions disponibles">
	</div>
	<%
		} else if (singleAd.getStatusPostTxt().getId() == 2) {
	%>
	<div>
		<form action="adToRestored" method="get">
			<input type="hidden" name="idAdToRestored" value=<%=singleAd.getId()%> /> <input
				type="submit" value="Restaurar anuncio" />
		</form>
	</div>

	<div>
		<input type="button" onclick=" window.location.href='adsRetired' "
			value="Mis anuncions retirados">
	</div>
	<%
		} else {
	%>
	<input type="button" onclick=" window.location.href='adsCompleted' "
		value="Mis anuncions completados">
	<%
		}
	%>
</body>
</html>