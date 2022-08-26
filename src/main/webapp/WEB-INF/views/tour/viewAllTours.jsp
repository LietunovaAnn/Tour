<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 07.08.2022
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tours</title>
    <link href="resources/css/info.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
    <div class="row">

        <div class="left_col">
            <div class="inside-left_col">
                <ol>
                    <li><a href="/Tourism/tour/addTour">Добавить новый тур</a></li>
                    <li><a href="/Tourism/">Вернуться на главную</a></li>
                    <li><a href="/Tourism/admin">Войти как администратор</a></li>
                </ol>
            </div>
        </div>
        <div class="right_col">
            <div class="inside-right_col">
                <div class="caption"><h2>Туры:</h2></div>
                <div class="right_col-text">

                    <table border="1" cellpadding="10" cellspacing="10">
                        <tr>
                            <th>№</th>
                            <th>Название</th>
                            <th>Цена</th>
                            <th>Сложность</th>
                            <th>Тип</th>
                            <th>Заказать</th>
                            <security:authorize access="hasRole('ADMIN')">
                                <th>Изменить</th>
                                <th>Удалить</th>
                            </security:authorize>
                            <security:csrfInput/>

                        </tr>
                        <c:forEach var="tour" items="${ListOfTours}">
                            <tr>
                                <td>${tour.id}</td>
                                <td>${tour.name}</td>
                                <td>${tour.price}</td>
                                <c:forEach var="complexity" items="${ListOfComplexity}">
                                    <c:if test="${complexity.id == tour.complexityId}">
                                        <td>${complexity.name}</td>
                                    </c:if>
                                </c:forEach>
                                <c:set var="type"/>
                                <c:forEach var="variation" items="${ListOfVariation}">
                                    <c:if test="${variation.tourId == tour.id}">


                                        <c:forEach var="typeOfTour" items="${ListOfTypeOfTour}">
                                            <c:if test="${variation.typeOfTourId == typeOfTour.id}">
                                                <c:set var="type" value="${type} - ${typeOfTour.name}"/>

                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                                <td><c:out value="${type}"/></td>
                                <td><a href="/Tourism/tourRegistration/${tour.id}">Выбрать</a></td>
                                <security:authorize access="hasRole('ADMIN')">
                                    <td><a href="/Tourism/tour/editTour/${tour.id}">Изменить</a></td>
                                    <td><a href="/Tourism/tour/removeTour/${tour.id}"
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
