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
				<td><input type="email" name="uemail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,64}$" title="caracteres@caracteres.dominio , y como mucho de 64 caracteres" size="20" required></td>
			</tr>
			<tr>
				<td>Usuario:</td>
				<td><input type="text" name="usr" pattern="[A-Za-z0-9]{4,32}" title="Se validan letras y números, escriba al menos 4 caracteres, y como mucho 32" size=20 required></td>
			</tr>
			<tr>
				<td>Contraseña:</td>
				<td><input type="password" name="psw"
					pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}"
					title="Debe contener al menos un numero, una minúscula y una mayúcula, y como minimo 8 caracteres, y como mucho 20"
					size="20" required></td>
			</tr>			
			<tr>
				<td>Descripcion:</td>
				<td><input type="text" name="desc" pattern="[A-Za-z0-9]{10,255}" title="Se validan letras y números, escriba al menos 10 caracteres , y como mucho 255" size="30" required></td>
			</tr>
		</table>
		<input type="submit" value="Crear cuenta">
	</form>
	<input type="button" onclick=" window.location.href='index.jsp' "
		value="Ya tengo cuenta">

</body>
</html>