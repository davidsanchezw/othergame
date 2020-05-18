<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Matches'%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-¿Proponer intercambio?</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">

	<h1>OtherGame</h1>
	<%
		Ads singleAd = (Ads) request.getAttribute("singleAd");
	%>
	<%
		Matches match = (Matches) request.getAttribute("match");
	%>
	<form action="matchViewOtherAdsOffered" method="get">
		<input type="hidden" name="idMatch" value=<%=match.getId()%> /> <input
			type="submit" value="Volver a los anuncios del otro usuario" />
	</form>
	<h2>Propuesta recibida del siguiente anuncio:</h2>
	<p>
		<b>Solicita tu artículo:</b>
		<%=match.getAd1().getNameAd()%></p>
	<p>
		<b>Descripción:</b>
		<%=match.getAd1().getExplanation()%></p>
	<p>
		<b>Estado:</b>
		<%=match.getAd1().getStatusItemTxt()%></p>
	<p>
		<b>Creado:</b>
		<%=match.getAd1().getDatePublished()%></p>

	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=match.getId()%> /> <input type="submit" value="Cancelar propuesta" />
	</form>

	<h3>¿Proponer intercambio con el siguiente artículo?</h3>
	<div>
		<form action="matchToPending" method="post">
			<input type="hidden" name="idAdToPending" value=<%=singleAd.getId()%> />
			<input type="hidden" name="idMatchToPending" value=<%=match.getId()%> />
			<table>
				<tr>
					<td>Nombre:</td>
					<td><%=singleAd.getNameAd()%></td>
				</tr>
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
			<input type="submit" value="Proponer intercambio" />
		</form>
	</div>

</body>
</html>