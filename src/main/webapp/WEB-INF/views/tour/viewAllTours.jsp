<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 07.08.2022
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tours</title>
</head>
<body>
<h2>Туры: </h2>
<table border="1" cellpadding="10" cellspacing="10">
    <tr>
        <th>№</th>
        <th>Название</th>
        <th>Цена</th>
        <th>Сложность</th>
        <th>Тип</th>
        <th>Дополнительно</th>
        <th>Заказать</th>
        <th>Изменить</th>
        <th>Удалить</th>

    </tr>


    <c:forEach var="tour" items="${ListOfTours}">
        <c:forEach var="complexity" items="${getComplexity}">


            <tr>
                <td>${tour.id}</td>
                <td>${tour.name}</td>
                <td>${tour.price}</td>
                <td>${complexity.name}</td>
                <c:forEach var="typeOfTour" items="${ListOfTypeOfTour}">
                    <td>${typeOfTour.name}</td>
                </c:forEach>
                <td><a href="/Tourism/tour/chooseTour/${tour.id}">Выбрать</a></td>
                <td><a href="/Tourism/tour/editTour/${tour.id}">Изменить</a></td>
                <td><a href="/Tourism/tour/removeTour/${tour.id}"
                       onclick="return confirm('Do you really want to delete?')">Удалить</a></td>
            </tr>
        </c:forEach>

    </c:forEach>
</table>
<p><a href="/Tourism/tour/addTour">Добавить новый тур</a></p>
<p><a href="/Tourism/">Вернуться на главную</a></p>
</body>
</html>
