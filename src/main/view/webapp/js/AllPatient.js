$(document).ready(function() {
   //tablesorter
    $(function(){
        $("#mytable").tablesorter(
            {headers: { 5: { sorter: false},
                6: {sorter: false},
                7: {sorter: false}}}
        );
    });

    //modal
    $('#deletePatientModal').on('show.bs.modal', function(e) {
        /* var id = $(e.relatedTarget).data('id');*/
        /*  var name = $(e.relatedTarget).data('name');*/
        var Selection = $(e.relatedTarget).data('values').split(",");
        var lastName = Selection[2];
        var id = Selection[1];
        var action = Selection[0];
        $(this).find('#deleteButton').attr('href', action+id);
        $('.debug-url').html('Are you really want to delete patient <strong>' + lastName +" ?" + '</strong>');
    });
});