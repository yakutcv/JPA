
$(function () {
    var d = new Date();
    $('#datetimepicker4').datetimepicker({
        format: 'DD/MM/YYYY',
        maxDate:d,
        howClose:true
    });
});


var d = new Date();
$(function () {
    $('#datetimepicker2').datetimepicker({
        format: 'DD/MM/YYYY HH:mm',
        sideBySide: true,
        maxDate:d,
        showClose:true
    });
});