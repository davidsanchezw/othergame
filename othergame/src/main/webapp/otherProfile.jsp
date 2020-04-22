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
	<%
		Users otherUser = (Users) request.getAttribute("otherUser");
	%>	
	<h1>Perfil de <%=otherUser.getPublicName() %></h1>
	
	
		<p><%=otherUser.toString()%></p>
		
		<h2>Propuestas</h2>
	
	<form action="otherMatches" method="get"> 
				<input type="hidden" name="idUser" value=<%=otherUser.getId()%> />
	 <input type="submit"
			value="Propuestas completadas" />

	</form>

<h2>Anuncios disponibles</h2>
	
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