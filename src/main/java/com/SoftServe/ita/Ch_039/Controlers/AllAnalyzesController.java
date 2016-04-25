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

@WebServlet("/AllAnalyzesController")

public class AllAnalyzesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientService patientService = new PatientService();
        AnalysisService analysisService = new AnalysisService();

        long id = Long.parseLong(request.getParameter("id"));
        String laboratory = request.getParameter("laboratory");

        //get patient by id
        Patient patient = patientService.getPatientById(id);
        //get all analyzes by patient id
        List<Analysis> analyzes = analysisService.getAllAnalyzesByPatientId(id);

        request.setAttribute("analyzes", analyzes);
        request.setAttribute("patient", patient);

        //define previous page
        String direction;
        if(laboratory==null){
            direction ="AllAnalyzes.jsp";
        }else{
            direction ="LaboratoryAnalyzes.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(direction);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AllPatientController");
        dispatcher.forward(request, response);
    }
}
