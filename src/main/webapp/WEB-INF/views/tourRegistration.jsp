<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 08.08.2022
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration</title>

    <link href="resources/css/info.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="row">

    <div class="center">
        <div class="inner-center">
            <h2>Выбранный тур: </h2>
            <table border="1" cellpadding="10" cellspacing="10">
                <tr>
                    <th>№</th>
                    <th>Название</th>
                    <th>Цена</th>
                    <th>Сложность</th>
                    <th>Тип</th>
                </tr>
                <tr>
                    <td>${tour.id}</td>
                    <td>${tour.name}</td>
                    <td>${tour.price}</td>
                    <td>${complexity.name}</td>
                    <c:set var="type"/>
                    <c:forEach var="variation" items="${ListVariation}">
                        <c:forEach var="typeOfTour" items="${ListOfTypeOfTour}">
                            <c:if test="${variation.typeOfTourId == typeOfTour.id}">
                                <c:set var="type" value="${type} - ${typeOfTour.name}"/>

                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    <td><c:out value="${type}"/></td>
                </tr>

            </table>

            <form:form action="/Tourism/addOrder" method="post" modelAttribute="customer">
                <h2>Регистрация на тур </h2>
                <table>
                    <form:hidden path="id"/>
                    <tr>
                        <td>Ваше имя:</td>
                        <td><form:input path="name"/></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><form:input path="email"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Save"/></td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
