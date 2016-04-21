
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" />

    <script src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/moment-with-locales.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>

    <title>All Analyzes</title>
</head>

<body>
<c:set var="count" value="${1}"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2 text-center> All analyzes for patient ${patient.getFullName()}</h2>
            <div class="table-responsive">
                <table id="mytable" class="table table-bordred table-striped">
                    <thead>
                    <th>#</th>
                    <th>Type Analyzes</th>
                    <th>Date</th>
                    <th>Report</th>
                    <th>Delete</th>
                    </thead>
                    <c:forEach items = "${analyzes}" var = "analysis">
                    <tbody>
                    <tr>
                        <td><c:out value="${count}"/></td>
                        <c:set var="count" value="${count+1}"/>
                        <td>${analysis.getType()}</td>
                        <td>${analysis.getDateInString()} </td>
                        <td>${analysis.getReport()}</td>
                        <td>

                            <div class="col-sm-5">
                                <p data-placement="top" data-toggle="tooltip" title="Delete">
                                    <button  class="btn btn-danger" id="deletePatient" data-values="DeleteAnalysis?idP=,${patient.id},&idA=${analysis.id},${analysis.getType()},${analysis.getDateInString()}" data-toggle="modal" data-target="#myModal">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </button>
                                </p>
                            </div>
                                <%-- modal window --%>
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                            <h4 class="modal-title custom_align" id="Heading">Delete Analysis</h4>
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
                        </td>
                    </tr>
                    </tbody>
            </div>
        </div>
        </c:forEach>
        </table>

        <a class="btn btn-default btn-lg" role="button" href = "<c:url value ="Patients"/>">
            <span class="glyphicon glyphicon-arrow-left"></span> Go back to the list with all patients </a>

        <a class="btn btn-success btn-lg" role="button" href = "<c:url value = "AddAnalyzes?id=${patient.id}"/>">
            <span class="glyphicon glyphicon-plus"></span> Add </a>

        <input type="hidden" id="tmpId" value = "patientId" name = "id" value="${patient.getId()}">

    </div>
</div>


<script src="${pageContext.request.contextPath}/assets/js/myScripts.js"></script>
</body>
</html>
