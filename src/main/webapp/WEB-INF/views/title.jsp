<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.11.2021
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/static/css/title.css">

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
            <form method="get" action="/catalog/search">
                <input type="text" class="search" name="search" placeholder="<spring:message code="app.lang.search"/>">
            </form>
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
<section class="title">
    <div class="container">
        <div class="benefistSection">
            <div class="oneDel">
                <img src="static/img/delivery.png" alt="" class="btnImgInfo">
                <h3 class="textDelivery"><spring:message code="app.lang.shippingFree"/> <spring:message code="app.lang.shipping"/></h3>
            </div>
            <div class="delivery">
                <img src="static/img/guarantee.png" alt="" class="btnImgInfo">
                <h3 class="textGuarantee"><spring:message code="app.lang.warranty"/> <spring:message code="app.lang.warranty18"/></h3>
            </div>
            <div class="delivery">
                <img src="static/img/quality.png" alt="" class="btnImgInfo">
                <h3 class="textGuarantee"><spring:message code="app.lang.qualityBest"/> <spring:message code="app.lang.quality"/></h3>
            </div>
            <div class="delivery">
                <img src="static/img/compass.png" alt="" class="btnImgInfo">
                <h3 class="textGuarantee"><spring:message code="app.lang.deliver"/> <spring:message code="app.lang.deliverBR"/></h3>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="banerAndCategory">
        <div class="category">
            <div class="categoryImg">
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
            <img src="<spring:message code="app.lang.img"/>" alt="" class="banerImg">
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
