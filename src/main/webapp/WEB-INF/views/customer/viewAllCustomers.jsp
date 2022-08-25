<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 17.08.2022
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<h2>Зарегистрированные клиенты: </h2>
<table border="1" cellpadding="10" cellspacing="10">
    <tr>
        <th>№</th>
        <th>Имя</th>
        <th>Еmail</th>
        <th>Количество купленных туров</th>
        <th>Изменить</th>
        <th>Удалить</th>
    </tr>

    <c:forEach var="customer" items="${ListOfCustomers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td>${customer.participationNumber}</td>
            <td><a href="/Tourism/editCustomer/${customer.id}">Изменить</a></td>
            <td><a href="/Tourism/removeCustomer/${customer.id}">Удалить</a></td>
        </tr>
    </c:forEach>

</table>
<p><a href="/Tourism/order/viewAllCustomersAndOrders">Купленные туры клинтов</a></p>
<p><a href="/Tourism/customer/addCustomer">Добавить нового клиента</a></p>
<p><a href="/Tourism/">Вернуться на главную</a></p>
</body>
</html>
