<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>OtherGame-Login</title>
</head>
<body>

	<h1>OtherGame</h1>
	<h2>Introduce tu correo y contraseña</h2>

	<form action="check" method="post">

		<div class="container">
			<label for="uemail"><b>Email:</b></label> <input type="text"
				placeholder="Introduce tu email" name="uemail" required> <label
				for="psw"><b>Contraseña:</b></label> <input type="password"
				placeholder="Introduce tu contraseña" name="psw" required>

			<button type="submit">Entrar</button>
		</div>
	</form>
	<h2>¿Aún no formas parte de esta gran comunidad?</h2>
	<form action="register" method="get">
		<button type="submit">Registrarse</button>
	</form>
</body>