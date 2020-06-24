<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dinh Phu
  Date: 6/24/2020
  Time: 8:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--    <c:redirect url="/home"/>--%>
    <c:forEach items="${message}" var="m">
      <p>${m}</p>
    </c:forEach>
    <form method="POST" action="home">
      <input type="hidden" name="action" value="login">
      User Name: <input type="text" name="username"><br>
      Password: <input type="password" name="password"><br>
      <button>Login</button>
    </form>
  </body>
</html>
