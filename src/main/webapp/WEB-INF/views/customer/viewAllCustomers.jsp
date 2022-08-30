<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 17.08.2022
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
    <title>Customer</title>
    <link href="${pageContext.request.contextPath}/resources/css/col.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="rar">

    <div class="header" id="head">
        <div class="inside-header">
            <div class="header-photo"></div>
        </div>
        <div>
            <ul id="navbar" style="top: auto">
                <li><a href="/Tourism/" target="_self">Главная</a></li>
                <li><a href="/Tourism/login">Aдмин</a></li>
                <security:authorize access="hasRole('ADMIN')">
                    <li><a href="/Tourism/viewAllCustomersAndOrders">Купленные туры клинтов</a></li>
                    <li><a href="/Tourism/customer/addCustomer">Добавить нового клиента</a></li>
                </security:authorize>
                <security:csrfInput/>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="right_col">
            <div class="inside-right_col">

                <div class="right_col-text">
                    <h2>Зарегистрированные клиенты:</h2>

                    <table cellpadding="10" cellspacing="10">
                        <tr>
                            <th>№</th>
                            <th>Имя</th>
                            <th>Еmail</th>
                            <th>Количество купленных туров</th>
                            <security:authorize access="hasRole('ADMIN')">
                            <th>Изменить</th>
                            <th>Удалить</th>
                        </security:authorize>
                        <security:csrfInput/>
                    </tr>

                    <c:forEach var="customer" items="${ListOfCustomers}">
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.name}</td>
                            <td>${customer.email}</td>
                            <td>${customer.participationNumber}</td>
                            <security:authorize access="hasRole('ADMIN')">
                                <td><a href="/Tourism/customer/editCustomer/${customer.id}">Изменить</a></td>
                                <td><a href="/Tourism/customer/removeCustomer/${customer.id}"
                                       onclick="return confirm('Do you really want to delete?')">Удалить</a></td>
                            </security:authorize>
                            <security:csrfInput/>
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
