<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Meal</title>
</head>
<body>
<form method="post" action="meals">
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == "update" ? "Update" : "Add"}</h2>
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <div style="margin-left: 30px">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" name="dateTime" value="${meal.dateTime}" required/></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" name="description" value="${meal.description}" required/></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" name="calories" value="${meal.calories}" required/></dd>
        </dl>
    </div>
    <input type="submit" value="Save"/>
    <input onclick="window.history.back()" type="button" value="Cancel"/>
</form>
</body>
</html>
