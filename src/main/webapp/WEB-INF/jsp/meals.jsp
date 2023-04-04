<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<script type="text/javascript" src="resources/js/topjava.common.js" defer></script>
<script type="text/javascript" src="resources/js/topjava.meals.js" defer></script>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="meal.title"/></h3>
        <form id="filter">
            <div class="row">
                <div class="col-2">
                    <label for="startDate"><spring:message code="meal.startDate"/></label>
                    <input class="form-control" name="startDate" id="startDate" autocomplete="off">
                </div>
                <div class="col-2">
                    <label for="endDate"><spring:message code="meal.endDate"/></label>
                    <input class="form-control" name="endDate" id="endDate" autocomplete="off">
                </div>
                <div class="offset-2 col-3">
                    <label for="startTime"><spring:message code="meal.startTime"/></label>
                    <input class="form-control" name="startTime" id="startTime" autocomplete="off">
                </div>
                <div class="col-3">
                    <label for="endTime"><spring:message code="meal.endTime"/></label>
                    <input class="form-control" name="endTime" id="endTime" autocomplete="off">
                </div>
            </div>
        </form>
    <hr>
    <a href="meals/create"><spring:message code="meal.add"/></a>
    <hr>
    <%--    <table border="1" cellpadding="8" cellspacing="0">--%>
    <table class="table table-striped" id="datatable">
        <thead>
        <tr>
            <th><spring:message code="meal.dateTime"/></th>
            <th><spring:message code="meal.description"/></th>
            <th><spring:message code="meal.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr data-meal-excess="${meal.excess}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals/update?id=${meal.id}"><spring:message code="common.update"/></a></td>
                <td><a href="meals/delete?id=${meal.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>