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


@WebServlet("/AdminController")
public class AdminController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientService patientService = new PatientService();
        String disabledPatients = request.getParameter("disable");

        List<Patient> patients;

        //switch two buttons.
        //if we click on button "Show all patients we receive list patients with all status (true and false)
        //if we click on button "Show all disabled patients we receive list patients only with status false
        if(disabledPatients == null) {
            patients = patientService.getAllPatientsA();
        }else{
            patients = patientService.getAllPatientsWithStatusFalse();
        }
        request.setAttribute("patients", patients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
        dispatcher.forward(request, response);
    }
}
