package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Model.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Model.Entity.AnalysisType;
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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ayasintc on 4/7/2016.
 */
@WebServlet("/AddAnalyzesController")
public class AddAnalyzesController extends HttpServlet {

    PatientService patientService = new PatientService();
    AnalysisService analysisService = new AnalysisService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String type = request.getParameter("type");
        String report = request.getParameter("report");
        String date = request.getParameter("date");

        //get patient by id
        Patient patient = patientService.getPatientById(id);

        //create analysis
        Analysis analysis = Analysis.newAnalysisBuilder()
                .setDate(date)
                .setReport(report)
                .setType(AnalysisType.valueOf(type.toUpperCase()))
                .build();

        //add analysis to patient
        analysisService.addAnalysisByPatient(analysis,patient);

        request.setAttribute("patient", patient);
        RequestDispatcher rd = request.getRequestDispatcher("AllAnalyzesController?laboratory=laboratory");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pass all our analysis type to <SELECT> tag on jsp
        List<AnalysisType> analysisTypes = Arrays.asList(AnalysisType.values());
        //get patient by id
        Patient patient = patientService.getPatientById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("patient", patient);
        request.setAttribute("analysisTypes", analysisTypes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddAnalyzes.jsp");
        dispatcher.forward(request, response);
    }
}
