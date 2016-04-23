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


@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Patient> patients = new ArrayList<>();
        try{
            patients = new PatientDAO().getAllPatientsWithStatusFalse();
        }catch (PersistenceException e) {
            e.printStackTrace();
        }
        request.setAttribute("patients", patients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Patient> patients = new ArrayList<>();

        String disabledPatients = request.getParameter("disable");

        if(disabledPatients == null) {
            try{
                patients = new PatientDAO().getAllPatientsA();
            }catch (PersistenceException e) {
                e.printStackTrace();
            }
        }else{
            try{
                patients = new PatientDAO().getAllPatientsWithStatusFalse();
            }catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("patients", patients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
        dispatcher.forward(request, response);
    }
}
