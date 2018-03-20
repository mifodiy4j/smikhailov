<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/useradd" method="post">
    name : <input type="text" name="name"><br/>
    login : <input type="text" name="login"><br/>
    email : <input type="text" name="email"><br/>
    Create date (yyyy-MM-dd) : <input type="text" createDate="email"><br/>
    <input type="submit" value='Add new user'>
</form>

<br/>

<form action="${pageContext.servletContext.contextPath}/useredit" method="post">
    id : <input type="text" name="id"><br/>
    name : <input type="text" name="name"><br/>
    <input type="submit" value='Edit'>
</form>

<br/>

<form action="${pageContext.servletContext.contextPath}/userdelete" method="post">
    id : <input type="text" name="id"><br/>
    <input type="submit" value='Delete'>
</form>

<br/>

<table style="border: 1p solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Create Date</th>
    </tr>

    <c:forEach items="${listId}" var="id">

    <tr>
        <td><c:out value="${id}"/></td>
        <td><c:out value="${users.selectById(id).name}"/></td>
        <td><c:out value="${users.selectById(id).login}"/></td>
        <td><c:out value="${users.selectById(id).email}"/></td>
        <td><c:out value="${users.selectById(id).createDate}"/></td>
    </tr>
    </c:forEach>
</table>

</body>
</html>