package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.Model.Entity.Patient;
import com.SoftServe.ita.Ch_039.Service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Phenom on 24.04.2016.
 */
@WebServlet("/ChangePatientStatusController")
public class ChangePatientStatusController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PatientService patientService = new PatientService();
        //change status for patient
        PrintWriter out = response.getWriter();
        String status = request.getParameter("status");
        String id = request.getParameter("id");


        Patient patient = patientService.getPatientById(Long.parseLong(id));

        if(status.equals("true")){
            patient.setStatus(false);
            patientService.updatePatient(patient);
            out.print("Success");
        }else {
            patient.setStatus(true);
            patientService.updatePatient(patient);
            out.print("Success");
        }
    }
}
