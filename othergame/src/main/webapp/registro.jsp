<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Registro</title>
</head>
<body>
	<h1>OtherGame</h1>
	<h2>Registro</h2>
	<p>Introduce tus datos:</p>
	<form method="post" action="createUser">
		<table>
			<tr>
				<td>Email:</td>
				<td><input type="email" name="uemail" size="20" required></td>
			</tr>
			<tr>
				<td>Usuario:</td>
				<td><input type="text" name="usr" size="20" required></td>
			</tr>
			<tr>
				<td>Contraseña:</td>
				<td><input type="password" name="psw" size="20" required></td>
			</tr>
			<tr>
				<td>Repite la contraseña()Pendiente(Tambien pendiente pedir mejor contraseña):</td>
<!-- 				<td><input type="password" name="psw2" size="20" required></td> -->
			</tr>
			<tr>
				<td>Descripcion:</td>
				<td><input type="text" name="desc" size="20" required></td>
			</tr>
		</table>
		<input type="submit" value="Crear cuenta">
	</form>
	<input type="button" onclick=" window.location.href='index.jsp' "
		value="Ya tengo cuenta">

</body>
</html>