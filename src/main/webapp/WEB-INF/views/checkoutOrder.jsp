<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <h1 class="registrationOrder"><spring:message code="app.lang.orderYour"/></h1>
        <div class="checkoutInfo">
            <form:form action="/checkoutOrder" modelAttribute="customer" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="inputForm">
                    <p class="nameInput">E-mail</p>
                    <spring:message code="app.lang.placeholderEmail" var="placeholderEmail" />
                    <form:input path="email" class="inputsBlock" placeholder='${placeholderEmail}' value = "${customer.email}"/>
                    <br>
                    <form:errors path="email" class="err"/>
                </div>
                <div class="inputForm">
                    <p class="nameInput"><spring:message code="app.lang.firstName"/></p>
                    <spring:message code="app.lang.placeholderFirstName" var="placeholderFirstName" />
                    <form:input path="firstName" class="inputsBlock" placeholder='${placeholderFirstName}'  value = "${customer.firstName}"/>
                    <br>
                    <form:errors path="firstName" class="err"/>
                </div>
                <div class="inputForm">
                    <p class="nameInput"><spring:message code="app.lang.lastName"/></p>
                    <spring:message code="app.lang.placeholderLastName" var="placeholderLastName" />
                    <form:input path="lastName" class="inputsBlock" placeholder='${placeholderLastName}' value = "${customer.lastName}"/>
                    <br>
                    <form:errors path="lastName" class="err"/>
                </div>
                <div class="inputForm">
                    <p class="nameInput"><spring:message code="app.lang.phone"/></p>
                    <spring:message code="app.lang.placeholderPhone" var="placeholderPhone" />
                    <form:input path="phoneNumber" type="phone" class="inputsBlock" placeholder='${placeholderPhone}' value = "${customer.phoneNumber}"/>
                    <br>
                    <form:errors path="phoneNumber" class="err"/>
                </div>

                <form:form action="/checkoutOrder" modelAttribute="address" method="post">
                    <div class="inputForm">
                        <p class="nameInput"><spring:message code="app.lang.city"/></p>
                        <spring:message code="app.lang.placeholderCity" var="placeholderCity" />
                        <input list="browsers" name="cityName" class="inputsBlock" placeholder='${placeholderCity}'/>
                        <datalist id="browsers" lass="inputsBlock">
                            <c:forEach items="${cityList}" var="city">
                            <option value="${city.cityName}">
                                </c:forEach>
                        </datalist>
                        <br>
                        <c:if test="${!messagesErrorCity}">
                            <p class="err">${messagesErrorCity}</p>
                        </c:if>
                    </div>
                    <div class="inputForm">
                        <p class="nameInput"><spring:message code="app.lang.street"/></p>
                        <spring:message code="app.lang.placeholderStreet" var="placeholderStreet" />
                        <form:input path="street" class="inputsBlock" placeholder='${placeholderStreet}' value = "${address.street}"/>
                        <br>
                        <form:errors path="street" class="err"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    </div>
                    <div class="inputForm">
                        <p class="nameInput"><spring:message code="app.lang.numberHouse"/></p>
                        <spring:message code="app.lang.placeholderNumberHouse" var="placeholderHouse" />
                        <form:input path="numberHouse" class="inputsBlock" placeholder='${placeholderHouse}'  value = "${address.numberHouse}"/>
                        <br>
                        <form:errors path="numberHouse" class="err"/>
                    </div>
                    <div class="btnOrder">
                        <button type="submit" class="sendOrder"><spring:message code="app.lang.makeOrder"/></button>
                    </div>
                </form:form>
            </form:form>
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
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="dataCostumer">
                    <div class="inputForm">
                        <p class="nameInput"> <spring:message code="app.lang.firstName"/></p>
                       <input disabled type="text" class="inputsBlock" value="${user.firstName}" name="firstName">
                    </div>
                    <div class="inputForm">
                        <p class="nameInput"><spring:message code="app.lang.lastName"/></p>
                        <input disabled type="text" class="inputsBlock" value="${user.lastName}" name="lastName">
                    </div>
                    <div class="inputForm">
                        <p class="nameInput"><spring:message code="app.lang.phone"/></p>
                        <input disabled type="phone" class="inputsBlock" value="${user.phoneNumber}" name="phoneNumber">
                    </div>
                    <div class="inputForm">
                        <p class="nameInput">E-mail</p>
                        <input disabled type="email" class="inputsBlock" value="${user.email}" name="email">
                    </div>
                    <div class="inputForm">
                        <p class="nameInput"><spring:message code="app.lang.city"/></p>
                        <input disabled type="text" class="inputsBlock" value="${user.address.city.cityName}" name="cityName">
                    </div>
                    <div class="inputForm">
                        <p class="nameInput"><spring:message code="app.lang.street"/></p>
                        <input disabled type="text" class="inputsBlock" value="${user.address.street}" name="street">
                    </div>
                    <div class="inputForm">
                        <p class="nameInput"><spring:message code="app.lang.numberHouse"/></p>
                        <input disabled type="text" class="inputsBlock" value="${user.address.numberHouse}" name="numberHouse">
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
