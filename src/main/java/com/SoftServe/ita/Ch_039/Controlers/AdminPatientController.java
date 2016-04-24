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

@WebServlet("/AdminPatientController")
public class AdminPatientController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientService patientService = new PatientService();
        long id = Long.parseLong(request.getParameter("id"));

        //delete patient by id
        patientService.deletePatientByIdA(id);

        //get all patients
        List<Patient> patients = patientService.getAllPatients();

        request.setAttribute("patients", patients);
        RequestDispatcher rd = request.getRequestDispatcher("AdminController");
        rd.forward(request, response);
    }
}
