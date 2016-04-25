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
                    <th>Edit</th>
                    <th>Delete</th>
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
                                        <a class="btn btn-success" data-title="Analyzes" href="AllAnalyzesController?id=${patient.id}"><span class="glyphicon glyphicon-tint"> </span></a></p>
                                </div>
                            </td>
                            <td>
                                <form class="col-sm-5" name = "listAnalyzes">
                                    <p data-placement="top" data-toggle="tooltip" title="Edit"><a class="btn btn-primary btn" data-title="Edit" href="EditPatientController?id=${patient.id}">
                                        <span class="glyphicon glyphicon-pencil"></span></a>
                                    </p>
                                </form>
                            </td>
                            <td>
                                <div class="col-sm-5">
                                    <p data-placement="top" data-toggle="tooltip" title="Delete">
                                        <button  class="btn btn-danger" id="deletePatient" data-values="DeletePatientController?id=,${patient.id},${patient.lastName}" data-toggle="modal" data-target="#deletePatientModal">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </p>
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
            <a class="btn btn-success btn-lg" role="button" href = "<c:url value = "AddPatient.jsp"/>">
                <span class="glyphicon glyphicon-plus"></span> Add Patient </a>
            <a type="submit" class="btn btn-primary btn-lg" href= "<c:url value = "AllPatientController"/>" >
                <span class="glyphicon glyphicon-refresh"></span>Refresh</a>
        </div>
    </div>
</div>

<%--  modal--%>
<div class="modal fade" id="deletePatientModal" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align" id="Heading">Delete patient</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span><p class="debug-url"></p></div>
            </div>
            <div class="modal-footer">
                <a class="btn btn-success" id ="deleteButton" type="submit"><span class="glyphicon glyphicon-ok-sign"></span>Yes</a>
                <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>No</button>
            </div>
        </div>
    </div>
</div>
<%-- end modal--%>
<script src="${pageContext.request.contextPath}/js/jquery.tablesorter.js"></script>
</body>
</html>