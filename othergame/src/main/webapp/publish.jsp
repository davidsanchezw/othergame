<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="createAd">
Nombre:<input type="text" name="name" size="20"><br>
Descripcion:<input type="text" name="desc" size="20"><br>
Estado:<select id="status" name="status">
  <option value="1">Nuevo</option>
  <option value="2">Como nuevo</option>
  <option value="3">Algo usado</option>
  <option value="4">Deteriorado</option>
</select>
<input type="submit" value="confirmar">
</form>
    <input type="button" onclick=" window.location.href='principal' " value="Principal">
</body>
</html>