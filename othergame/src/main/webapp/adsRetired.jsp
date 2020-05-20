<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Mis retirados</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<input type="button" onclick=" window.location.href='logout' "
			value="Cerrar sesión"> <input type="button"
			onclick=" window.location.href='principal' " value="Principal">
		<input type="button" onclick=" window.location.href='myprofile' "
			value="Mi perfil"> <input type="button"
			onclick=" window.location.href='publish' "
			value="Publicar un anuncio">
		<h1>OtherGame</h1>
	</div>
	<div class='cuerpo'>
		<h2>Mis anuncios retirados</h2>

		<%
			List<Ads> adsRetired = (List<Ads>) request.getAttribute("adsRetired");
		if (adsRetired.size() < 1) {
		%>
		<p>No hay anuncios disponibles</p>

		<%
			} else {
		%>

		<%
			for (Ads adRetired : adsRetired) {
		%>

		<table>
			<tr>
				<td><%=adRetired.getNameAd()%></td>
			</tr>
		</table>
		<div class='linea'>
			<table>
				<tr>
					<td><form action="adViewRetired" method="post">
							<input type="hidden" name="idAd" value=<%=adRetired.getId()%> />
							<input type="submit" value="Ver" />
						</form></td>
					<td><form action="adToRestored" method="post">
							<input type="hidden" name="idAdToRestored"
								value=<%=adRetired.getId()%> /> <input type="submit"
								value="Restaurar" />
						</form></td>
				</tr>
			</table>
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