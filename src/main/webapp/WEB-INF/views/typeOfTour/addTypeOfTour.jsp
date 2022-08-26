<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 14.08.2022
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Type Of Tour</title>
    <style>
        <%@include file="/resources/css/info.css" %>
    </style>
</head>
<body>
<div class="row">

    <div class="left_col">
        <div class="inside-left_col">
            <li><a href="/Tourism/">Вернуться на главную</a></li>
        </div>
    </div>
    <div class="right_col">
        <div class="inside-right_col">
            <div class="caption"><h2>Введите данные видa тура:</h2></div>
            <div class="right_col-text">

                <form:form action="/Tourism/saveTypeOfTour" method="post">
                    <table>
                        <form:hidden path="id"/>
                        <tr>
                            <td>Название:</td>
                            <td><form:input path="name"/></td>
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
</body>
</html>
