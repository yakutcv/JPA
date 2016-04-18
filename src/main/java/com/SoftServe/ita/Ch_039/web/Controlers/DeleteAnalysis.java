package com.SoftServe.ita.Ch_039.web.Controlers;

import com.SoftServe.ita.Ch_039.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Entity.Patient;
import com.SoftServe.ita.Ch_039.IO.SQL.AnalyzesDAO;
import com.SoftServe.ita.Ch_039.IO.SQL.PatientDAO;

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
 * Created by ayasintc on 4/8/2016.
 */
@WebServlet("/DeleteAnalysis")
public class DeleteAnalysis extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Patient patient2 = new PatientDAO().getPatientById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("patient", patient2);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AllAnalyzes.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long analyzesId = Long.parseLong(request.getParameter("idA"));
        long patientId = Long.parseLong(request.getParameter("idP"));

        System.out.println(analyzesId);
        System.out.println(patientId);


        List<Analysis> analyzes = new ArrayList<>();
        Patient patient = new PatientDAO().getPatientById(patientId);

        try{
            new AnalyzesDAO().deleteAnalysisById(analyzesId);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            analyzes = new AnalyzesDAO().getAllAnalyzesByPatientId(patientId);
        } catch (Exception e) {
        }

        request.setAttribute("analyzes", analyzes);
        request.setAttribute("patient", patient);

        RequestDispatcher rd = request.getRequestDispatcher("AllAnalyzes.jsp");

        rd.forward(request, response);
    }
}
