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
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.default.css"/>

  <%--Script--%>
  <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/moment-with-locales.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/AllPatient.js"></script>

  <title>All Patients</title>
</head>
<body>
<c:set var="count" value="${1}"/>
<div class="container">
  <div class="row">
    <div class="col-md-12">
      <h4>All Patients</h4>
      <div class="table-responsive">
        <table id="mytable" class="tablesorter table table-bordred table-striped ">
          <thead>
          <th>#</th>
          <th>Last Name</th>
          <th>First Name</th>
          <th>Birthdate</th>
          <th>Age(years)</th>
          <th>Analyzes</th>
          </thead>
          <tbody>
          <c:forEach items = "${patients}" var = "patient">
            <tr>
              <td><c:out value="${count}"/></td>
              <c:set var="count" value="${count+1}"/>
              <td>${patient.lastName}</td>
              <td>${patient.name} </td>
              <td>${patient.getBirthDateInString()}</td>
              <td>${patient.getAge()}</td>
              <td>
                <div class="col-sm-5" name = "listAnalyzes">
                  <p data-placement="top" data-toggle="tooltip" title="Analyzes">
                    <a class="btn btn-success" data-title="Analyzes" href="AllAnalyzesController?id=${patient.id}&laboratory=laboratory"><span class="glyphicon glyphicon-tint"></span></a></p>
                </div>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <div>
      <a type="submit" class="btn btn-default btn-lg" href= "<c:url value = "index.jsp"/>" ><span class="glyphicon glyphicon-arrow-left"></span>Go back to the main page</a>
      <a type="submit" class="btn btn-primary btn-lg" href= "<c:url value = "AllPatientController?laboratory=laboratory"/>" >
        <span class="glyphicon glyphicon-refresh"></span>Refresh</a>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.tablesorter.js"></script>
</body>
</html>