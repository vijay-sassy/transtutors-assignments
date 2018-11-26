<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<center>
  <h1><b>Welcome to Vijay Bank!</b></h1>
  <form action = "LoginValidator" method="post">
    <table>
    <tr>
      <td>Username</td>
      <td><input type="text" name="user"/></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="password" name="pass"/></td>
    </tr>
    </table>
    <input type="submit" value="submit"/>
  </form>
</center>
</body>
</html>