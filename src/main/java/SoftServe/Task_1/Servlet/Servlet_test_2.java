//<%--
//        Created by IntelliJ IDEA.
//        User: ayasintc
//        Date: 4/7/2016
//        Time: 2:51 PM
//        To change this template use File | Settings | File Templates.
//        --%>
//<%@ page contentType="text/html;charset=UTF-8" language="java" %>
//<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
//
//<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
//<html>
//<head>
//<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
//<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
//<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" />
//
//<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
//<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
//<script src="${pageContext.request.contextPath}/js/moment-with-locales.min.js"></script>
//<script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
//
//<script>
//$(document).ready(function(){
//        $("#mytable #checkall").click(function () {
//        if ($("#mytable #checkall").is(':checked')) {
//        $("#mytable input[type=checkbox]").each(function () {
//        $(this).prop("checked", true);
//        });
//
//        } else {
//        $("#mytable input[type=checkbox]").each(function () {
//        $(this).prop("checked", false);
//        });
//        }
//        });
//
//        $("[data-toggle=tooltip]").tooltip();
//        });
//
//
//</script>
//
//
//<title>All Patients</title>
//
//</head>
//
//<body>
//
//<c:set var="count" value="${1}"/>
//
//<h2> All Patients</h2>
//
//<table border = "1">
//<tr>
//<th> № </th>
//<th> Name </th>
//<th> Last Name </th>
//<th> Birth Date </th>
//</tr>
//<c:forEach items = "${patients}" var = "patient">
//
//<tr>
//<td><c:out value="${count}"/></td>
//<c:set var="count" value="${count+1}"/>
//
//<td> ${patient.name} </td>
//<td> ${patient.lastName} </td>
//<td> ${patient.getBirthDateInString()} </td>
//<td>
//<form name = "edit" action = "EditPatientController?id=${patient.id}" method = "POST">
//<input type = "submit" value = "Edit">
//</form>
//</td>
//
//<td>
//
//
//<button type="button" id="deleteButton" class="btn btn-danger" data-toggle="modal" data-target="#myModal" onclick="${patient}">
//        Delete
//</button>
//
//
//<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
//<div class="modal-dialog" role="document">
//<div class="modal-content">
//<div class="modal-header">
//
//<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
//<h3 class="modal-title text-center" id="myModalLabel">Delete patient ${patient.name} ${patient.lastName} ? </h3>
//</div>
//
//<div class="modal-body text-center">
//
//<a class="btn btn-danger btn-lg" type="submit" href = "<c:url value = "DeletePatient?id=${patient.id}"/>">Delete</a>
//
//<a class="btn btn-primary btn-lg col-md-offset-2" type="submit" href = "<c:url value = "Patients"/>">Close</a>
//
//<br>
//<br>
//
//<div class="modal-footer text-center">
//<blockquote>
//<small>"you must remember that undo actions will already be impossible</small>
//</blockquote>
//
//
//</div>
//</div>
//</div>
//</div>
//</div>
//
//
//
//</td>
//
//<td>
//<form name = "analyzes" action = "AllAnalyzes?id=${patient.id}" method = "POST">
//<input type = "submit" value = "Analyzes">
//</form>
//</td>
//</tr>
//
//
//</div>
//<input type="hidden" id="tmpId" value = "patientId" name = "id" value="${patient.getId()}">
//</div>
//
//</c:forEach>
//</table>
//<form name = "goIndex" action = "Patients" method = "POST">
//<input type = "submit" value = "Go back to the main page">
//</form>
//
//
//
//
//</body>
//</html>
