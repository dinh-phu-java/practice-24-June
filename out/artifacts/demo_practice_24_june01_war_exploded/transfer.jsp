<%--
  Created by IntelliJ IDEA.
  User: Dinh Phu
  Date: 6/24/2020
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer</title>
</head>
<body>
<p>From ${sessionScope.loginUser.name}</p>
<p>Transfer to: ${transferUser.name}</p>

<form action="transfer" method="post">
    Amount <input type="text" name="amount">
    <button>Transfer</button>
</form>
</body>
</html>
