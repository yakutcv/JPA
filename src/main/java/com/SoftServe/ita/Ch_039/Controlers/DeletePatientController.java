package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Model.Entity.Patient;
import com.SoftServe.ita.Ch_039.Service.AnalysisService;
import com.SoftServe.ita.Ch_039.Service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ayasintc on 4/8/2016.
 */
@WebServlet("/DeletePatientController")

public class DeletePatientController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientService patientService = new PatientService();
        AnalysisService analysisService = new AnalysisService();

        long id = Long.parseLong(request.getParameter("id"));

        //delete patient by id
        patientService.deletePatient(id);

        //get all patients
        List<Patient> patients = patientService.getAllPatients();

        request.setAttribute("patients", patients);
        RequestDispatcher rd = request.getRequestDispatcher("AllPatients.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AllPatients.jsp");
        dispatcher.forward(request, response);
    }
}
