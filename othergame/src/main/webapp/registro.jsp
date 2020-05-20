<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Registro</title>
<link rel="stylesheet" type="text/css" href="styleform.css">
</head>
<body>
	<form autocomplete='off' class='form' method="post" action="createUser">
		<div class='control'>
			<h1>OtherGame</h1>
			<h2>Registro</h2>
		</div>
		<div class='control block-cube block-input'>
			<input type="email" name="uemail"
				pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,64}$"
				title="caracteres@caracteres.dominio , y como mucho de 64 caracteres"
				placeholder='Email' size="20" required>			
			<div class='bg-top'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg-right'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg'>
				<div class='bg-inner'></div>
			</div>
		</div>
		<div class='control block-cube block-input'>
			<input type="text" name="usr" pattern="[A-Za-z0-9]{4,32}"
				title="Se validan letras y números, escriba al menos 4 caracteres, y como mucho 32"
				placeholder='Usuario' size=20 required>
			<div class='bg-top'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg-right'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg'>
				<div class='bg-inner'></div>
			</div>
		</div>
		<div class='control block-cube block-input'>
			<input type="password" name="psw"
				pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}"
				title="Debe contener al menos un numero, una minúscula y una mayúcula, y como minimo 8 caracteres, y como mucho 20"
				placeholder='Contraseña' size="20" required>
			<div class='bg-top'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg-right'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg'>
				<div class='bg-inner'></div>
			</div>
		</div>
		<div class='control block-cube block-input'>
			<input type="text" name="desc" pattern="[A-Za-z0-9]{10,255}"
				title="Se validan letras y números, escriba al menos 10 caracteres , y como mucho 255"
				placeholder='Descripción' size="30" required>
			<div class='bg-top'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg-right'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg'>
				<div class='bg-inner'></div>
			</div>
		</div>
		<button class='btn block-cube block-cube-hover' type="submit"
			type='button'>
			<div class='bg-top'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg-right'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg'>
				<div class='bg-inner'></div>
			</div>
			<div class='text'>Registrarse</div>
		</button>
		<button class='btn block-cube block-cube-hover' type='button'
			onclick=" window.location.href='index.jsp'">
			<div class='bg-top'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg-right'>
				<div class='bg-inner'></div>
			</div>
			<div class='bg'>
				<div class='bg-inner'></div>
			</div>
			<div class='text'>Ya tengo cuenta</div>
		</button>
	</form>
</body>
</html>

