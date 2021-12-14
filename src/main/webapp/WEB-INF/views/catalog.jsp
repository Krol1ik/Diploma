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
<link rel="stylesheet" href="/static/css/catalog.css">
<script src="/static/js/main.js" />"></script>
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
            <img src="/static/img/viber.png" alt="" class="messages">
            <a href="https://t.me/krol1ik" target="_blank" class="telegram">Telegram</a>
            <img src="/static/img/telegram.png" alt="" class="messages">
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
            <div class="imgForButton">
                <a href="/catalog">
                    <img src="/static/img/catalog.png" alt="" class="btnImg">
                </a>
                <a href="/catalog" class="catalog"><spring:message code="app.lang.catalog"/></a>
            </div>
            <input type="text" class="search" placeholder="<spring:message code="app.lang.search"/>">
            <sec:authorize access="isAnonymous()">
                <div class="imgForButton">
                    <a href="/basket">
                        <img src="/static/img/basket.png" alt="" class="btnImg">
                    </a>
                    <a href="/basket" class="basket"><spring:message code="app.lang.basket"/></a>
                </div>
                <div class="imgForButton">
                    <a href="/login">
                        <img src="/static/img/signIn.png" alt="" class="btnImg" style="margin-left: 3px">
                    </a>
                    <a href="/login" class="log"><spring:message code="app.lang.log"/></a>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="imgForButton">
                    <a href="/basket">
                        <img src="/static/img/basket.png" alt="" class="btnImg">
                    </a>
                    <a href="/basket/user" class="basket"><spring:message code="app.lang.basket"/></a>
                </div>
                <div class="imgForButton">
                    <img src="/static/img/profile.png" alt="" class="btnImg">
                    <select class="profile" onchange="window.location.href = this.options[this.selectedIndex].value">
                        <option><spring:message code="app.lang.profile"/></option>
                        <option value="http://localhost:8080/profile"><spring:message code="app.lang.personalData"/></option>
                        <option value="http://localhost:8080/history"><spring:message code="app.lang.history"/></option>
                        <sec:authorize access="hasAuthority('ADMIN')">
                            <option></option>
                            <option value="http://localhost:8080/admin/userList"><spring:message code="app.lang.list"/></option>
                            <option value="http://localhost:8080/admin/addProduct"><spring:message code="app.lang.addProd"/></option>
                            <option value="http://localhost:8080/admin/productList"><spring:message code="app.lang.listProduct"/></option>
                        </sec:authorize>
                        <option></option>
                        <option value="http://localhost:8080/logout"><spring:message code="app.lang.logOut"/></option>
                    </select>
                </div>
            </sec:authorize>
        </div>
    </div>
</header>
<section class="sectionOne">
    <div class="container">
        <div class="category">
            <div class="categoryImgFirst">
                <a href="/catalog/category/${categoryList.get(0).id}">
                    <img src="/static/img/horizontalBar.png" alt="" class="imgCategory">
                </a>
                <a href="/catalog/category/${categoryList.get(0).id}" class="btn"><spring:message code="app.lang.bars"/></a>
            </div>
            <div class="categoryImg">
                <a href="/catalog/category/${categoryList.get(1).id}">
                    <img src="/static/img/wallBars.png" alt="" class="imgCategory">
                </a>
                <a href="/catalog/category/${categoryList.get(1).id}" class="btn"><spring:message code="app.lang.swedish"/></a>
            </div>
            <div class="categoryImg">
                <a href="/catalog/category/${categoryList.get(2).id}">
                    <img src="/static/img/iron.png" alt="" class="imgCategory">
                </a>
                <a href="/catalog/category/${categoryList.get(2).id}" class="btn"><spring:message code="app.lang.iron"/></a>
            </div>
            <div class="categoryImg">
                <a href="/catalog/category/${categoryList.get(3).id}">
                    <img src="/static/img/trainingApparatus.png" alt="" class="imgCategory">
                </a>
                <a href="/catalog/category/${categoryList.get(3).id}" class="btn"><spring:message code="app.lang.simulators"/></a>
            </div>
        </div>
    </div>
</section>
<section class="sectionTwo">
    <div class="container">
        <p><spring:message code="app.lang.infoCatalog"/></p>
        <div class="pageProduct">
            <div class="filter">
            <h3><spring:message code="app.lang.filter"/> <br>_________________________</h3>
            </div>
            <div class="products">

<sec:authorize access="isAnonymous()">
<c:forEach items="${productList}" var="prod">
                <div class="prodouctStyle">
                    <img src="${prod.filename}" alt="No images" class="img">
                    <br>
                    <h4 class="nameProduct">${prod.category} <br> ${prod.type.toString()} ${prod.brand} ${prod.model}</h4>
                    <p class="nameProduct">Артикул: ${prod.article}</p>
                    <div class="by">
                        <h3 class="price">${prod.price} руб</h3>
                        <button type="submit" value="idProd" name="id" class="btnBy"><a href="/catalog/${prod.id}"><spring:message code="app.lang.inBasket"/></a></button>
                    </div>
                </div>

</c:forEach>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <c:forEach items="${productList}" var="prod">
    <div class="prodouctStyle">
        <img src="${prod.filename}" alt="No images" class="img">
        <br>
        <h4 class="nameProduct">${prod.category} <br> ${prod.type.toString()} ${prod.brand} ${prod.model}</h4>
        <p class="nameProduct">Артикул: ${prod.article}</p>
        <div class="by">
            <h3 class="price">${prod.price} руб</h3>
            <button type="submit" class="btnBy"><a href="/catalog/user/${prod.id}">В корзину</a></button>
        </div>
        <sec:authorize access="hasAuthority('ADMIN')">
        <a href="/catalog/delete/${prod.id}" class="deleteProduct">Удалить товар</a>
        </sec:authorize>
    </div>
    </c:forEach>
</sec:authorize>

            </div>
        </div>
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
