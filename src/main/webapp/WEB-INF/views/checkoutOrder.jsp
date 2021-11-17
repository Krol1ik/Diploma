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
<link rel="stylesheet" href="/static/css/checkoutOrder.css">
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
            <a href="" class="catalog">Каталог товаров</a>
            <input type="text" class="search" placeholder="поиск товаров">
            <a href="" class="basket">Корзина</a>
            <a href="" class="log">Войти</a>
        </div>
    </div>
</header>
<section class="title">
    <div class="container">
        <div class="chekoutInfo">
            <h1 class="registrationOrder">Оформление заказа</h1>
            <form method="post" action="/checkoutOrder">
            <div class="dataCostumer">
                <div class="firstName">
                    Имя: <input type="text" class="inputsBlock" value="${customer.firstName}" name="firstName">
                </div>
                <div class="lastName">
                    Фамилия: <input type="text" class="inputsBlock" value="${customer.lastName}" name="lastName">
                </div>
                <div class="phone">
                    Телефон: <input type="phone" class="inputsBlock" value="${customer.phoneNumber}" name="phoneNumber">
                </div>
                <div class="email">
                    E-mail: <input type="email" class="inputsBlock" value="${customer.email}" name="email">
                </div>
                <input type="hidden" value="${address.country.id}" name="country">
                <div class="city">
<%--                    Город: <select class="inputsBlock">--%>
<%--                    <c:forEach items="${cityList}" var="city">--%>
<%--                    <option value="${city.id}" name="cityId">${city.cityName}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>

                    Город: <input list="browsers" name="cityName" class="inputsBlock"/>
                    <datalist id="browsers" lass="inputsBlock">
                        <c:forEach items="${cityList}" var="city">
                        <option value="${city.cityName}">
                            </c:forEach>
                    </datalist>

                </div>
                <div class="street">
                    Улица:<input type="text" class="inputsBlock" value="${address.street}" name="street">
                </div>
                <div class="numberHouse">
                    Номер дома:<input type="text" class="inputsBlock" value="${address.numberHouse}" name="numberHouse">
                </div>
                <div class="btnOrder">
                    <button type="submit" class="sendOrder">Оформить заказ</button>
                </div>
            </div>
            </form>
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