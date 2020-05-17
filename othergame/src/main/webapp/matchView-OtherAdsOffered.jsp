<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Matches'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal ">
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión ">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">

	<h1>Articulos del otro usuario</h1>
	<%
		Matches match = (Matches) request.getAttribute("currentMatch");
	%>
	<p>Desea tu artículo: <%=match.getAd1().getNameAd()%></p>
	<p><%=match.toString()%></p>
	<form action="matchesReceived" method="get">
		<input type="submit" value="Propuestas recibidas" />
	</form>
	<form action="matchtocancelled" method="post">
		<input type="hidden" name="idMatchToCancelled"
			value=<%=match.getId()%> /> <input type="submit"
			value="Cancelar propuesta" />
	</form>

	<%
		List<Ads> ads = (List<Ads>) request.getAttribute("otherAds");
	if (ads.size() < 1) {
	%>
	<p>No hay anuncios disponibles</p>
	<%
		} else {
	%>

	<%
		for (Ads ad : ads) {
	%>
	<div>
			<p><%=ad.getNameAd()%></p>
		<form action="matchSimpleAdReceived" method="post">
			<input type="hidden" name="idAd" value=<%=ad.getId()%> /> <input
				type="hidden" name="idMatch" value=<%=match.getId()%> />

			<p><%=ad.toString()%></p>
			<input type="submit" value="Ver artículo" />
		</form>
	</div>
	<%
		}
	%>
	<%
		}
	%>


</body>
</html>