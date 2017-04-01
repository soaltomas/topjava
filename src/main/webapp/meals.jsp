<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 30.03.17
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://topjava.ru/functions" prefix="f" %>
<html>
<head>
    <style>
        .table_col {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            width: 660px;
            background: white;
            text-align: left;
            border-collapse: collapse;
            color: #3E4347;
        }

        .table_col th:first-child, .table_col tr:first-child {
            color: #F5F6F6;
            border-left: none;
        }

        .table_col th {
            font-weight: normal;
            border-right: 20px solid #8ff0ad;
            border-left: 20px solid #8ff0ad;
            padding: 8px 10px;
            background-color: #8ff0ad;
            text-align: center;
        }

        .table_col td {
            border-right: 20px solid white;
            border-left: 20px solid white;
            padding: 12px 10px;
        }

        .text_col_green {
            color: green;
        }

        .text_col_red {
            color: red;
        }
    </style>
    <title>Meal List</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal List</h2>
<hr>

<table class="table_col">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th/>
        <th/>
        <th/>
    </tr>
    <c:forEach items="${mealList}" var="meal">
        <c:if test="${meal.exceed}">
            <tr class="text_col_red">
        </c:if>
        <c:if test="${!meal.exceed}">
            <tr class="text_col_green">
        </c:if>
        <td><c:out value="${f:matches(meal.dateTime, 'dd.MM.yyyy HH:mm')}"/></td>
        <td><c:out value="${meal.description}"/></td>
        <td><c:out value="${meal.calories}"/></td>
        <%--<td><a href="meals?mealId=${meal.id}&action=update">Update</a></td>--%>
        <td><a href="meals?mealId=${meal.id}&action=delete">Delete</a></td>
        <td><a href="meals?mealId=${meal.id}&action=update">Update</a></td>
        </tr>
    </c:forEach>
</table>
<a href="meals?action=add">Add</a>
</body>
</html>
