<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Publicar</title>
</head>
<body>
	<input type="button" onclick=" window.location.href='logout' "
		value="Cerrar sesión ">
	<input type="button" onclick=" window.location.href='myprofile' "
		value="Mi perfil">
	<input type="button" onclick=" window.location.href='principal' "
		value="Principal">
	<h1>OtherGame</h1>
	<h2>Publicación</h2>
	<p>Crea tu anuncio</p>

	<form method="post" action="createAd">
		<table>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="name" size="20" required></td>
			</tr>
			<tr>
				<td>Descripcion:</td>
				<td><input type="text" name="desc" size="20" required></td>

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
</body>
</html>