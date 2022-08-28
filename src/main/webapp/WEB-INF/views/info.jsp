<%--
  Created by IntelliJ IDEA.
  User: Let
  Date: 09.08.2022
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="errorPage.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Information</title>
    <link href="${pageContext.request.contextPath}/resources/css/info.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="main">

    <div class="header" id="head">
        <div class="inside-header">
            <div class="header-photo"></div>
        </div>
        <div>
            <ul id="navbar">
                <li><a href="/Tourism/" target="_self">Главная</a></li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="left_col">
            <div class="inside-left_col">
                <div class="left-caption">Виды спортивного туризма различаются:</div>
                <div class="div-genre">
                    <ul class="list-genre">
                        <li class="item-genre">По видам
                            <ul>
                                <li class="inside-item-genre"><a href="resources/img/62.JPG" style="color: #000066">Пешие
                                    походы</a></li>
                                <li class="inside-item-genre"><a href="resources/img/31.jpg" style="color: #000066">Лыжные
                                    походы</a></li>
                                <li class="inside-item-genre"><a href="resources/img/10.jpg" style="color: #000066">Горные
                                    походы</a></li>
                                <li class="inside-item-genre"><a href="resources/img/26.jpg" style="color: #000066">Водные
                                    походы</a></li>
                                <li class="inside-item-genre"><a href="resources/img/45.jpg" style="color: #000066">Спелеопоходы</a>
                                </li>
                                <li class="inside-item-genre"><a href="resources/img/14.jpg" style="color: #000066">Комбинированные
                                    походы</a></li>
                                <li class="inside-item-genre">И т.д.</li>
                            </ul>
                        </li>
                        <li class="item-genre">По возрастно-социальному признаку
                            <ul>
                                <li class="inside-item-genre">Детский туризм</li>
                                <li class="inside-item-genre">Юношеский туризм</li>
                                <li class="inside-item-genre">Взрослый туризм</li>
                                <li class="inside-item-genre">Семейный туризм</li>
                                <li class="inside-item-genre">Туризм для людей с ограниченными возможностями</li>
                            </ul>
                        </li>
                        <li class="item-genre">По направлению
                            <ul>
                                <li class="inside-item-genre">Путешествия</li>
                                <li class="inside-item-genre">Экстремальный туризм</li>
                                <li class="inside-item-genre">Дисциплина дистанция</li>
                                <li class="inside-item-genre">Короткие маршруты в классе спортивных походов</li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="right_col">
            <div class="inside-right_col">
                <h2>Виды спортивного туризма</h2>
                <a href="resources/img/8.jpg"> <img class="leftfoto" alt="Картинка" src="resources/img/8.jpg"></a>
                <p>Под спортивным туризмом понимают виды спорта, нацеленные на преодоление определенного отрезка
                    земной поверхности – маршрута. Земной поверхностью может быть не только расстояние
                    непосредственно на земле, но также на воде и над землей. Особенностью прохождения
                    маршрутов в спортивном туризме является преодоление различных специфических естественных
                    препятствий – горных вершин и перевалов в горном туризме, а также речных порогов при сплаве по
                    рекам. </p>
                <p>В современном мире не редко встречаешь различные виды путешествий. К таким относятся
                    оздоровительный туризм, медицинский, рекреационный, экстремальный, деловой и т.д.
                    Но и не стоит забывать о спортивном туризме. Он так же подразделяется на несколько видов:</p>
                <ul>
                    <li class="inside-item-genre">Пешеходный туризм - путешествие осуществляется пешком с преодолением
                        рельефно-ландшафтных препятствий.
                    </li>
                    <li class="inside-item-genre">Лыжный туризм - путешествие осуществляется в основном на лыжах,
                        преодоление препятствий по снегу и льду.
                    </li>
                    <li class="inside-item-genre">Горный туризм - это походы в горной местности с по долинам, тропам,
                        преодолением горных перевалов, подъёмы на вершины гор.
                    </li>
                    <li class="inside-item-genre">Вводный - это сплав по рекам на судах, часто в горах, преодоление
                        водных препятствий, преимущественно на плотах, катамаранах, рафтах и каяках.
                    </li>
                    <li class="inside-item-genre">Спелеотуризм - покорение пещер, преодоление их препятствий.</li>
                    <li class="inside-item-genre">Комбинированный туризм - это туристические походы с сочетанием
                        различных видов туризма.
                    </li>
                </ul>
                <p>Так же спортивный туризм делится по социально-возрастному признаку: детский, взрослый, семейный и
                    т.п. Поэтому каждый может подобрать себе необходимый вид путешествия. Конечно, существует ряд
                    трудностей такого туризма. Например, связанные с районом похода, сложностью маршрута, природные
                    факторы.</p>
                <p>В помощь туристам созданы специальные организации по спортивному туризму. Они способствуют правильной
                    организации движения туристов по тому или иному маршруту, разрабатывают эти маршруты, делают поход
                    комфортным и интересным, учат навыкам выживания в экстремальных условиях, преодоление препятствий.
                    Так же организовывают спортивные мероприятия, которые доступны всем слоям населения.</p>
                <p>Не малую роль в спортивном туризме играет снаряжение. Оно зависит от вида выбранного похода и
                    включает в себя спецодежду и обувь. Основные навыки, которыми должен обладать турист это: оказание
                    первой помощи, выбор места установки лагеря и временной стоянки, умение обращаться с верёвками и
                    техническими средствами, страховки и другие.</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
