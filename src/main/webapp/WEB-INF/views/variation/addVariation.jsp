<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 14.08.2022
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Add Variation</title>
</head>
<body>
<h1>Создать новую варианцию тура: </h1>
<form:form action="/Tourism/saveVariation" method="post">
    <table>
            <%--        <tr>--%>
            <%--            <td>Enter id:</td>--%>
            <%--            <td><form:input path="id"/></td>--%>
            <%--        </tr>--%>
        <tr>
            <td>№ Тура:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>№ Вид тура:</td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>
