<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 18.08.2022
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>OrdersCustomers</title>
</head>
<body>
<c:forEach var="customer" items="${ListOfCustomers}">
    <h2>${customer.id}. Клиент ${customer.name} </h2>
    <h3>Email: ${customer.email}</h3>
    <h3>Количество купленных туров: ${customer.participationNumber}</h3>


    <c:forEach var="order" items="${ListOfOrders}">
        <c:if test="${order.customerId == customer.id}">
            <c:forEach var="tour" items="${ListOfTour}">
                <c:if test="${order.tourId == tour.id}">
                    <table border="1" cellpadding="10" cellspacing="10">
                        <tr>
                            <th>Название</th>
                            <th>Цена</th>
                            <th>Цена со скидкой</th>
                            <th>Сложность</th>
                            <th>Тип</th>
                            <th>Удалить</th>
                        </tr>
                        <tr>
                            <td>${tour.name}</td>
                            <td>${tour.price}</td>
                            <td>${order.discountPrise}</td>
                            <c:forEach var="complexity" items="${ListOfComplexity}">
                                <c:if test="${complexity.id == tour.complexityId}">
                                    <td>${complexity.name}</td>
                                </c:if>
                            </c:forEach>
                            <c:set var="type"/>
                            <c:forEach var="variation" items="${ListOfVariation}">
                                <c:if test="${variation.tourId == tour.id}">
                                    <c:forEach var="typeOfTour" items="${ListOfTypeOfTour}">
                                        <c:if test="${variation.typeOfTourId == typeOfTour.id}">
                                            <c:set var="type" value="${type} - ${typeOfTour.name}"/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                            <td><c:out value="${type}"/></td>
                            <td><a href="/Tourism/removeOrder/${order.id}"
                                   onclick="return confirm('Do you really want to delete?')">Удалить</a></td>
                        </tr>
                        <br>
                    </table>
                </c:if>
            </c:forEach>


        </c:if>
    </c:forEach>
</c:forEach>

<p><a href="/Tourism/">Вернуться на главную</a></p>
</body>
</html>
