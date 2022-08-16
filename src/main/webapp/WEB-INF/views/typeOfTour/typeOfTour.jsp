<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 14.08.2022
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Type Of Tour</title>
</head>
<body>
<h2>Виды туров: </h2>
<table border="1" cellpadding="10" cellspacing="10">
    <tr>
        <th>№</th>
        <th>Название</th>
    </tr>

    <c:forEach var="typeOfTour" items="${ListOfTypeOfTour}">
        <tr>
            <td>${typeOfTour.id}</td>
            <td>${typeOfTour.name}</td>
            <td><a href="/Tourism/editTypeOfTour/${typeOfTour.id}">Изменить</a></td>
            <td><a href="/Tourism/removeTypeOfTour/${typeOfTour.id}">Удалить</a></td>
        </tr>
    </c:forEach>

</table>
<p><a href="/Tourism/addTypeOfTour">Добавить новый вид</a></p>
<p><a href="/Tourism/">Вернуться на главную</a></p>
</body>
</html>
