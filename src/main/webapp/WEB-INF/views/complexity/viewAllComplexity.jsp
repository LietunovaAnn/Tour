<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 07.08.2022
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
    <title>Complexity</title>
</head>
<body>
<h2>Виды сложности: </h2>
<table border="1" cellpadding="10" cellspacing="10">
    <tr>
        <th>№</th>
        <th>Наименование</th>
        <th>Изменить</th>
        <th>Удалить</th>
    </tr>

    <c:forEach var="сomplexity" items="${ListOfComplexity}">
        <tr>
            <td>${сomplexity.id}</td>
            <td>${сomplexity.name}</td>
            <security:authorize access="hasRole('ADMIN')">
                <td><a href="/Tourism/editComplexity/${сomplexity.id}">Изменить</a></td>
                <td><a href="/Tourism/removeComplexity/${сomplexity.id}">Удалить</a></td>
            </security:authorize>
            <security:csrfInput/>
        </tr>
    </c:forEach>

</table>
<security:authorize access="hasRole('ADMIN')">
    <p><a href="/Tourism/addComplexity">Добавить новый вид</a></p>
</security:authorize>
<security:csrfInput/>
<p><a href="/Tourism/">Вернуться на главную</a></p>
</body>
</html>