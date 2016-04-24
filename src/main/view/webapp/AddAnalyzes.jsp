<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Analysis</title>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.toastmessage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


    <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/moment-with-locales.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/check.js"></script>
    <script src="${pageContext.request.contextPath}js/jquery.toastmessage.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.backstretch.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/Background.js"></script>
    <script src="${pageContext.request.contextPath}/dateTimePicker/js/bootstrap-datetimepicker.min.js"></script>


<script>
    var d = new Date();
    $(function () {
    $('#datetimepicker2').datetimepicker({
    format: 'DD/MM/YYYY HH:mm',
    sideBySide: true,
    maxDate:d,
    showClose:true
    });
    });
</script>

</head>
<body>
<!-- Top content -->
<div class="top-content">
    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>Patient ${patient.getFullName()}</h3>
                                <p>To add a new analyzes, please fill all fields:</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-pencil"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <div role="form" <%--action="AddAnalyzesController?id=${patient.getId()}" method="POST"--%> class="registration-form">
                                <div class="form-group">
                                    <label class="sr-only control-label" for="inputType">Type Analyzes</label>
                                    <select name="type" class="form-control" id="inputType">
                                        <c:forEach items = "${analysisTypes}" var = "oneType">
                                            <option value="${oneType}">${oneType}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="datetimepicker2" class="col-sm-2 control-label sr-only">Date</label>
                                    <input type='text' class="form-control" id='datetimepicker2' name="date"
                                           placeholder="Date..."/>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="inputReport">Report</label>
                                   <textarea name="report" placeholder="Report..." class="form-report form-control"
                                             id="inputReport" onkeyup="checkReport(this.value)"></textarea>
                                    <span id="wrongLengthReport">Report must contains maximum 200 characters!</span>
                                </div>
                                <input type="hidden" name="id" id="patientId" value="${patient.getId()}">
                                <button type="submit" onclick = "checkAnalysis(event)" class="btn form-control">Add Analyzes</button>
                            </div>
                            <form name="goToListWithAnalyzes" role="form" class="registration-form" action="AllAnalyzesController" method="GET">
                                <input type="hidden" name="id" id="patientId2" value="${patient.getId()}">
                                <button type="submit" class="btn btn-primary">Go back to the list with Analyzes </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer -->
<footer>
    <div class="container">
        <div class="row for-error">
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <div class="col-sm-8 col-sm-offset-2">
                <div class="footer-border"></div>
                <p>Made by Andrew Jasinskiy having a lot of fun. <i class="fa fa-smile-o"></i></p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>