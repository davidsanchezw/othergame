<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.StatusPostTxt'%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='java.util.List'%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Otro anuncio</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">
	<%
		Ads otherAd = (Ads) request.getAttribute("otherAd");
	%>
	<h1>
		Otro anuncio:
		<%=otherAd.getNameAd()%></h1>


	<div>
		<table>
			<tr>
				<td>Descripción:</td>
				<td><%=otherAd.getExplanation()%></td>
			</tr>
			<tr>
				<td>Estado:</td>
				<td><%=otherAd.getStatusItemTxt()%></td>
			<tr>
			<tr>
				<td>Fecha de publicación:</td>
				<td><%=otherAd.getDatePublished()%></td>
			<tr>
			<tr>
				<td>Estado del Post:</td>
				<td><%=otherAd.getStatusPostTxt().getTxt()%></td>
			<tr>
		</table>
	</div>

	<%
		Matches match1 = (Matches) request.getAttribute("match1");
	Matches match2 = (Matches) request.getAttribute("match2");

	if (match1 != null) {
	%>

	<h2>Propuesta con solo un artículo definido en curso</h2>

	<div>
		<form action="matchView" method="get">
			<input type="hidden" name="idMatch" value=<%=match1.getId()%> />
			<p><%=match1.toString()%></p>
			<input type="submit" value="Ver propuesta" />
		</form>
	</div>
	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=match1.getId()%> /> <input type="submit"
			value="Cancelar propuesta" />
	</form>

	<%
		} else if (match2 != null) {
	%>
	<h2>Propuesta con dos artículos definidos en curso</h2>
	<%
		
	%>
	<div>
		<form action="matchView" method="get">
			<input type="hidden" name="idMatch" value=<%=match2.getId()%> />
			<p><%=match2.toString()%></p>
			<input type="submit" value="Ver propuesta" />
		</form>
	</div>
	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=match2.getId()%> /> <input type="submit"
			value="Cancelar propuesta" />
	</form>

	<%
		} else {
	%>
	<div>
		<form action="createMatch" method="post">
			<input type="hidden" name="idAd" value=<%=otherAd.getId()%> /> <input
				type="submit" value="Proponer intercambio" />
		</form>
	</div>
	<%
		}
	%>
	
	<div>
		<%
			Users otherUser = (Users) request.getAttribute("otherUser");
		%>
		<h2><%=otherUser.getPublicName()%></h2>
		<form action="otherProfile" method="get">
			<input type="hidden" name="idOtherUser" value=<%=otherUser.getId()%> />
			<input type="submit" value="Ver perfil" />
		</form>
	</div>

</body>
</html>