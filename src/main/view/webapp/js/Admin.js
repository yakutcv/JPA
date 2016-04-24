
$(document).ready(function() {

    //change status patient by click on button
    $('.statusPatient').click(function(event) {
        var id = $(this).attr("data-id");
        var status = $(this).val();

        if(status=='true'){
            $.get('ChangePatientStatusController',{'id':id,'status':status});
            $(this).val('false').removeClass('btn-success').addClass('btn-warning');
        }else{
            $.get('ChangePatientStatusController',{'id':id,'status':status});
                $(this).val('true').removeClass('btn-warning').addClass('btn-success');
        }
    });


    //table sorter
    $(function(){
        $("#mytable").tablesorter(
            {headers: { 5: { sorter: false},
                        6: {sorter: false}}}
        );
    });

    //modal window for delete
    $('#deletePatientModal').on('show.bs.modal', function(e) {
        var Selection = $(e.relatedTarget).data('values').split(",");
        var lastName = Selection[2];
        var id = Selection[1];
        var action = Selection[0];
        $(this).find('#deleteButton').attr('href', action+id);
        $('.debug-url').html('Are you sure you want to delete patient <strong>' + lastName +" with all his Analyzes ?" + '</strong>');
    });
});








