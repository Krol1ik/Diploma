<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.11.2021
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/static/css/basket.css">
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
            <form method="get" action="/catalog/search">
                <template id="resultstemplate">
                    <c:forEach items="${search}" var="search">
                        <option> ${search}</option>
                    </c:forEach>
                </template>
                <input type="text" oninput="searchCatalog()" name="search" id="search"   class="search" list="searchresults" autocomplete="off" placeholder="<spring:message code="app.lang.search"/>"/>
                <datalist id="searchresults"></datalist>
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

<section class="basketList">
    <h1 class="textBasket"><spring:message code="app.lang.basket"/></h1>
    <div class="container">

<sec:authorize access="isAnonymous()">
        <c:if test="${!messages}">
            <p class="err">${messages}</p>
        </c:if>
        <form action="/basket" method="post">
            <c:forEach items="${order}" var="ord">
                <div class="productList">
                    <img src="${ord.productOrder.filename}" alt="No images" class="img">
                    <div class="infoProduct">
                        <br>
                        <h4 class="nameProduct"><span class="article"><br><spring:message code="app.lang.article"/>: ${ord.productOrder.article}</span>
                            <br><br><br>${ord.productOrder.category} ${ord.productOrder.type} ${ord.productOrder.brand} ${ord.productOrder.model}
                        </h4>
                        <h5 class="quality"><spring:message code="app.lang.qualityProd"/>: <input min="1" onclick="sum(document.getElementById('${ord.productOrder.id}').id, document.getElementById('${ord.productOrder.id + 1000}').id, document.getElementById('${ord.productOrder.id + 1100}').id)" id="${ord.productOrder.id}" type="number" class="qualityInput" value="${ord.count}"
                                                           name="orderCount"></h5>
                        <input type="hidden" id="${ord.productOrder.id + 1000}" value="${ord.productOrder.price}">
                        <h3 id="${ord.productOrder.id + 1100}" class="price">${ord.productOrder.price}</h3><h3 class="priceStr"><spring:message code="app.lang.price"/> </h3>
                            <a href="/basket/${ord.id}" target="" class="del"><spring:message code="app.lang.delete"/></a>
                        <input type="hidden" value="${ord.id}" name="orderId">
                    </div>
                </div>
            </c:forEach>
            <c:if test="${!errorCount}">
                <p class="err">${errorCount}</p>
            </c:if>
            <button type="submit" class="btn"><spring:message code="app.lang.makeOrder"/></button>
        </form>

</sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <c:if test="${!messages}">
                <p class="err">${messages}</p>
            </c:if>
            <form action="/basket" method="post">
                <c:forEach items="${orderForUser}" var="ord">
                    <div class="productList">
                        <img src="${ord.productOrder.filename}" alt="No images" class="img">
                        <div class="infoProduct">
                            <br>
                            <h4 class="nameProduct"><span class="article"><br><spring:message code="app.lang.article"/>: ${ord.productOrder.article}</span>
                                <br><br><br>${ord.productOrder.category} ${ord.productOrder.type} ${ord.productOrder.brand} ${ord.productOrder.model}
                            </h4>
                            <h5 class="quality"><spring:message code="app.lang.qualityProd"/>: <input min="1" onclick="sum(document.getElementById('${ord.productOrder.id}').id, document.getElementById('${ord.productOrder.id + 1000}').id, document.getElementById('${ord.productOrder.id + 1100}').id)" id="${ord.productOrder.id}" type="number" class="qualityInput" value="${ord.count}"
                                                                                                      name="orderCount"></h5>
                            <input type="hidden" id="${ord.productOrder.id + 1000}" value="${ord.productOrder.price}">
                            <h3 id="${ord.productOrder.id + 1100}" class="price">${ord.productOrder.price}</h3><h3 class="priceStr"><spring:message code="app.lang.price"/> </h3>
                            <a href="/basket/user/${ord.id}" class="del"><spring:message code="app.lang.delete"/></a>
                            <input type="hidden" value="${ord.id}" name="orderId">
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${!errorCountForUser}">
                    <p class="err">${errorCountForUser}</p>
                </c:if>
                <button type="submit" class="btn"><spring:message code="app.lang.makeOrder"/></button>
            </form>
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

