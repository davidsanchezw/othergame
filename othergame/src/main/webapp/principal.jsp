<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import='com.comweb.model.Users'%>
<%@ page import='com.comweb.model.Ads'%>
<%@ page import='java.util.List'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Principal</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<input type="button" onclick=" window.location.href='logout' "
			value="Cerrar sesi�n"> <input type="button"
			onclick=" window.location.href='myprofile' " value="Mi perfil">
		<h1>OtherGame</h1>
	</div>
	<div class='cuerpo'>
		<h2>Principal</h2>
		
		<%
			Users me = (Users) session.getAttribute("me");
			int quantity1 = (int)request.getAttribute("quantity1");
			int quantity2 = (int)request.getAttribute("quantity2");
			if(quantity1 + quantity2 > 0){
		%>
		<h3>Tareas: <%=quantity1 + quantity2 %> (Dir�gete a mi perfil)</h3>
		
		<%} %>
		
		<p>
			�Qu� est�s buscando hoy,
			<%=me.getPublicName()%>?
		</p>

		<form method="get" action="search">
			<input id='texto' type="hidden" name="page" value="0"> <input
				type="text" name="search" pattern="[A-Za-z0-9]{4,32}"
				placeholder='Escribe tu b�squeda'
				title="Se validan letras y n�meros, escriba al menos 4 caracteres, y como mucho 32"
				size="20" required> <input type="submit" value="Buscar">
		</form>

		<p>�Algo que ofrecer?</p>
		<input type="button" onclick=" window.location.href='publish' "
			value="Publicar un anuncio ">

		<h2>�ltimos anuncios disponibles</h2>

		<%
			List<Ads> ads = (List<Ads>) request.getAttribute("principalAds");
		if (ads.size() < 1) {
		%>
		<p>No hay anuncios disponibles</p>
		<%
			} else {
			int actualPage = (int) request.getAttribute("page");
			int quantity = (int) request.getAttribute("quantity");
		%>

		<p>
			Mostrando anuncios
			<%=actualPage * 10 + 1%>
			a
			<%
			if (quantity > actualPage * 10 + 1 + 9) {
		%><%=(actualPage * 10 + 1 + 9)%>
			<%
				} else {
			%><%=quantity%>
			<%
				}
			%>
			de un total de
			<%=quantity%>
		</p>
		<table>
			<%
				for (Ads ad : ads) {
			%>

			<tr>
				<td><%=ad.getNameAd()%></td>
				<td><div>
						<form action="adView" method="get">
							<input type="hidden" name="idAd" value=<%=ad.getId()%> /><input
								type="submit" value="Ver" />
						</form>
					</div></td>
			</tr>

			<%
				}
			%>
		</table>
		<table>
			<tr>
				<td>
					<%
						if (actualPage != 0) {
					%>
					<form method="get" action="moreResults">
						<input type="hidden" name="page" value=<%=actualPage - 1%>>
						<input type="submit" value="Anterio p�g.">
					</form> <%
 	}
 %>
				</td>
				<td>
					<%
						if (quantity - (10 * actualPage) > 10) {
					%>
					<form method="get" action="moreResults">
						<input type="hidden" name="page" value=<%=actualPage + 1%>>
						<input type="submit" value="Siguiente p�g.">
					</form> <%
 	}
 %>
				</td>
			</tr>
		</table>
		<p>
			P�gina:
			<%=actualPage%></p>
		<%
			}
		%>
	</div>
</body>
</html>