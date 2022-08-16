<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 13.08.2022
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
<h1>Создать нового покупателя: </h1>
<form:form action="/Tourism/saveCustomer" method="post">
    <table>
        <form:hidden path="id"/>
        <tr>
            <td>Имя:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Еmail:</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Количество курленных туров:</td>
            <td><form:input path="participationNumber"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>
