<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" />--%>

    <script src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
   <%-- <script src="${pageContext.request.contextPath}/js/moment-with-locales.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>--%>



    <title>Admin</title>
</head>
<body>
<c:set var="count" value="${1}"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h4>Admin</h4>
            <div class="table-responsive">
                <table id="mytable" class="table table-bordred table-striped">
                    <thead>
                    <th>#</th>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>Birthdate</th>
                    <th>Status</th>

                    <th>Delete</th>
                    </thead>
                    <c:forEach items = "${patients}" var = "patient">
                        <tbody>
                        <tr>
                            <td id="counter"><c:out value="${count}"/></td>
                            <c:set var="count" value="${count+1}"/>
                            <td>${patient.lastName}</td>
                            <td>${patient.name} </td>
                            <td>${patient.getBirthDateInString()}</td>
                            <td>
                                <c:set var="status" value="${patient.getStatus()}"/>
                                <c:if test="${status==true}">
                                    <input type="button" class="btn btn-success btn-sm" id="patientStatus" value="${patient.getStatus()}">
                                </c:if>

                                <c:if test="${status==false}">
                                    <input type="button" class="btn btn-warning btn-sm" id="patientStatus" value="${patient.getStatus()}">
                                </c:if>

                            </td>

                            <td>
                                <div class="col-sm-5">
                                    <p data-placement="top" data-toggle="tooltip" title="Delete">
                                        <button  class="btn btn-danger" id="deletePatient" data-values="AdminPatientController?id=,${patient.id},${patient.lastName}" data-toggle="modal" data-target="#deletePatientModal">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </p>
                                </div>
                            </td>

                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </div>
            <input type="hidden" id="disable" name = "disable" value="disable">

        </div>



        <div>
            <a type="submit" class="btn btn-default btn-lg" href= "<c:url value = "index.jsp"/>" >
                <span class="glyphicon glyphicon-arrow-left"></span>Go back to the main page</a>

            <a type="submit" class="btn btn-primary btn-lg" href= "<c:url value = "Admin.jsp"/>" >
                <span class="glyphicon glyphicon-refresh"></span>Reset</a>


            <a class="btn btn-info btn-lg"  href= "<c:url value = "/AdminController"/>" role="button">
            <span class="glyphicon glyphicon-user"></span> Show all patients </a>


            <a class="btn btn-warning btn-lg"  href= "<c:url value = "/AdminController?disable=disable"/>" role="button">
                <span class="glyphicon glyphicon-user"></span> Show all disabled patients </a>


        </div>

    </div>
</div>

<%--
<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align" id="Heading1">Edit Your Detail</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <input class="form-control " type="text" placeholder="Mohsin">
                </div>
                <div class="form-group">
                    <input class="form-control " type="text" placeholder="Irshad">
                </div>
                <div class="form-group">
                    <textarea rows="2" class="form-control" placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>
                </div>
            </div>
            <div class="modal-footer ">
                <button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span> Update</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
--%>

<%-- </form>--%>
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


<script>
    $('#deletePatientModal').on('show.bs.modal', function(e) {
        /* var id = $(e.relatedTarget).data('id');*/
        /*  var name = $(e.relatedTarget).data('name');*/
        var Selection = $(e.relatedTarget).data('values').split(",");
        var lastName = Selection[2];
        var id = Selection[1];
        var action = Selection[0];
        $(this).find('#deleteButton').attr('href', action+id);
        $('.debug-url').html('Are you sure you want to delete patient <strong>' + lastName +" with all his Analyzes ?" + '</strong>');
    });
</script>
<script>
    var patietnId =


</script>




<script src="${pageContext.request.contextPath}/js/Admin.js"></script>
</body>

</html>