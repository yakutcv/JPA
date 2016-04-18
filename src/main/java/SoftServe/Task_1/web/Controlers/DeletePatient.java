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
 * Created by ayasintc on 4/8/2016.
 */
@WebServlet("/DeletePatient")

public class DeletePatient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        Patient patient = new PatientDAO().getPatientByIdWithAllAnalyzes(id);

        if(patient.getList().isEmpty()) {
            try{
                new PatientDAO().deletePatientById(id);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            try{
                new PatientDAO().changeStatusPatientToFalse(patient);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        List<Patient> patients = new ArrayList<>();

        try{
            patients = new PatientDAO().getAllPatients();
        } catch (Exception e) {
        }

        request.setAttribute("patients", patients);
        request.setAttribute("patient", patient);

        RequestDispatcher rd = request.getRequestDispatcher("AllPatients.jsp");

        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AllPatients.jsp");
        dispatcher.forward(request, response);
    }
}
