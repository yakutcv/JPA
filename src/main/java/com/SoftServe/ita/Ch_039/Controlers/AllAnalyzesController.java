package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Model.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Model.Entity.Patient;
import com.SoftServe.ita.Ch_039.Model.DAO.AnalyzesDAO;
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

/**
 * Created by ayasintc on 4/7/2016.
 */
@WebServlet("/AllAnalyzesController")

public class AllAnalyzesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        Patient patient = new PatientDAO().getPatientById(id);

        List<Analysis> analyzes = new ArrayList<>();

        try{

            analyzes = new AnalyzesDAO().getAllAnalyzesByPatientId(id);

        }catch (PersistenceException e) {
            e.printStackTrace();
        }

        request.setAttribute("analyzes", analyzes);
        request.setAttribute("patient", patient);

        RequestDispatcher dispatcher = request.getRequestDispatcher("AllAnalyzes.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("AllPatientController");
        dispatcher.forward(request, response);

    }


}
