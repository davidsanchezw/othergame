<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<%@ page import='java.text.SimpleDateFormat'%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Artículos del otro usuario</title>
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
		<form action="matchesReceived" method="get">
			<input type="submit" value="Ir a propuestas recibidas" />
		</form>

		<h2>Propuesta recibida del siguiente anuncio:</h2>

		<%
			Matches match = (Matches) request.getAttribute("currentMatch");
		%>
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
			<%=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(match.getAd1().getDatePublished())%></p>

		<form action="matchtocancelled" method="post">
			<input type="hidden" name="idMatchToCancelled"
				value=<%=match.getId()%> /> <input type="submit"
				value="Cancelar propuesta" />
		</form>
		<h2>Artículos del otro usuario</h2>
		<%
			List<Ads> ads = (List<Ads>) request.getAttribute("otherAds");
		if (ads.size() < 1) {
		%>
		<p>No tiene artículos disponibles, tal vez en un rato publique el
			artículo que dispone</p>
		<%
			} else {
		%>

		<%
			for (Ads ad : ads) {
		%>
		<div class='linea'>
			<p><%=ad.getNameAd()%></p>
			<form action="matchSimpleAdReceived" method="post">
				<input type="hidden" name="idAd" value=<%=ad.getId()%> /> <input
					type="hidden" name="idMatch" value=<%=match.getId()%> /> <input
					type="submit" value="Ver artículo" />
			</form>
		</div>
		<%
			}
		%>
		<%
			}
		%>
	</div>

</body>
</html>