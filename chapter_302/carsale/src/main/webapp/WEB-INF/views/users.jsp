<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Список пользователей</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

    <table border="1">
        <tr>
            <th>id</th>
            <th>login</th>
            <th>password</th>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="status">
            <tr valign="top">
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

