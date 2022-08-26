<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 14.08.2022
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
<head>
    <title>Type Of Tour</title>
    <link href="resources/css/info.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="main">
    <div class="row">

        <div class="left_col">
            <div class="inside-left_col">
                <ol>
                    <li><a href="/Tourism/addTypeOfTour">Добавить новый вид</a></li>
                    <li><a href="/Tourism/">Вернуться на главную</a></li>
                    <li><a href="/Tourism/admin">Войти как администратор</a></li>
                </ol>
            </div>
        </div>
        <div class="right_col">
            <div class="inside-right_col">
                <div class="caption"><h2>Виды туров:</h2></div>
                <div class="right_col-text">

                    <table border="1" cellpadding="10" cellspacing="10">
                        <tr>
                            <th>№</th>
                            <th>Название</th>
                            <th>Изменить</th>
                            <th>Удалить</th>
                        </tr>

                        <c:forEach var="typeOfTour" items="${ListOfTypeOfTour}">
                            <tr>
                                <td>${typeOfTour.id}</td>
                                <td>${typeOfTour.name}</td>
                                    <%--            <security:authorize access="hasRole('ADMIN')">--%>
                                <td><a href="/Tourism/editTypeOfTour/${typeOfTour.id}">Изменить</a></td>
                                <td><a href="/Tourism/removeTypeOfTour/${typeOfTour.id}">Удалить</a></td>
                                    <%--            </security:authorize>--%>
                                    <%--            <security:csrfInput/>--%>
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
