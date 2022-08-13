<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 08.08.2022
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/demo/info.css"/> ">
</head>
<body>

<div class="row">

    <div class="center">
        <div class="inner-center">
            <form name="userform" action="/Tourism/tour?action=mainPage" method="post">
                <h2>Регистрация пользователей </h2>
                <fieldset id="inputs">
                    <input type="text" id="username" name="username" type="text" placeholder="Имя" autofocus required>

                    <input type="text" id="mail" name="mail" type="text" placeholder="Эл. почта" autofocus required>

                </fieldset>
                <fieldset id="actions">
                    <input type="submit" id="submit" value="РЕГИСТРАЦИЯ">

                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>
