<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Error Registro</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<h1>OtherGame</h1>
	</div>
	<div class='cuerpo'>
		<h2>Error en el registro</h2>
		<%
			int errorTxt = (int) request.getAttribute("errorTxt");
		if (errorTxt == 1) {
		%>
		<p>Email existente</p>
		<%
			} else if (errorTxt == 2) {
		%>
		<p>Nombre de usuario existente</p>
		<%
			}
		%>
		<input type="button" onclick=" window.location.href='registro.jsp' "
			value="Registro">
	</div>
</body>
</html>