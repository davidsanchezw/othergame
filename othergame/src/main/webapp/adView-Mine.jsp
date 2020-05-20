<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.StatusPostTxt'%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='java.util.List'%>
<%@ page import='java.text.SimpleDateFormat'%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Mi anuncio</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<input type="button" onclick=" window.location.href='logout' "
			value="Cerrar sesión"> <input type="button"
			onclick=" window.location.href='principal' " value="Principal">
		<input type="button" onclick=" window.location.href='myprofile' "
			value="Mi perfil">

		<h1>OtherGame</h1>
	</div>
	<div class='cuerpo'>
		<%
			Ads myAd = (Ads) request.getAttribute("myAd");
		%>
		<h2>
			Mi anuncio:
			<%=myAd.getNameAd()%></h2>
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
					<td><%=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(myAd.getDatePublished())%></td>
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


		<%
			List<Matches> matches1 = (List<Matches>) request.getAttribute("matches1");
		List<Matches> matches2 = (List<Matches>) request.getAttribute("matches2");
		List<Matches> matches3 = (List<Matches>) request.getAttribute("matches3");
		if (matches1.size() > 0 || matches2.size() > 0 || matches3.size() > 0) {
		%>
		<h2>Propuestas en curso</h2>
		<%
			} else {
		%>
		<h3>No existen propuestas en curso</h3>
		<%
			}
		if (matches1.size() > 0) {
		%>
		<h3>Tienes pendiente solicitar un articulo al otro usuario o
			cancelar en las siguientes propuestas:</h3>
		<%
			for (Matches match : matches1) {
		%>

		<p>
			<b>Solicitado por: </b><%=match.getUsr1().getPublicName()%></p>
		<table class='linea'>
			<tr>
				<td>
					<form action="matchView" method="get">
						<input type="hidden" name="idMatch" value=<%=match.getId()%> /> <input
							type="submit" value="Ver propuesta" />
					</form>
				</td>
				<td>
					<form action="matchtocancelled" method="post">
						<input type="hidden" name="idMatchToCancelled"
							value=<%=match.getId()%> /> <input type="submit"
							value="Cancelar propuesta" />
					</form>
				</td>
			</tr>
		</table>
		<%
			}

		}

		if (matches2.size() > 0) {
		%>
		<h3>Tienes pendiente confirmar o cancelar las siguientes
			propuestas:</h3>
		<%
			for (Matches match : matches2) {
		%>

		<p>
			<b>Solicitas: </b>
			<%=match.getAd1().getNameAd()%></p>
		<p>
			<b>Te solicita: </b>
			<%=match.getAd2().getNameAd()%></p>
		<table class='linea'>
			<tr>
				<td>
					<form action="matchView" method="get">
						<input type="hidden" name="idMatch" value=<%=match.getId()%> /> <input
							type="submit" value="Ver propuesta" />
					</form>
				</td>
				<td>
					<form action="matchtocancelled" method="post">
						<input type="hidden" name="idMatchToCancelled"
							value=<%=match.getId()%> /> <input type="submit"
							value="Cancelar propuesta" />
					</form>
				</td>
			</tr>
		</table>
		<%
			}

		}

		if (matches3.size() > 0) {
		%>
		<h3>A la espera de respuesta de las siguientes propuestas:</h3>
		<%
			for (Matches match : matches3) {
		%>

		<p>
			<b>Te solicita: </b>
			<%=match.getAd1().getNameAd()%></p>
		<p>
			<b>Solicitas: </b>
			<%=match.getAd2().getNameAd()%></p>

		<table class='linea'>
			<tr>
				<td><form action="matchView" method="get">
						<input type="hidden" name="idMatch" value=<%=match.getId()%> /> <input
							type="submit" value="Ver propuesta" />
					</form></td>
				<td><form action="matchtocancelled" method="post">
						<input type="hidden" name="idMatchToCancelled"
							value=<%=match.getId()%> /> <input type="submit"
							value="Cancelar propuesta" />
					</form></td>
			</tr>
		</table>




		<%
			}
		}
		%>
	</div>
</body>
</html>