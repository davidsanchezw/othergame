<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Publicar</title>
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
		<h2>Publicación</h2>
		<p>Crea tu anuncio</p>

		<form method="post" action="createAd">
			<table>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="name" pattern="[A-Za-z0-9\s.,¡!¿?]{3,32}"
						title="Se validan letras y números, escriba al menos 3 caracteres, y como mucho 32"
						size="20" required></td>
				</tr>
				<tr>
					<td>Descripcion:</td>
					<td><input type="text" name="desc"
						pattern="[A-Za-z0-9\s.,¡!¿?]{10,80}"
						title="Se validan letras y números, escriba al menos 10 caracteres, y como mucho 80"
						size="30" required></td>

				</tr>
				<tr>
					<td>Estado:</td>
					<td><select id="status" name="status">
							<option value="1">Nuevo</option>
							<option value="2">Como nuevo</option>
							<option value="3">Algo usado</option>
							<option value="4">Deteriorado</option>
					</select></td>
				</tr>
			</table>
			<input type="submit" value="Publicar anuncio">
		</form>
	</div>
</body>
</html>