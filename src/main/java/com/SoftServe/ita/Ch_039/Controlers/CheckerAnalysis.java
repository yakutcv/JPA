package com.SoftServe.ita.Ch_039.Controlers;

import com.SoftServe.ita.Ch_039.IO.Validators.SelfFormatValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/CheckerAnalysis")

public class CheckerAnalysis extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String report = request.getParameter("report");
        String date = request.getParameter("date");

        if(!SelfFormatValidator.validAnalysisType(type)) {
            out.print("invalid_type");
        }else if(!SelfFormatValidator.validAnalyzesDate(date)){
            out.print("Invalid_date");
        }else if(!SelfFormatValidator.validReport(report)) {
            out.print("Invalid_report");
        }
    }
}