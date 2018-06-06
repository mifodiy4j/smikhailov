<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Список пользователей</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<div class="container">
    <h2>Список пользователей</h2>
<form action="${pageContext.servletContext.contextPath}/users.do" method="post">
    name : <input type="text" name="name"><br/>
    <input type="submit"><br/>
</form>
<table border="1">
    <tr>
        <th>Имя</th>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="status">
        <tr valign="top">
            <td>${user.name}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

