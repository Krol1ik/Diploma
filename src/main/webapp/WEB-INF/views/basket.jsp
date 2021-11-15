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
<link rel="stylesheet" href="/static/css/basket.css">
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
            <a href="catalog" class="catalog">Каталог товаров</a>
            <input type="text" class="search" placeholder="поиск товаров">
            <a href="/basket" class="basket">Корзина</a>
            <a href="" class="log">Войти</a>
        </div>
    </div>
</header>

<section class="basketList">
    <h1 class="textBasket">Корзина</h1>
    <div class="container">
        <div class="productList">
            <img src="img/турник1.jpg" alt="" class="img">
            <div class="infoProduct">
                <br>
                <p class="article">Артикул: 1</p><br><br><br>
                <h4 class="nameProduct">Турник Champion G-630/10</h4>
                <h5 class="quality">Кол-во: <input type="number" class="qualityInput" value="1"></h5>
                <h3 class="price">99 руб</h3>
                <a href="" class="del">Удалить из корзины</a>
            </div>
        </div>
        <div class="productList">
            <img src="img/турник1.jpg" alt="" class="img">
            <div class="infoProduct">
                <br>
                <p class="article">Артикул: 1</p><br><br><br>
                <h4 class="nameProduct">Турник Champion G-630/10</h4>
                <h5 class="quality">Кол-во: <input type="number" class="qualityInput" value="1"></h5>
                <h3 class="price">99 руб</h3>
                <a href="" class="del">Удалить из корзины</a>
            </div>
        </div>
        <a href="" class="btn">Оформить заказ</a>
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

