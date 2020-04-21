<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.Users'%>
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
	<h1>Mi perfil de usuario</h1>
	<%
		Users me = (Users) session.getAttribute("me");
	%>
	
		<p><%=me.toString()%></p>
		
		<h2>Propuestas</h2>
	
	<form action="matchesStarted" method="get"> <input type="submit"
			value="Propuestas iniciadas" />
	</form>
	<form action="matchesReceived" method="get"> <input type="submit"
			value="Propuestas recibidas" />
	</form>
	<form action="matchesPendingOther" method="get"> <input type="submit"
			value="Propuestas respondidas, pendientes de otro" />
	</form>
	<form action="matchesPendingMe" method="get"> <input type="submit"
			value="Propuestas pendientes de mí" />
	</form>
	<form action="matchesCompleted" method="get"> 
	 <input type="submit"
			value="Propuestas completadas" />
	</form>
	<form action="matchesCancelled" method="get"> 
	<input type="submit"
			value="Propuestas canceladas" />
	</form>
	<form action="matchesNonAvaible" method="get">
	<input type="submit"
			value="Propuestas no disponibles" />
	</form>

<h2>Mis anuncios disponibles</h2>
	<input type="button" onclick=" window.location.href='publish' "
		value="Publicar un anuncio ">
	<%
		List<Ads> ads = (List<Ads>) request.getAttribute("ads");
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
		<form action="singleAd" method="get">
			<input type="hidden" name="idAd" value=<%=ad.getId()%> />
			<p><%=ad.toString()%></p>
			<input type="submit" value="Ver" />
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