<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 13.08.2022
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Add Discount</title>
</head>
<body>
<h1>Создать новую скидку: </h1>
<form:form action="/Tourism/saveDiscount" method="post">
    <table>
        <form:hidden path="id"/>
        <tr>
            <td>Количество купленных туров:</td>
            <td><form:input path="participationNumber"/></td>
        </tr>
        <tr>
            <td>Скидка(%):</td>
            <td><form:input path="percent"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>
