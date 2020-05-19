<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OtherGame-Login</title>
<link rel="stylesheet" type="text/css" href="styleform.css">
</head>
<body>

	<form autocomplete='off' class='form' action="checkLogin" method="post">
		<div class='control'>
			<h1>OtherGame</h1>
			<h2>Autenticación</h2>
		</div>

		<div class='control block-cube block-input'>
			<input type="text" class="form-control"
				placeholder="Introduce tu email" name="uemail" required>
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
			<input type="password" class="form-control"
				placeholder="Introduce tu contraseña" name="psw" required>
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
			<div class='text'>Entrar</div>
		</button>

		<button class='btn block-cube block-cube-hover' type='button'
			onclick=" window.location.href='register'">
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

	</form>

</body>
</html>