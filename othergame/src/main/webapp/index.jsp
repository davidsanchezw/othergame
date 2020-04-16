<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<h2>Login Form</h2>

<form action="check" method="post">

  <div class="container">
    <label for="uemail"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uemail" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>
        
    <button type="submit">Login</button>
  </div>
</form>
<form action="registro" method="post">
    <button type="submit">Registrarse</button>
</form>
</body>