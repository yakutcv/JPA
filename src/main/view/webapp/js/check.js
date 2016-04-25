
function checkPatient(event){

    var firstName = $('#form-first-name').val();
    var lastName =  $('#form-last-name').val();
    var date = $('#datetimepicker4').val();
    var id = $('#updatePatient').val();

    if(firstName.length==0||lastName.length==0||date==0) {
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

    $.get('AddPatientController',{'id':id,'name':firstName,'lastName':lastName,'birthDate':date}, function (data) {
        switch (data) {
            case 'Same': {
                event.preventDefault();
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
                    window.location.replace("/AllPatientController");
                    break;
                }

                $.post('AddPatientController',{'name':firstName,'lastName':lastName, 'birthDate':date});
                window.location.replace("/AllPatientController");
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

    $.get('CheckerAnalysisController',{'id':id,'type':type,'report':report,'date':date}, function (data) {
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
                $.post('AddAnalyzesController',{'id':id,'type':type,'report':report,'date':date});
                window.location.replace("AllAnalyzesController?id="+id+"&laboratory=laboratory");
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