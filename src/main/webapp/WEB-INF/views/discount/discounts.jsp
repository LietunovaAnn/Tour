<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 07.08.2022
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Discount</title>
</head>
<body>
<h2>Скидки: </h2>
<table border="1" cellpadding="10" cellspacing="10">
    <tr>
        <th>№</th>
        <th>Количество посещенных туров</th>
        <th>Скидка</th>
        <th>Изменить</th>
        <th>Удалить</th>
    </tr>

    <c:forEach var="discount" items="${ListOfDiscounts}">
        <tr>
            <td>${discount.id}</td>
            <td>${discount.participationNumber}</td>
            <td>${discount.percent}</td>
            <td><a href="/Tourism/editDiscount/${discount.id}">Изменить</a></td>
            <td><a href="/Tourism/removeDiscount/${discount.id}">Удалить</a></td>
        </tr>
    </c:forEach>

</table>

<p><a href="index.jsp">Вернуться на главную</a></p>

</body>
</html>
