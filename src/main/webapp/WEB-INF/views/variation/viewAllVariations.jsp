<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 14.08.2022
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page errorPage="../errorPage.jsp" %>
<html>
<head>
    <title>Variations</title>

    <link href="${pageContext.request.contextPath}/resources/css/info.css" rel="stylesheet" type="text/css"/>
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
                    <li><a href="/Tourism/tour/addTour">Добавить новый тур</a></li>
                </security:authorize>
                <security:csrfInput/>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="right_col">
            <div class="inside-right_col">
                <div class="right_col-text">
                    <h2>Виды туров:</h2>
                    <table border="1" cellpadding="10" cellspacing="10">
                        <tr>
                            <th>№</th>
                            <th>Название</th>
                            <th>Изменить</th>
                            <th>Удалить</th>
                        </tr>
                        <c:forEach var="variation" items="${ListOfVariation}">
                            <tr>
                                <td>${variation.tourId}</td>
                                <td>${variation.typeOfTourId}</td>
                                <security:authorize access="hasRole('ADMIN')">
                                    <td><a href="/Tourism/editVariation/${variation.tourId}">Изменить</a></td>
                                    <td><a href="/Tourism/removeVariation/${variation.tourId}">Удалить</a></td>
                                </security:authorize>
                                <security:csrfInput/>
                            </tr>
                        </c:forEach>

                    </table>
                    <security:authorize access="hasRole('ADMIN')">
                        <p><a href="/Tourism/addVariation">Добавить новую вариацию</a></p>
                    </security:authorize>
                    <security:csrfInput/>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
