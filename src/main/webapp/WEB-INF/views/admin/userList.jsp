<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.11.2021
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/static/css/admin/userList.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/admin/userList.css">

    <head>
        <title>Sport line</title>
    </head>
<body>
<header class="header" id="header">
    <div class="container">
        <div class="wrapper">
            <h2 class="listUser"><a href="/" class="logo">SPORT LINE</a><spring:message code="app.lang.list"/>
                <select class="profile" onchange="window.location.href = this.options[this.selectedIndex].value">
                    <option><spring:message code="app.lang.profile"/></option>
                    <option value="http://localhost:8080/profile"><spring:message code="app.lang.personalData"/></option>
                    <option value="http://localhost:8080/history"><spring:message code="app.lang.history"/></option>
                    <option></option>
                    <option value="http://localhost:8080/admin/userList"><spring:message code="app.lang.list"/></option>
                    <option value="http://localhost:8080/admin/addProduct"><spring:message code="app.lang.addProd"/></option>
                    <option></option>
                    <option value="http://localhost:8080/logout"><spring:message code="app.lang.logOut"/></option>
                </select>
            </h2>
        </div>
    </div>
</header>
<section class="title">
    <div class="container">
        <div class="wrapped">
        <TABLE BORDER="1" CELLSPACING="0" class="tableUsers" >
            <TH class="row"> Логин </TH>
            <TH class="row"> Имя </TH>
            <TH class="row"> Фамилия </TH>
            <TH class="row"> Город </TH>
            <TH class="row"> E-mail </TH>
            <TR>
            <c:forEach items="${user}" var="user">
                <TD> ${user.username} </TD>
                <TD> ${user.firstName} </TD>
                <TD> ${user.lastName} </TD>
                <TD> ${user.address.city.cityName} </TD>
                <TD> ${user.email} </TD>

            <TR>
                </c:forEach>
        </TABLE>
        <TABLE BORDER="1" CELLSPACING="0" class="tableUsers">
                    <TH class="row"> Сумма заказов </TH>
                    <c:forEach items="${priceOrder}" var="price">
                         <TR>
                         <TD class="tableData"> ${price} </TD>
                    </c:forEach>
        </TABLE>
        <TABLE BORDER="1" CELLSPACING="0" class="tableUsers">
                     <TH class="row"> Кол-во товаров </TH>
                    <c:forEach items="${countOrder}" var="count">
                         <TR>
                        <TD class="tableData"> ${count} </TD>
                    </c:forEach>
        </TABLE>
        </div>
    </div>
</section>
</body>
</html>