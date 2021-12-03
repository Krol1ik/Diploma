<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page isELIgnored = "false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.11.2021
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/static/css/login.css">
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
            <p class="workTime"><spring:message code="app.lang.schedule"/> <br> <spring:message code="app.lang.dayWeek"/>: 9:00-18:00</p>
            <div class="dropdown">
                <button class="dropbtn"><spring:message code="app.lang.change"/></button>
                <div class="dropdown-content">
                    <a href="?lang=en"><spring:message code="app.lang.english"/></a>
                    <a href="?lang=ru"><spring:message code="app.lang.russia"/></a>
                </div>
            </div>
        </div>
    </div>
    <hr class="topLine" style="margin-top: 10px">
    <div class="container">
        <div class="headLineTwo">
            <a href="/" class="logo">SPORT LINE</a>
            <a href="/catalog" class="catalog"><spring:message code="app.lang.catalog"/></a>
            <input type="text" class="search" placeholder="поиск товаров">
            <sec:authorize access="isAnonymous()">
                <a href="/basket" class="basket"><spring:message code="app.lang.basket"/></a>

                <a href="/login" class="log"><spring:message code="app.lang.log"/></a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="/basket/user" class="basket"><spring:message code="app.lang.basket"/></a>
                <select class="profile" onchange="window.location.href = this.options[this.selectedIndex].value">
                    <option><spring:message code="app.lang.profile"/></option>
                    <option value="http://localhost:8080/profile"><spring:message code="app.lang.personalData"/></option>
                    <option value="http://localhost:8080/history"><spring:message code="app.lang.history"/></option>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <option></option>
                        <option value="http://localhost:8080/admin/userList"><spring:message code="app.lang.list"/></option>
                        <option value="http://localhost:8080/admin/addProduct"><spring:message code="app.lang.addProd"/></option>
                    </sec:authorize>
                    <option></option>
                    <option value="http://localhost:8080/logout"><spring:message code="app.lang.logOut"/></option>
                </select>
            </sec:authorize>
        </div>
    </div>
</header>
<div class="container">
    <div class="inputForm">
        <div>
            <form action="/login" method="post">
            <h4 class="login">Логин: <input type="text" class="inputPlace" name="username"> </h4>
            <h4 class="pass">Пароль: <input type="password" class="inputPlace" name="password"> </h4>
            <div class="btnAction">
                <button type="submit" class="btn">Вход</button>
                <a href="/registration" class="btnReg">Регистрация</a>
            </div>
            </form>
        </div>
    </div>
</div>
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