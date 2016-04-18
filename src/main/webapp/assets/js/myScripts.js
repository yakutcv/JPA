
jQuery(document).ready(function() {
	
       /* Fullscreen background*/


    $.backstretch("assets/img/backgrounds/1.jpg");
    
        /*Login form validation*/

    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
    
        /*Registration form validation*/

    $('.registration-form input[type="text"], .registration-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.registration-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });

});


//modal
$('#myModal').on('show.bs.modal', function(e) {
    /* var id = $(e.relatedTarget).data('id');*/
    /*  var name = $(e.relatedTarget).data('name');*/
    var Selection = $(e.relatedTarget).data('values').split(",");
    var action = Selection[0];
    var patientId = Selection[1];
    var analysisId = Selection[2];
    var analysisType = Selection[3];
    var analysisDate = Selection[4];
    $(this).find('#deleteButton').attr('href', action+patientId+analysisId);
    $('.debug-url').html('Are you really want to delete analysis <strong>' + analysisType + " by " + analysisDate + "?" + '</strong>');
});

//ajax post and get
function checkPatient(event){

    var firstName = $('#form-first-name').val();
    var lastName =  $('#form-last-name').val();
    var date = $('#datetimepicker4').val();
    var id = $('#updatePatient').val();

    if(firstName.length==0||lastName.length==00||date==0) {
        event.preventDefault();
        $().toastmessage('showToast', {
            text: "Please, enter all fields!",
            sticky: false,
            position: 'top-center',
            type: 'error',
            inEffectDuration: 1000,
            stayTime: 2000,
            close: function () {console.log("toast is closed ...");
            }
        });
}

    $.get('AddPatient',{'id':id,'name':firstName,'lastName':lastName,'birthDate':date}, function (data) {
        switch (data) {
            case 'Same': {
                event.preventDefault();
               /* $('#error_message').css({'display': 'inline'});*/
                /* $('#error_message').html("Patient " + firstName + " " + lastName + " already exist!");*/
                $().toastmessage('showToast', {
                    text: "Patient " + firstName + " " + lastName + " already exist!",
                    sticky: false,
                    position: 'top-center',
                    type: 'error',
                    inEffectDuration: 600,
                    stayTime: 2000,
                    close: function () {console.log("toast is closed ...");
                    }
                });
                break;
            }

            case 'Invalid_name': {
                 event.preventDefault();
                $().toastmessage('showToast', {
                    text: "Invalid name " + firstName + " .Please retry your input!",
                    sticky: false,
                    position: 'top-center',
                    type: 'error',
                    inEffectDuration: 600,
                    stayTime: 2000,
                    close: function () {
                        console.log("toast is closed ...");
                    }
                });
                break;
            }

            case 'Invalid_last_name': {
                event.preventDefault();
                $().toastmessage('showToast', {
                    text: "Invalid last name " + lastName + " .Please retry your input!",
                    sticky: false,
                    position: 'top-center',
                    type: 'error',
                    inEffectDuration: 600,
                    stayTime: 2000,
                    close: function () {
                        console.log("toast is closed ...");}
                });
                break;
            }

            case 'Invalid_birth_date': {
                event.preventDefault();
                $().toastmessage('showToast', {
                    text: "Invalid birth date" + date + " .Please retry your input!",
                    sticky: false,
                    position: 'top-center',
                    type: 'error',
                    inEffectDuration: 600,
                    stayTime: 2000,
                    close: function () {
                        console.log("toast is closed ...");
                    }
                });
                break;
            }
            default :{
                if (id){
                    $.post('EditPatientController',{'id':id,'name':firstName,'lastName':lastName, 'birthDate':date});
                    window.location.replace("/Patients");
                    break;
                }

                $.post('AddPatient',{'name':firstName,'lastName':lastName, 'birthDate':date});
                window.location.replace("/Patients");
                break;
                }
            }
    });
}

function checkAnalysis(event) {
    var type = $('#inputType').val();
    var report = $('#inputReport').val();
    var date = $('#datetimepicker2').val();
    var id = $('#patientId').val();


    if(type.length==0||report.length==00||date==0) {
        event.preventDefault();
        $().toastmessage('showToast', {
            text: "Please, enter all fields!",
            sticky: false,
            position: 'top-center',
            type: 'error',
            inEffectDuration: 1000,
            stayTime: 2000,
            close: function () {console.log("toast is closed ...");
            }
        });
    }

    $.get('CheckerAnalysis',{'id':id,'type':type,'report':report,'date':date}, function (data) {
        switch (data){
            case 'invalid_type': {
                event.preventDefault();
                $().toastmessage('showToast', {
                    text: "Invalid type " + type + " .Please retry your input!",
                    sticky: false,
                    position: 'top-center',
                    type: 'error',
                    inEffectDuration: 600,
                    stayTime: 2000,
                    close: function () {
                        console.log("toast is closed ...");
                    }
                });
                break;
            }

            case 'Invalid_date': {
                event.preventDefault();
                $().toastmessage('showToast', {
                    text: "Invalid date " + date + " .Please retry your input!",
                    sticky: false,
                    position: 'top-center',
                    type: 'error',
                    inEffectDuration: 600,
                    stayTime: 2000,
                    close: function () {
                        console.log("toast is closed ...");
                    }
                });
                break;
            }

            case 'Invalid_report': {
                event.preventDefault();
                $().toastmessage('showToast', {
                    text: "Invalid report " + report + " .Please retry your input!",
                    sticky: false,
                    position: 'top-center',
                    type: 'error',
                    inEffectDuration: 600,
                    stayTime: 2000,
                    close: function () {
                        console.log("toast is closed ...");
                    }
                });
                break;
            }
            default :
                $.post('AddAnalyzes',{'id':id,'type':type,'report':report,'date':date});
                window.location.replace("AllAnalyzes?id="+id);
                break;
        }
    });
}



var patternName =/^[A-Za-z]+$/i;

var name = document.querySelector("#wrongNameFormat");
var lastName = document.querySelector("#wrongNameFormatLM");
function showErrorForEmptyFields(value){
    if(name.value==null||lastName.value==null) {
        alert();
        checkName(value);
        checkLastName(value);
    }
}

//name
function checkName(value){
    if ((value.length < 2)||(value.length >20)) {
        document.querySelector("#wrongLength").style.display = "inline";
    }else{
        document.querySelector("#wrongLength").style.display = "none";
    }
     if(!patternName.test(value)) {
         document.querySelector("#wrongNameFormat").style.display = "inline";
    }
    else {
         document.querySelector("#wrongNameFormat").style.display = "none";
    }
}
//lastname
function checkLastName(value){
    if ((value.length < 2)||(value.length >20)) {
        document.querySelector("#wrongLengthLM").style.display = "inline";
    }else{
        document.querySelector("#wrongLengthLM").style.display = "none";
    }
    if(!patternName.test(value)) {
        document.querySelector("#wrongNameFormatLM").style.display = "inline";
    }
    else {
        document.querySelector("#wrongNameFormatLM").style.display = "none";
    }
}

//report
function checkReport(value) {
    if (value.length > 200) {
        document.querySelector("#wrongLengthReport").style.display = "inline";
    } else {
        document.querySelector("#wrongLengthReport").style.display = "none";
    }
}

$(function () {
    var d = new Date();
    $('#datetimepicker4').datetimepicker({
        format: 'DD/MM/YYYY',
        maxDate:d
    });
});
