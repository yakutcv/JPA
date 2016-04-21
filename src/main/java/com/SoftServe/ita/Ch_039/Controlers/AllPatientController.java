package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Entity.Patient;
import com.SoftServe.ita.Ch_039.IO.SQL.PatientDAO;

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

/**
 * Created by ayasintc on 4/7/2016.
 */
    @WebServlet("/Patients")
public class AllPatientController extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Patient> patients = new ArrayList<>();
        try{
            patients = new PatientDAO().getAllPatients();
        }catch (PersistenceException e) {
            e.printStackTrace();
        }
        request.setAttribute("patients", patients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AllPatients.jsp");
        dispatcher.forward(request, response);
    }
}
