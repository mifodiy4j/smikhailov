<%@ page import="ru.job4j.application_2513.UserStore" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.application_2513.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/useradd" method="post">
    name : <input type="text" name="name"><br/>
    login : <input type="text" name="login"><br/>
    email : <input type="text" name="email"><br/>
    Create date (yyyy-MM-dd) : <input type="text" createDate="email"><br/>
    <input type="submit" value='Add new user'>
</form>

<br/>

<form action="<%=request.getContextPath()%>/useredit" method="post">
    id : <input type="text" name="id"><br/>
    name : <input type="text" name="name"><br/>
    <input type="submit" value='Edit'>
</form>

<br/>

<form action="<%=request.getContextPath()%>/userdelete" method="post">
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

    <%! final UserStore users = new UserStore(); %>
    <% List<Integer> listId = this.users.getListId(); %>
    <% for (int id : listId) {%>
        <% User user = this.users.selectById(id); %>

    <tr>
        <td><%=id%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
    </tr>
    <% } %>
</table>

</body>
</html>
