<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dinh Phu
  Date: 6/24/2020
  Time: 8:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Table</title>
</head>
<body>
        <p>User: ${loginUser.name}</p>
        <p>Amount: ${loginUser.amount}</p>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>username</th>
                <th>amount</th>
                <th>Transfer</th>
            </tr>

                <c:forEach items="${userList}" var="user">
                    <c:if  test="${user.name != loginUser.name}">
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.username}</td>
                            <td>${user.amount}</td>
                            <td><a href="<c:url value="/home?action=transfer&id=${user.id}"/>">transfer Money</a></td>
                        </tr>
                    </c:if>
                </c:forEach>

        </table>
</body>
</html>
