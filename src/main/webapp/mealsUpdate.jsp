<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
</head>
<body>
<form method="post" action="meals" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${meal.id}">
    <h3><a href="index.html">Home</a></h3>
    <h4>Update/Add meal</h4>
    <dl>
        <dt>DateTime:</dt>
        <dd><input type="text" placeholder="dd-mm-yyyy hh:mm" name="dateTime" size=50 value="${meal.dateTime}"></dd>
    </dl>
    <dl>
        <dt>Description:</dt>
        <dd><input type="text" placeholder="meal" name="description" size=50 value="${meal.description}"></dd>
    </dl>
    <dl>
        <dt>Calories:</dt>
        <dd><input type="number" placeholder="10 - 2000" name="calories" size=50 value="${meal.calories}"></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
