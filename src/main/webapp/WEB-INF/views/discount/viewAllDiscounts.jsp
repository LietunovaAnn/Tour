<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 07.08.2022
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
<head>
    <title>Discount</title>
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
                    <li><a href="/Tourism/addDiscount">Добавить новую скидку</a></li>
                </security:authorize>
                <security:csrfInput/>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="right_col">
            <div class="inside-right_col">
                <div class="right_col-text">
                    <<h2>Скидки:</h2></div>

                <table border="1" cellpadding="10" cellspacing="10">
                    <tr>
                        <th>№</th>
                        <th>Количество посещенных туров</th>
                        <th>Скидка</th>
                        <security:authorize access="hasRole('ADMIN')">
                            <th>Изменить</th>
                            <th>Удалить</th>
                        </security:authorize>
                        <security:csrfInput/>
                    </tr>

                    <c:forEach var="discount" items="${ListOfDiscounts}">
                        <tr>
                            <td>${discount.id}</td>
                            <td>${discount.participationNumber}</td>
                            <td>${discount.percent}</td>
                            <security:authorize access="hasRole('ADMIN')">
                                <td><a href="/Tourism/editDiscount/${discount.id}">Изменить</a></td>
                                <td><a href="/Tourism/removeDiscount/${discount.id}">Удалить</a></td>
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
