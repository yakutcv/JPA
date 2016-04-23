/*


var status = document.getElementById("patientStatus");

status.addEventListener("click", changeStatus(event));

function changeStatus(event) {

    if (event.target.value == "true") {
        event.target.value = "false";
        */
/*  span.innerHTML = passData[passId];*//*

    }
    else {
        event.target.value = "true";
        */
/*  span.innerHTML = replacePassword(passData[passId]);*//*

    }
}

*/

$(document).ready(function() {



$('#patientStatus').click(function(event){
    if ($(event.target).val() == 'true'){
        $(event.target).val('false');
        $(event.target).removeClass('btn-success');
        $(event.target).addClass('btn-warning')
    }else{
        $(event.target).val('true');
        $(event.target).removeClass('btn-warning');
        $(event.target).addClass('btn-success')
    }
});

 /*   $('#patientStatus').click(function(event){

        if($(this).val()=='true'){
            $(this).val('false');
            $(this).removeClass('btn-success').addClass('btn-warning');
        }

            $(this).val('true');
        $(this).removeClass('btn-warning').addClass('btn-success');


    });*/






});










