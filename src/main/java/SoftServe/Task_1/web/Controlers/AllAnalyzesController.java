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
import java.util.List;

/**
 * Created by ayasintc on 4/7/2016.
 */
@WebServlet("/AllAnalyzes")

public class AllAnalyzesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        Patient patient = new PatientDAO().getPatientById(id);

        List<Analysis> analyzes = new ArrayList<>();

        try{

            analyzes = new AnalyzesDAO().getAllAnalyzesByPatientId(id);

        }catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("analyzes", analyzes);
        request.setAttribute("patient", patient);

        RequestDispatcher dispatcher = request.getRequestDispatcher("AllAnalyzes.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("AllPatients");
        dispatcher.forward(request, response);

    }


}
