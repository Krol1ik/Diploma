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
            <a href="https://viber.click/375259624389" target="_blank" class="viber">Viber</a>
            <a href="https://t.me/krol1ik" target="_blank" class="telegram">Telegram</a>
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
            <input type="text" class="search" placeholder="<spring:message code="app.lang.search"/>">
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
<section class="chekorder">
    <div class="container">
<sec:authorize access="isAnonymous()">
        <div class="allOrderCheck">
            <h2 class="orderInfo"><spring:message code="app.lang.yourOrder"/>:</h2>
            <c:forEach items="${order}" var="ord">
            <h4 class="yourOrder">${ord.productOrder.category} ${ord.productOrder.brand} ${ord.productOrder.brand}, <spring:message code="app.lang.inQuantity"/> ${ord.count} <spring:message code="app.lang.totalCoast"/> ${ord.priceOrder} <spring:message code="app.lang.price"/>.</h4>
            </c:forEach>
            <h3 class="orderInfo"><spring:message code="app.lang.totalPrice"/>: ${finalPrice} <spring:message code="app.lang.price"/>.</h3>
            <h5 class="yourOrder"><spring:message code="app.lang.confirm"/></h5>
        </div>
    </div>
</section>
<section class="title">
    <div class="container">
        <div class="chekoutInfo">
            <h1 class="registrationOrder"><spring:message code="app.lang.orderYour"/></h1>
            <form method="post" action="/checkoutOrder">
            <div class="dataCostumer">
                <div class="firstName">
                    <spring:message code="app.lang.firstName"/>: <input type="text" class="inputsBlock" value="${customer.firstName}" name="firstName">
                </div>
                <div class="lastName">
                    <spring:message code="app.lang.lastName"/>: <input type="text" class="inputsBlock" value="${customer.lastName}" name="lastName">
                </div>
                <div class="phone">
                    <spring:message code="app.lang.phone"/>: <input type="phone" class="inputsBlock" value="${customer.phoneNumber}" name="phoneNumber">
                </div>
                <div class="email">
                    E-mail: <input type="email" class="inputsBlock" value="${customer.email}" name="email">
                </div>
                <div class="city">
                    <spring:message code="app.lang.city"/>: <input list="browsers" name="cityName" class="inputsBlock"/>
                    <datalist id="browsers" lass="inputsBlock">
                        <c:forEach items="${cityList}" var="city">
                        <option value="${city.cityName}">
                            </c:forEach>
                    </datalist>
                </div>
                <div class="street">
                    <spring:message code="app.lang.street"/>:<input type="text" class="inputsBlock" value="${address.street}" name="street">
                </div>
                <div class="numberHouse">
                    <spring:message code="app.lang.numberHouse"/>:<input type="text" class="inputsBlock" value="${address.numberHouse}" name="numberHouse">
                </div>
                <div class="btnOrder">
                    <button type="submit" class="sendOrder"><spring:message code="app.lang.makeOrder"/></button>
                </div>
            </div>
            </form>
        </div>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
        <div class="allOrderCheck">
            <h2 class="orderInfo"><spring:message code="app.lang.yourOrder"/>:</h2>
            <c:forEach items="${orderForUser}" var="ord">
                <h4 class="yourOrder">${ord.productOrder.category} ${ord.productOrder.brand} ${ord.productOrder.brand}, <spring:message code="app.lang.inQuantity"/> ${ord.count} <spring:message code="app.lang.totalCoast"/> ${ord.priceOrder} <spring:message code="app.lang.price"/></h4>
            </c:forEach>
            <h3 class="orderInfo"><spring:message code="app.lang.totalPrice"/>: ${finalPriceUser} <spring:message code="app.lang.price"/>.</h3>
            <h5 class="yourOrder"><spring:message code="app.lang.confirm"/></h5>
        </div>
    </div>
</section>
<section class="title">
    <div class="container">
        <div class="chekoutInfo">
            <h1 class="registrationOrder"><spring:message code="app.lang.orderYour"/></h1>
            <form method="post" action="/checkoutOrder/user">
                <div class="dataCostumer">
                    <div class="firstName">
                        <spring:message code="app.lang.firstName"/>: <input disabled type="text" class="inputsBlock" value="${user.firstName}" name="firstName">
                    </div>
                    <div class="lastName">
                        <spring:message code="app.lang.lastName"/>: <input disabled type="text" class="inputsBlock" value="${user.lastName}" name="lastName">
                    </div>
                    <div class="phone">
                        <spring:message code="app.lang.phone"/>: <input disabled type="phone" class="inputsBlock" value="${user.phoneNumber}" name="phoneNumber">
                    </div>
                    <div class="email">
                        E-mail: <input disabled type="email" class="inputsBlock" value="${user.email}" name="email">
                    </div>
                    <div class="city">
                        <spring:message code="app.lang.city"/>: <input disabled type="text" class="inputsBlock" value="${user.address.city.cityName}" name="cityName">
                    </div>
                    <div class="street">
                        <spring:message code="app.lang.street"/>:<input disabled type="text" class="inputsBlock" value="${user.address.street}" name="street">
                    </div>
                    <div class="numberHouse">
                        <spring:message code="app.lang.numberHouse"/>:<input disabled type="text" class="inputsBlock" value="${user.address.numberHouse}" name="numberHouse">
                    </div>
                    <a href="/profile"><p class="redactorProfile"><span style="color: red"><spring:message code="app.lang.important"/>:</span>
                        <spring:message code="app.lang.refactor"/>
                    </p></a>
                    <div class="btnOrder">
                        <button type="submit" class="sendOrder"><spring:message code="app.lang.makeOrder"/></button>
                    </div>
                </div>
            </form>
        </div>
        </sec:authorize>
    </div>
</section>
<footer class="footer">
    <div class="container">
        <div class="footerLineOne">
            <p class="footerInfo">
                <spring:message code="app.lang.footerInfoOne"/>
            </p>
            <p class="footerInfo">
                <spring:message code="app.lang.footerInfoTwo"/>
            </p>
            <p class="footerInfo">
                <spring:message code="app.lang.footerInfoThree"/>
            </p>
        </div>
        <hr>
        <div class="container">
            <div class="footerRequisites">
                <p class="footerText">
                    <spring:message code="app.lang.requisitesOne"/>
                </p>
                <p class="footerText">
                    <spring:message code="app.lang.requisitesTwo"/>
                </p>
                <p class="footerText">
                    <spring:message code="app.lang.requisitesThree"/>
                </p>
                <p class="footerText">
                    <spring:message code="app.lang.requisitesFor"/>
                </p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
