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
import java.util.List;

@WebServlet("/EditPatientController")
public class EditPatientController extends HttpServlet {

    PatientService patientService = new PatientService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get all update parameters from HTML form
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");

        //update patient
        patientService.updatePatient(id,name,lastName,birthDate);
        //get all patients for view on table
        List<Patient> patients = patientService.getAllPatients();

        request.setAttribute("patients", patients);
        RequestDispatcher rd = request.getRequestDispatcher("AllPatients.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get patient by id and pass him on HTML form
        Patient patient2 = patientService.getPatientById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("patient2", patient2);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPatient.jsp");
        dispatcher.forward(request, response);
    }
}
