package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Model.Entity.Analysis;
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


@WebServlet("/DeleteAnalysisController")
public class DeleteAnalysisController extends HttpServlet {

    PatientService patientService = new PatientService();
    AnalysisService analysisService = new AnalysisService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        //get patient by id
        Patient patient = patientService.getPatientById(id);

        request.setAttribute("patient", patient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AllAnalyzes.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long analyzesId = Long.parseLong(request.getParameter("idA"));
        long patientId = Long.parseLong(request.getParameter("idP"));

        //get patient by id
        Patient patient = patientService.getPatientById(patientId);

        //delete analysis
        analysisService.deleteAnalysis(analyzesId);

        //get analysis by patient id
        List<Analysis> analyzes = analysisService.getAllAnalyzesByPatientId(patientId);

        request.setAttribute("analyzes", analyzes);
        request.setAttribute("patient", patient);
        RequestDispatcher rd = request.getRequestDispatcher("AllAnalyzes.jsp");
        rd.forward(request, response);
    }
}
