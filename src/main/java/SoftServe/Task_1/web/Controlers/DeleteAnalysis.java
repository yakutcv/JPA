package SoftServe.Task_1.web.Controlers;

import SoftServe.Task_1.Entity.Analysis;
import SoftServe.Task_1.Entity.Patient;
import SoftServe.Task_1.IO.SQL.AnalyzesDAO;
import SoftServe.Task_1.IO.SQL.PatientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
