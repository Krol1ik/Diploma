<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.11.2021
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/static/css/catalog.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sport line</title>
</head>
<body>
<header class="header" id="header">
    <div class="container">
        <div class="headLineOne">
            <p class="number">+375 29 111 11 11</p>
            <a href="" class="viber">Viber</a>
            <a href="" class="telegram">Telegram</a>
            <p class="workTime">график работы <br> пн-пт: 9:00-18:00</p>
            <p class="lang">ENG</p>
        </div>
    </div>
    <hr class="topLine">
    <div class="container">
        <div class="headLineTwo">
            <p class="logo">SPORT LINE</p>
            <a href="/catalog" class="catalog">Каталог товаров</a>
            <input type="text" class="search" placeholder="поиск товаров">
            <a href="/basket" class="basket">Корзина</a>
            <a href="" class="log">Войти</a>
        </div>
    </div>
</header>
<section class="sectionOne">
    <div class="container">
        <div class="category">
            <a href="" class="btn">Турники</a>
            <a href="" class="btn">Шведские стены</a>
            <a href="" class="btn">Железо</a>
            <a href="" class="btn">Спортивные комплексы</a>
        </div>
    </div>
</section>
<section class="sectionTwo">
    <div class="container">
        <p>Физические упражнения – залог красивого тела и отличного самочувствия. Всем, у кого нет времени посещать спортзал, рекомендуем купить настенный турник и уделять занятиям всего несколько минут в день.</p>
        <div class="pageProduct">
            <div class="filter">

            </div>

            <div class="products">
<c:forEach items="${productList}" var="prod">
                <div class="prodouctStyle">
                    <img src="${prod.filename}" alt="No images" class="img">
                    <br>
                    <h4 class="nameProduct">${prod.category} ${prod.brand} ${prod.model}</h4>
                    <p class="nameProduct">Артикул: ${prod.article}</p>
                    <div class="by">
                        <h3 class="price">${prod.price} руб</h3>
                        <button type="submit" class="btnBy"><a href="/catalog/${prod.id}">В корзину</a></button>
                    </div>
                </div>
</c:forEach>
            </div>
        </div>
    </div>
</section>
<footer class="footer">
    <div class="container">
        <div class="footerLineOne">
            <p class="footerInfo">
                Режим работы интернет-магазина: с 9:00 до 18:00. Оформить заказ можно в любое время (круглосуточно)
            </p>
            <p class="footerInfo">
                Товары можно оплатить наличным и/или безналичным способом при его получении.
            </p>
            <p class="footerInfo">
                Способо доставка товара: самовывоз, доставка.</p>
        </div>
        <hr>
        <div class="container">
            <div class="footerRequisites">
                <p class="footerText">
                    Общество с ограниченной ответственностью "Спорт Лайн"
                </p>
                <p class="footerText">
                    Свидетельство №1237618726123 от 01.01.2001 выдно Минским городским исполкомом,
                </p>
                <p class="footerText">
                    УНП 12882711
                </p>
                <p class="footerText">
                    Юр.адрес: г. Минск, ул. Центральная, дом 11, офис 11
                </p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
