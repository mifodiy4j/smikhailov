<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Hello, your login: ${login} status: ${role}</h3>

<br/>

<form action="${pageContext.servletContext.contextPath}/usereditname" method="post">
    <input type="hidden" name="id" value="${id}"/>
    name : <input type="text" name="name"><br/>
    <input type="submit" value='Edit'>
</form>

<br/>

<form action="${pageContext.servletContext.contextPath}/usereditlogin" method="post">
    <input type="hidden" name="id" value="${id}"/>
    login : <input type="text" name="login"><br/>
    <input type="submit" value='Edit'>
</form>

<br/>

<form action="${pageContext.servletContext.contextPath}/usereditemail" method="post">
    <input type="hidden" name="id" value="${id}"/>
    email : <input type="text" name="email"><br/>
    <input type="submit" value='Edit'>
</form>

<br/>

<table style="border: 1p solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <td>Name</td>
        <td><c:out value="${users.selectById(id).name}"/></td>
    </tr>

    <tr>
        <td>Login</td>
        <td><c:out value="${users.selectById(id).login}"/></td>
    </tr>

    <tr>
        <td>Email</td>
        <td><c:out value="${users.selectById(id).email}"/></td>
    </tr>

    <tr>
        <td>Create Date</td>
        <td><c:out value="${users.selectById(id).createDate}"/></td>
    </tr>

    <tr>
        <td>Password</td>
        <td><c:out value="${users.selectById(id).password}"/></td>
    </tr>

    <tr>
        <td>Role</td>
        <td><c:out value="${users.selectById(id).role}"/></td>
    </tr>

</table>

</body>
</html>