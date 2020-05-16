<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.StatusPostTxt'%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='java.util.List' %>



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
		Ads myAd = (Ads) request.getAttribute("myAd");
	%>
	<h1>
		Mi anuncio:
		<%=myAd.getNameAd()%></h1>


	<div>
		<table>
			<tr>
				<td>Descripción:</td>
				<td><%=myAd.getExplanation()%></td>
			</tr>
			<tr>
				<td>Estado:</td>
				<td><%=myAd.getStatusItemTxt()%></td>
			<tr>
			<tr>
				<td>Fecha de publicación:</td>
				<td><%=myAd.getDatePublished()%></td>
			<tr>
			<tr>
				<td>Estado del Post:</td>
				<td><%=myAd.getStatusPostTxt().getTxt()%></td>
			<tr>
		</table>
	</div>

	<div>
		<form action="adToRetired" method="post">
			<input type="hidden" name="idAdToRetired" value=<%=myAd.getId()%> />
			<input type="submit" value="Retirar anuncio" />
		</form>
	</div>


	<h1>Propuestas en curso</h1>
	<%
		List<Matches> matches1 = (List<Matches>) request.getAttribute("matches1");
	if (matches1.size() < 1) {
	%>
	<p>No hay propuestas con un artículo definido en curso</p>
	<%
		} else {
	%>
	<h2>Propuestas con solo un artículo definido</h2>
	<%
		for (Matches match : matches1) {
	%>
	<div>
		<form action="mySingleMatchStarted" method="post">
			<input type="hidden" name="idMatch" value=<%=match.getId()%> />
			<p><%=match.toString()%></p>
			<input type="submit" value="Ver propuesta" />
		</form>
	</div>
	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=match.getId()%> /> <input type="submit"
			value="Cancelar propuesta" />
	</form>
	<%
		}
	%>
	<%
		}
	%>
	
	<%
		List<Matches> matches2 = (List<Matches>) request.getAttribute("matches2");
	if (matches2.size() < 1) {
	%>
	<p>No hay propuestas con dos artículos definidos en curso</p>
	<%
		} else {
	%>
	<h2>Propuestas con dos artículos definidos en curso</h2>
	<%
		for (Matches match : matches2) {
	%>
	<div>
		<form action="mySingleMatchStarted" method="post">
			<input type="hidden" name="idMatch" value=<%=match.getId()%> />
			<p><%=match.toString()%></p>
			<input type="submit" value="Ver propuesta" />
		</form>
	</div>
	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=match.getId()%> /> <input type="submit"
			value="Cancelar propuesta" />
	</form>
	<%
		}
	%>
	<%
		}
	%>

</body>
</html>