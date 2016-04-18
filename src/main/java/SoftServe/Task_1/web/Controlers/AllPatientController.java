package SoftServe.Task_1.web.Controlers;

import SoftServe.Task_1.Entity.Patient;
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
 * Created by ayasintc on 4/7/2016.
 */
    @WebServlet("/Patients")
public class AllPatientController extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PrintWriter out = response.getWriter();

        List<Patient> patients = new ArrayList<>();
        try{
            patients = new PatientDAO().getAllPatients();
        }catch (Exception e) {
            e.printStackTrace();
            //out.print("Can't view all patients from DB");
        }
        request.setAttribute("patients", patients);

        RequestDispatcher dispatcher = request.getRequestDispatcher("AllPatients.jsp");

        dispatcher.forward(request, response);

    }
}
