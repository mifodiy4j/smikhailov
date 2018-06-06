<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Список машин</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

    <table border="1">
        <tr>
            <th>id</th>
            <th>Brand</th>
            <th>Model</th>
            <th>Body</th>
            <th>Year of manufacture</th>
            <th>Mileage</th>
            <th>Transmission</th>
            <th>Engine</th>
            <th>Description</th>
            <th>Sold</th>
            <th>Author</th>
            <th>Foto</th>
            <th>Create date</th>
        </tr>
        <c:forEach items="${cars}" var="car" varStatus="status">
            <tr valign="top">
                <td>${car.id}</td>
                <td>${car.brand}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

