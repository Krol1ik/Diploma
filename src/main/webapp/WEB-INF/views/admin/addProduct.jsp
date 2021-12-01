<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h2 class="titleHead"><a href="/" class="logo">SPORT LINE</a>Добавление товара
                <select class="profile" onchange="window.location.href = this.options[this.selectedIndex].value">
                    <option>Профиль</option>
                    <option value="http://localhost:8080/profile">Личные данные</option>
                    <option value="http://localhost:8080/history">История заказов</option>
                    <option></option>
                    <option value="http://localhost:8080/admin/userList">Список пользователей</option>
                    <option value="http://localhost:8080/admin/addProduct">Добавить товар</option>
                    <option></option>
                    <option value="http://localhost:8080/logout">Выход</option>
                </select>
            </h2>
        </div>
    </div>
</header>
<section class="title">
    <div class="container">
        <div class="wrapped">
            <form method="post" enctype="multipart/form-data">
                <input type="text" name="brandName" placeholder="марка" class="row">
                <input type="text" name="modelName" placeholder="модель" class="row">
                <input list="browsers" name="categoryName"  placeholder="категория"/ class="row">
                <datalist id="browsers" class="inputsBlock" >
                    <c:forEach items="${allCategory}" var="category">
                    <option value="${category.categoryName}" >
                        </c:forEach>
                </datalist>
                <input list="browser" name="typeName"  placeholder="тип товара" class="row"/>
                <datalist id="browser" сlass="inputsBlock">
                    <c:forEach items="${allType}" var="type">
                    <option value="${type.typeName}">
                        </c:forEach>
                </datalist>
                <input type="text" name="descriptionProduct" placeholder="описание" class="row">
                <input type="text" name="price" placeholder="стоимость в формате xxx.xx" class="row">
                <input type="number" name="discount" placeholder="скидка в %" class="row">
                <input type="hidden" name="_csrf" value="${_csrf.token}" class="row"/>
                <input type="file" name="file" class="fileAdd">
                <button type="submit" class="row">Добавить товар</button>
            </form>
            <c:if test="${!messages}">
                <p class="err">${messages}</p>
            </c:if>
        </div>
    </div>
</section>
</body>
</html>