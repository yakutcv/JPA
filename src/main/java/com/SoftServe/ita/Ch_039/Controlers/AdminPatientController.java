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
import java.util.ArrayList;
import java.util.List;




@WebServlet("/AdminPatientController")
public class AdminPatientController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        new PatientDAO().deletePatientById(id);
        List<Patient> patients = new ArrayList<>();
        try{
            patients = new PatientDAO().getAllPatients();
        } catch (PersistenceException e) {
            }
        request.setAttribute("patients", patients);
        RequestDispatcher rd = request.getRequestDispatcher("AdminController");
        rd.forward(request, response);
    }


}
