package SoftServe.Task_1.web.Controlers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static SoftServe.Task_1.IO.Validators.SelfFormatValidator.*;


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

        if(!validAnalysisType(type)) {
            out.print("invalid_type");
        }else if(!validAnalyzesDate(date)){
            out.print("Invalid_date");
        }else if(!validReport(report)) {
            out.print("Invalid_report");
        }
    }
}
