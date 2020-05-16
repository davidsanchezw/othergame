<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Mis disponibles</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión ">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">	
	<input type="button" onclick=" window.location.href='publish' "
		value="Publicar un anuncio ">
<h1>OtherGame</h1>
<h2>Mis anuncios disponibles</h2>
	
	<%
		List<Ads> myAds = (List<Ads>) request.getAttribute("myAds");
	if (myAds.size() < 1) {
	%>
	<p>No hay anuncios disponibles</p>

	<%
		} else {
	%>

	<%
		for (Ads ad : myAds) {
	%>
	
	<table>
			<tr>
				<td><%=ad.getNameAd()%></td>
			</tr>			
		</table>
	<div>
		<form action="adView" method="get">
			<input type="hidden" name="idAd" value=<%=ad.getId()%> />
			<input type="submit" value="Ver" />
		</form>
		<form action="adToRetired" method="post">
			<input type="hidden" name="idAdToRetired" value=<%=ad.getId()%> />
			<input type="submit" value="Retirar" />
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