<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 08.08.2022
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h3>
    Извините за ошибку<br>
    Проблемы c сервером
</h3>

<p>
    Причина <%exception.getCause();%>
</p>
<p>
    Детали <%exception.printStackTrace(response.getWriter());%>
</p>

</body>
</html>
