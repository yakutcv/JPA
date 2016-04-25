<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%--CSS--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.default.css" />

    <%--Script--%>
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.tablesorter.js"></script>
    <script src="${pageContext.request.contextPath}/js/AllAnalyzes.js"></script>

    <title>All Analyzes</title>
</head>
<body>
<c:set var="count" value="${1}"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2> All analyzes for patient ${patient.getFullName()}</h2>
            <div class="table-responsive">
                <table id="mytable" class="tablesorter table table-bordred table-striped">
                    <thead>
                    <th>#</th>
                    <th>Type Analyzes</th>
                    <th>Date</th>
                    <th>Report</th>
                    </thead>
                    <tbody>
                    <c:forEach items = "${analyzes}" var = "analysis">
                        <tr>
                            <td><c:out value="${count}"/></td>
                            <c:set var="count" value="${count+1}"/>
                            <td>${analysis.getType()}</td>
                            <td>${analysis.getDateInString()} </td>
                            <td style="word-wrap: break-word;">${analysis.getReport()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div>
            <a class="btn btn-default btn-lg" role="button" href = "<c:url value ="AllPatientController"/>">
                <span class="glyphicon glyphicon-arrow-left"></span> Go back to the list with all patients </a>
            <a type="submit" class="btn btn-primary btn-lg" href= "<c:url value = "AllAnalyzesController?id=${patient.id}"/>" >
                <span class="glyphicon glyphicon-refresh"></span>Refresh</a>
            <input type="hidden" id="tmpId" name = "id" value="${patient.getId()}">
        </div>
    </div>
</div>
</body>
</html>