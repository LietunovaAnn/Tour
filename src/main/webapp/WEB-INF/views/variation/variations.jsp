<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 14.08.2022
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Variations</title>
</head>
<body>
<h2>Виды туров: </h2>
<table border="1" cellpadding="10" cellspacing="10">
    <tr>
        <th>№</th>
        <th>Название</th>
    </tr>

    <c:forEach var="variation" items="${ListOfVariation}">
        <tr>
            <td>${variation.tourId}</td>
            <td>${variation.typeOfTourId}</td>
            <td><a href="/Tourism/editVariation/${variation.tourId}">Изменить</a></td>
            <td><a href="/Tourism/removeVariation/${variation.tourId}">Удалить</a></td>
        </tr>
    </c:forEach>

</table>
<p><a href="/Tourism/addVariation">Добавить новую вариацию</a></p>
<p><a href="/Tourism/">Вернуться на главную</a></p>
</body>
</html>
