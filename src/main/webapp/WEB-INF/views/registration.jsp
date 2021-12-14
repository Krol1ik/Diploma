<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<link rel="stylesheet" href="/static/css/registration.css">
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
                        <option value="http://localhost:8080/admin/productList"><spring:message code="app.lang.listProduct"/></option>
                    </sec:authorize>
                    <option></option>
                    <option value="http://localhost:8080/logout"><spring:message code="app.lang.logOut"/></option>
                </select>
            </sec:authorize>
        </div>
    </div>
</header>
<section class="title">
    <div class="container">
        <div class="chekoutInfo">
            <h1 class="registrationOrder"><spring:message code="app.lang.formReg"/></h1>
            <div class="dataCostumer">
            <form:form action="/registration" modelAttribute="user" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="inputForm">
                    <p class="nameInput"><spring:message code="app.lang.login"/></p>
                    <spring:message code="app.lang.placeholderLogin" var="placeholderLogin" />
                <form:input path="username" class="inputsBlock" placeholder='${placeholderLogin}'/>
                    <br>
                <form:errors path="username" class="err"/>
                    <c:if test="${!messages}">
                        <p class="err">${messages}</p>
                    </c:if>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </div>
                <div class="inputForm">
                    <p class="nameInput"><spring:message code="app.lang.password"/></p>
                    <spring:message code="app.lang.placeholderPassword" var="placeholderPass" />
                <form:input path="password" type="password" class="inputsBlock" placeholder='${placeholderPass}'/>
                    <br>
                <form:errors path="password" class="err"/>
                </div>
                <div class="inputForm">
                    <p class="nameInput">E-mail</p>
                    <spring:message code="app.lang.placeholderEmail" var="placeholderEmail" />
                <form:input path="email" class="inputsBlock" placeholder='${placeholderEmail}'/>
                    <br>
                <form:errors path="email" class="err"/>
                    <c:if test="${!messagesForEmail}">
                        <p class="err">${messagesForEmail}</p>
                    </c:if>
                </div>
                <div class="inputForm">
                    <p class="nameInput"><spring:message code="app.lang.firstName"/></p>
                    <spring:message code="app.lang.placeholderFirstName" var="placeholderFirstName" />
                <form:input path="firstName" class="inputsBlock" placeholder='${placeholderFirstName}'/>
                    <br>
                <form:errors path="firstName" class="err"/>
                </div>
                <div class="inputForm">
                    <p class="nameInput"><spring:message code="app.lang.lastName"/></p>
                    <spring:message code="app.lang.placeholderLastName" var="placeholderLastName" />
                <form:input path="lastName" class="inputsBlock" placeholder='${placeholderLastName}'/>
                    <br>
                <form:errors path="lastName" class="err"/>
                </div>
                <div class="inputForm">
                    <p class="nameInput"><spring:message code="app.lang.phone"/></p>
                    <spring:message code="app.lang.placeholderPhone" var="placeholderPhone" />
                <form:input path="phoneNumber" type="phone" class="inputsBlock" placeholder='${placeholderPhone}'/>
                    <br>
                <form:errors path="phoneNumber" class="err"/>
                </div>

                <form:form action="/registration" modelAttribute="address" method="post">
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
                    <form:input path="street" class="inputsBlock" placeholder='${placeholderStreet}'/>
                    <br>
                    <form:errors path="street" class="err"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </div>
                <div class="inputForm">
                    <p class="nameInput"><spring:message code="app.lang.numberHouse"/></p>
                    <spring:message code="app.lang.placeholderNumberHouse" var="placeholderHouse" />
                    <form:input path="numberHouse" class="inputsBlock" placeholder='${placeholderHouse}'/>
                    <br>
                    <form:errors path="numberHouse" class="err"/>
                </div>
                    <div class="btnOrder">
                        <button type="submit" class="sendOrder"><spring:message code="app.lang.registrationEnter"/></button>
                        </div>
            </form:form>
            </form:form>
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
