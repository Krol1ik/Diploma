<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.11.2021
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/static/css/title.css">
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
            <a href="/" class="logo">SPORT LINE</a>
            <a href="/catalog" class="catalog">Каталог товаров</a>
            <input type="text" class="search" placeholder="поиск товаров">
            <sec:authorize access="isAnonymous()">
                <a href="/basket" class="basket">Корзина</a>

                <a href="/login" class="log">Войти</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="/basket/user" class="basket">Корзина</a>
                <select class="profile" onchange="window.location.href = this.options[this.selectedIndex].value">
                    <option>Профиль</option>
                    <option value="http://localhost:8080/profile">Личные данные</option>
                    <option value="http://localhost:8080/history">История заказов</option>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <option></option>
                        <option value="http://localhost:8080/admin/userList">Список пользователей</option>
                        <option value="http://localhost:8080/admin/addProduct">Добавить товар</option>
                    </sec:authorize>
                    <option></option>
                    <option value="http://localhost:8080/logout">Выход</option>
                </select>
            </sec:authorize>
        </div>
    </div>
</header>
<section class="title">
    <div class="container">
        <div class="benefistSection">
            <div class="oneDel">
                <h3 class="textDelivery">Бесплатная</h3>
                <h3 class="textDeliveryTwo">доставка</h3>
            </div>
            <div class="delivery">
                <h3 class="textGuarantee">Гарантия</h3>
                <h3 class="textGuaranteeTwo">18 месяцев</h3>
            </div>
            <div class="delivery">
                <h3 class="textGuarantee">Лучшее</h3>
                <h3 class="textQualityTwo">качество</h3>
            </div>
            <div class="delivery">
                <h3 class="textGuarantee">Доставляем</h3>
                <h3 class="deliveryBenefist">По всей Беларуси</h3>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="category">
            <a href="/catalog/category/${categoryList.get(0).id}" class="btn">Турники</a>
            <a href="/catalog/category/${categoryList.get(1).id}" class="btn">Шведские стены</a>
            <a href="/catalog/category/${categoryList.get(2).id}" class="btn">Железо</a>
            <a href="/catalog/category/${categoryList.get(3).id}" class="btn">Тренажеры</a>
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
