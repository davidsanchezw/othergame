<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='com.comweb.model.StatusMatchTxt'%>
<%@ page import='com.comweb.model.Matches'%>
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
	<%
		Users otherUser = (Users) request.getAttribute("otherUser");
	%>
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión ">
	<form action="otherProfile" method="get">
		<input type="hidden" name="idOtherUser" value=<%=otherUser.getId()%> />
		<input type="submit" value="Volver al perfil de <%=otherUser.getPublicName()%>" />
	</form>
	<h1><%=request.getAttribute("title")%></h1>
	<%
		List<Matches> matches = (List<Matches>) request.getAttribute("matches");
	if (matches.size() < 1) {
	%>
	<p>No hay propuestas disponibles</p>
	<%
		} else {
	%>

	<%
		for (Matches match : matches) {
	%>
	<div>
		<form action="singleMatch" method="get">
			<input type="hidden" name="idMatch" value=<%=match.getId()%> />
			<p><%=match.toString()%></p>
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