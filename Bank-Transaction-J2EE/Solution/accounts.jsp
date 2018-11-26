<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accounts Summary</title>
</head>
<body>
<center>
  <h3>Welcome,</h3> ${uname} <br><br>
  <b>Checking Account: </b> 
  $${chekbal}
  <b>Savings Account: </b> 
  $${savbal} <br>
  <form action = "Transfer" method="post">
  <table>
    <tr>
      <td>From</td>
      <td>
        <select name="from">
          <option value="Checking Account">Checking Account</option>
          <option value="Savings Account">Savings Account</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>To</td>
      <td>
        <select name="to">
          <option value="Checking Account">Checking Account</option>
          <option value="Savings Account">Savings Account</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Amount</td>
      <td><input type="text" name="amt"/></td>
    </tr>
    </table>
    <input type="submit" value="Transfer"/>
    </form>
</center>
</body>
</html>