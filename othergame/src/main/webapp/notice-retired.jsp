<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Aviso Retirado</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class='titulo'>
		<input type="button" onclick=" window.location.href='logout' "
			value="Cerrar sesi�n"> <input type="button"
			onclick=" window.location.href='principal' " value="Principal">
		<input type="button" onclick=" window.location.href='myprofile' "
			value=" Mi perfil ">
		<h1>Anuncio retirado</h1>
	</div>
	<div class='cuerpo'>
		<p>Puedes seguir administrando tus anuncios disponibles</p>
		<input type="button" onclick=" window.location.href='adsAvaibles' "
			value="Ver mis anuncios disponibles">
		<p>Puedes restaurar tus anuncios retirados, pero no se pueden
			recuperar las propuestas relacionadas creadas previamente</p>
		<input type="button" onclick=" window.location.href='adsRetired' "
			value="Ver mis anuncios retirados">
	</div>
</body>
</html>