<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 09.08.2022
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Information</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/demo/info.css"/> ">
</head>
<body>
<div class="row">

    <div class="left_col">
        <div class="inside-left_col">

            <form id="login" method="post" action="index.php">
                <h3> Авторизация</h3>
                <fieldset id="inputs">
                    <input id="username" name="login" type="text" placeholder="Логин" autofocus required>
                    <input id="password" name="password" type="password" placeholder="Пароль" required>
                </fieldset>
                <fieldset id="actions">
                    <input type="submit" id="submit" value="ВОЙТИ">
                </fieldset>
            </form>

        </div>
    </div>
</div>
</body>
</html>
