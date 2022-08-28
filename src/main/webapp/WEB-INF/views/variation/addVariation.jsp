<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 14.08.2022
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Add Variation</title>

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
                <li><a href="/Tourism/viewAllVariations" target="_self">Назад</a></li>
            </ul>
        </div>
    </div>
    <div class="row">

        <div class="right_col">
            <div class="inside-right_col">
                <div class="right_col-text">
                    <h2>Введите данные варианции тура:</h2>
                    <form:form action="/Tourism/saveVariation" method="post">
                        <table>
                            <form:hidden path="id"/>
                            <tr>
                                <td>№ Тура:</td>
                                <td><form:input path="name"/></td>
                            </tr>
                            <tr>
                                <td>№ Вид тура:</td>
                                <td><form:input path="price"/></td>
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
