package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Model.Entity.Patient;
import com.SoftServe.ita.Ch_039.Model.DAO.PatientDAO;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.SoftServe.ita.Ch_039.IO.Validators.SelfFormatValidator.validBirthDate;
import static com.SoftServe.ita.Ch_039.IO.Validators.SelfFormatValidator.validName;

/**
 * Created by ayasintc on 4/7/2016.
 */
@WebServlet("/AddPatientController")
public class AddPatientController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String id = request.getParameter("id");

        Patient patient = Patient.newPatientBuilder()
                .setBirthDate(birthDate)
                .setLastName(lastName)
                .setName(name)
                .build();

        if(!validName(patient.getName())){
            out.print("Invalid_name");
        }else if(!validName(patient.getLastName())){
            out.print("Invalid_last_name");
        }else if(!validBirthDate(patient.getBirthDateInString())) {
            out.print("Invalid_birth_date");
        }
        if (id.equals("")){
            List<Patient> tmpPatients;
            try {
                tmpPatients = new PatientDAO().getAllPatients();
                for(Patient onePatient: tmpPatients) {
                    if(onePatient.getFullName().equals(patient.getFullName()) )  {
                        out.print("Same");
                        break;
                    }
                }
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        Patient patient = Patient.newPatientBuilder()
                .setBirthDate(birthDate)
                .setLastName(lastName)
                .setName(name)
                .setStatus(true)
                .build();
        try{
            new PatientDAO().addPatient(patient);
        }catch (PersistenceException e){
            e.printStackTrace();
        }

       List<Patient> patients = new ArrayList<>();
        try{
            patients = new PatientDAO().getAllPatients();
        }catch (PersistenceException e) {
            e.printStackTrace();
        }
        request.setAttribute("patients", patients);
        RequestDispatcher rd = request.getRequestDispatcher("AllPatients.jsp");
        rd.forward(request, response);

    }
}