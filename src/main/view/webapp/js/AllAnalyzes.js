$(document).ready(function() {

    //table sorter
    $(function(){
        $("#mytable").tablesorter(
            {headers: { 4: { sorter: false}}}
        );
    });
    //modal window
    $('#deleteAnalysisModal').on('show.bs.modal', function(e) {
        var Selection = $(e.relatedTarget).data('values').split(",");
        var action = Selection[0];
        var patientId = Selection[1];
        var analysisId = Selection[2];
        var analysisType = Selection[3];
        var analysisDate = Selection[4];
        $(this).find('#deleteButton').attr('href', action+patientId+analysisId);
        $('.debug-url').html('Are you really want to delete analysis <strong>' + analysisType + " by " + analysisDate + "?" + '</strong>');
    });
});