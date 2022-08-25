<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Welcome page</title>
    <style>
        <%@include file="/resources/css/info.css" %>
    </style>
</head>
<body>
<div class="right_col">
    <div class="inside-right_col">
        <div class="caption"><h2>Application menu:</h2></div>
        <div class="right_col-text">
            <ol>
                <li><a href="/Tourism/tour/viewAllTours">Подобрать поход</a></li>
                <li><a href="/Tourism/viewAllDiscounts">Скидки</a></li>
                <li><a href="/Tourism/viewAllComplexity">Сложность походов</a></li>
                <li><a href="/Tourism/viewAllTypeOfTour">Виды туров</a></li>
                <li><a href="/Tourism/viewAllVariations">ы</a></li>
                <li><a href="/Tourism/customer/viewAllCustomers">Клиенты</a></li>
                <li><a href="/Tourism/info">Информация</a></li>
                <li><a href="/Tourism/admin">Войти как администратор</a></li>
            </ol>
        </div>
    </div>
</div>
</body>
</html>