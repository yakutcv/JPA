package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Model.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Model.Entity.AnalysisType;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by ayasintc on 4/7/2016.
 */
@WebServlet("/AddAnalyzesController")
public class AddAnalyzesController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        Patient patient = new PatientDAO().getPatientById(Long.parseLong(id));
        List<Analysis> analyzes = new ArrayList<>();

        String type = request.getParameter("type");
        String report = request.getParameter("report");
        String date = request.getParameter("date");

        Analysis analysis = Analysis.newAnalysisBuilder()
                .setDate(date)
                .setReport(report)
                .setType(AnalysisType.valueOf(type.toUpperCase()))
                .build();
        try {
            new AnalyzesDAO().addAnalysis(analysis,patient);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        request.setAttribute("patient", patient);
        RequestDispatcher rd = request.getRequestDispatcher("AllAnalyzesController");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<AnalysisType> analysisTypes = Arrays.asList(AnalysisType.values());
        Patient patient = new PatientDAO().getPatientById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("patient", patient);
        request.setAttribute("analysisTypes", analysisTypes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddAnalyzes.jsp");
        dispatcher.forward(request, response);
    }
}
