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
    <link href="resources/css/info.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
    <div class="row">

        <div class="left_col">
            <div class="inside-left_col">
                <ol>
                    <li><a href="/Tourism/viewAllCustomersAndOrders">Купленные туры клинтов</a></li>
                    <li><a href="/Tourism/customer/addCustomer">Добавить нового клиента</a></li>
                    <li><a href="/Tourism/">Вернуться на главную</a></li>
                    <li><a href="/Tourism/admin">Войти как администратор</a></li>
                </ol>
            </div>
        </div>
        <div class="right_col">
            <div class="inside-right_col">
                <div class="caption"><h2>Зарегистрированные клиенты:</h2></div>
                <div class="right_col-text">
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
                                <td><a href="/Tourism/customer/editCustomer/${customer.id}">Изменить</a></td>
                                <td><a href="/Tourism/customer/removeCustomer/${customer.id}">Удалить</a></td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
