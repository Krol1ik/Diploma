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
<link rel="stylesheet" href="/static/css/admin/productList.css">
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
                <div class="dropdown">
                    <button class="dropbtn"><spring:message code="app.lang.change"/></button>
                    <div class="dropdown-content">
                        <a href="?lang=en"><spring:message code="app.lang.english"/></a>
                        <a href="?lang=ru"><spring:message code="app.lang.russia"/></a>
                    </div>
                </div>
            </h2>
        </div>
    </div>
</header>
<section class="title">
    <div class="container">
        <c:if test="${!updateAccept}">
            <p class="err">${updateAccept}</p>
        </c:if>
        <div class="wrapped">
            <TABLE BORDER="1" CELLSPACING="0" class="tableUsers" >
                <TH class="row"> <spring:message code="app.lang.article"/></TH>
                <TH class="row"> <spring:message code="app.lang.brand"/></TH>
                <TH class="row"> <spring:message code="app.lang.model"/></TH>
                <TH class="row"> <spring:message code="app.lang.category"/></TH>
                <TH class="row"> <spring:message code="app.lang.type"/></TH>
                <TH class="row"> <spring:message code="app.lang.price"/></TH>
                <TH class="row"> <spring:message code="app.lang.discount"/></TH>
                <TH class="row"> <spring:message code="app.lang.qualityProd"/></TH>
                <TR>
                        <c:forEach items="${products}" var="prod">
                        <form action="/admin/updateProduct/${prod.id}" method="post">
                        <TD> ${prod.article} </TD>
                        <TD> ${prod.brand} </TD>
                        <TD> ${prod.model} </TD>
                        <TD> ${prod.category} </TD>
                        <TD> ${prod.type} </TD>
                        <TD> <input name="price" value="${prod.price}"/> </TD>
                        <TD> <input name="discount" value="${prod.discount}" type="number"/> </TD>
                        <TD> <input name="stockBalance" value="${prod.stockBalance}" type="number"/> </TD>
                        <TD> <button type="submit" class="btn"><spring:message code="app.lang.update"/></button> <br>
                            <button type="button" class="btn"><a href="/admin/deleteProduct/${prod.id}"><spring:message code="app.lang.deleteProduct"/></a></button>
                        </TD>
                        </form>
                <TR>
                    </c:forEach>
            </TABLE>
        </div>
    </div>
</section>
</body>
</html>