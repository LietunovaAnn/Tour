<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 13.08.2022
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Add Discount</title>
    <link href="${pageContext.request.contextPath}/resources/css/col.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="rar">

    <div class="header" id="head">
        <div class="inside-header">
            <div class="header-photo"></div>
        </div>
        <div>
            <ul id="navbar">
                <li><a href="/Tourism/" target="_self">Главная</a></li>
                <li><a href="/Tourism/viewAllDiscounts" target="_self">Назад</a></li>
            </ul>
        </div>
    </div>
    <div class="row">

        <div class="right_col">
            <div class="inside-right_col">
                <div class="right_col-text">
                    <h2>Введите данные скидки:</h2>
                    <form:form action="/Tourism/saveDiscount" method="post">
                        <table>
                            <form:hidden path="id"/>
                            <tr>
                                <td>Количество купленных туров:</td>
                                <td><form:input path="participationNumber"/>
                                    <form:errors path="participationNumber"/></td>
                            </tr>
                            <tr>
                                <td>Скидка(%):</td>
                                <td><form:input path="percent"/>
                                    <form:errors path="percent"/></td>
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
    </div>
</div>
</body>
</html>
