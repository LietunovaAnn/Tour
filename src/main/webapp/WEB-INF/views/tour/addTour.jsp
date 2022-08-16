<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 10.08.2022
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Add Tour</title>
</head>
<body>
<h1>Создать новый тур: </h1>
<form:form action="/Tourism/tour/saveTour" method="post">
    <table>
            <%--        <form:hidden path="id"/>--%>
        <tr>
            <td>Название:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Цена:</td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td>Сложность:</td>
            <td><form:input path="complexityId"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>
