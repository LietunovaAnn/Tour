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
    <link href="/resources/css/info.css" rel="stylesheet" type="text/css"/>
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
            <div class="caption"><h2>Введите данные скидки:</h2></div>
            <div class="right_col-text">

                <form:form action="/Tourism/saveDiscount" method="post">
                    <table>
                        <form:hidden path="id"/>
                        <tr>
                            <td>Количество купленных туров:</td>
                            <td><form:input path="participationNumber"/></td>
                        </tr>
                        <tr>
                            <td>Скидка(%):</td>
                            <td><form:input path="percent"/></td>
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
