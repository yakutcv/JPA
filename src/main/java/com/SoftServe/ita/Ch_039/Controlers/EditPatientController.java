package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Entity.Patient;
import com.SoftServe.ita.Ch_039.IO.SQL.PatientDAO;

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

@WebServlet("/EditPatientController")
public class EditPatientController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Patient patient2 = new PatientDAO().getPatientById(Long.parseLong(id));
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");

        Patient patient = Patient.newPatientBuilder()
                .setBirthDate(birthDate)
                .setLastName(lastName)
                .setName(name)
                .setId(patient2.getId())
                .build();
        try {
            new PatientDAO().updatePatient(patient);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        List<Patient> patients = new ArrayList<>();
        try {
            patients = new PatientDAO().getAllPatients();
        } catch (PersistenceException e) {}
        request.setAttribute("patients", patients);
        request.setAttribute("patient", patient);
        RequestDispatcher rd = request.getRequestDispatcher("AllPatients.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Patient patient2 = new PatientDAO().getPatientById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("patient2", patient2);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPatient.jsp");
        dispatcher.forward(request, response);
    }
}
