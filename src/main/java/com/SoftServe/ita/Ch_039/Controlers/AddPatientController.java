package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Model.Entity.Patient;
import com.SoftServe.ita.Ch_039.Service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.SoftServe.ita.Ch_039.IO.Validators.SelfFormatValidator.validBirthDate;
import static com.SoftServe.ita.Ch_039.IO.Validators.SelfFormatValidator.validName;

@WebServlet("/AddPatientController")
public class AddPatientController extends HttpServlet {

    PatientService patientService = new PatientService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String id = request.getParameter("id");

        //create tmpPatient
        Patient patient = Patient.newPatientBuilder()
                .setName(name)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .build();

        //valid patient
        if(!validName(patient.getName())){
            out.print("Invalid_name");
        }else if(!validName(patient.getLastName())){
            out.print("Invalid_last_name");
        }else if(!validBirthDate(patient.getBirthDateInString())) {
            out.print("Invalid_birth_date");
        }

        //if id equals null we create new patient
        if (id.equals("")){
            List<Patient> tmpPatients = patientService.getAllPatients();
            //check new patient on equals full name
            for(Patient onePatient: tmpPatients) {
                if(onePatient.getFullName().equals(patient.getFullName()) )  {
                    out.print("Same");
                    break;
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");

        //add patient
        patientService.addPatient(name,lastName,birthDate);

        //get all patients
        List<Patient> patients = patientService.getAllPatients();

        request.setAttribute("patients", patients);
        RequestDispatcher rd = request.getRequestDispatcher("AllPatients.jsp");
        rd.forward(request, response);
    }
}
