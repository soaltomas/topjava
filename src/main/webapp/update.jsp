<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 31.03.17
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add/Update meal</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Update meal</h2>
<hr>

<form action="meals" method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <p>DateTime:</p>
    <input type="datetime" name="dateTime" value="${meal.dateTime}">
    <p>Description:</p>
    <input type="text" name="description" value="${meal.description}">
    <p>Calories:</p>
    <input type="text" name="calories" value="${meal.calories}">
    <input type="submit" value="Save">
</form>
</body>
</html>
