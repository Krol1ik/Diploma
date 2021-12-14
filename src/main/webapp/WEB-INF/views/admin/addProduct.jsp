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
<link rel="stylesheet" href="/static/css/admin/addProduct.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/admin/userList.css">
    <title>Document</title>
    <head>
        <title>Sport line</title>
    </head>
<body>
<header class="header" id="header">
    <div class="container">
        <div class="wrapper">
            <h2 class="titleHead"><a href="/" class="logo">SPORT LINE</a><spring:message code="app.lang.addProd"/>
                <select class="profile" onchange="window.location.href = this.options[this.selectedIndex].value">
                    <option><spring:message code="app.lang.profile"/></option>
                    <option value="http://localhost:8080/profile"><spring:message code="app.lang.dataProfile"/></option>
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
        <div class="wrapped">
            <form method="post" enctype="multipart/form-data">
                <input type="text" name="brandName" placeholder="<spring:message code="app.lang.brand"/>" class="row">
                <input type="text" name="modelName" placeholder="<spring:message code="app.lang.model"/>" class="row">
                <input list="browsers" name="categoryName"  placeholder="<spring:message code="app.lang.category"/>" class="row">
                <datalist id="browsers" class="inputsBlock" >
                    <c:forEach items="${allCategory}" var="category">
                    <option value="${category.categoryName}" >
                        </c:forEach>
                </datalist>
                <input list="browser" name="typeName"  placeholder="<spring:message code="app.lang.type"/>" class="row"/>
                <datalist id="browser" сlass="inputsBlock">
                    <c:forEach items="${allType}" var="type">
                    <option value="${type.typeName}">
                        </c:forEach>
                </datalist>
                <input type="text" name="descriptionProduct" placeholder="<spring:message code="app.lang.description"/>" class="row">
                <input type="text" name="price" placeholder="<spring:message code="app.lang.priceInFormat"/>" class="row">
                <input type="number" name="discount" placeholder="<spring:message code="app.lang.discount"/>" class="row">
                <input type="number" name="count" placeholder="<spring:message code="app.lang.qualityProd"/>" class="row">
                <input type="hidden" name="_csrf" value="${_csrf.token}" class="row"/>
                <input type="file" name="file" class="fileAdd">
                <c:if test="${!messages}">
                    <p class="err">${messages}</p>
                </c:if>
                <button type="submit" class="row">"<spring:message code="app.lang.addProd"/>"</button>
            </form>
        </div>
    </div>
</section>
</body>
</html>