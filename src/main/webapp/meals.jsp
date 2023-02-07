<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ru.javawebinar.topjava.util/functions" prefix="f" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="meals?&action=add">Add meal</a>
<table>
    <table border="6" cellpadding="9" cellspacing="1">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <c:if test="${meal.excess == true}">
                <tr style="color:red">
                    <td>${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy')}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?id=${meal.id}&action=update">Update</a></td>
                    <td><a href="meals?id=${meal.id}&action=delete">Delete</a></td>

                </tr>
            </c:if>
            <c:if test="${meal.excess == false}">
                <tr style="color:green">
                    <td>${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy')}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?id=${meal.id}&action=update">Update</a></td>
                    <td><a href="meals?id=${meal.id}&action=delete">Delete</a></td>

                </tr>
            </c:if>
        </c:forEach>
    </table>
</body>
</html>
