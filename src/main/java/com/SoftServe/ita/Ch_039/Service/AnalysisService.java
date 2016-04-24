package com.SoftServe.ita.Ch_039.Service;

import com.SoftServe.ita.Ch_039.DAO.AnalysisDAO;
import com.SoftServe.ita.Ch_039.DAO.PatientDAO;
import com.SoftServe.ita.Ch_039.Model.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Model.Entity.Patient;

import javax.persistence.PersistenceException;
import java.util.List;

public class AnalysisService {

    PatientDAO patientDAO = new PatientDAO();
    AnalysisDAO analysisDAO = new AnalysisDAO();

    //add analysis by patient
    public void addAnalysisByPatient(Analysis analysis, Patient patient){
        try {
            analysisDAO.addAnalysis(analysis, patient);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    //get all analyzes by patient id
    public List<Analysis> getAllAnalyzesByPatientId(long id){
        List<Analysis> analyzes;
        try{
            return analyzes = analysisDAO.getAllAnalyzesByPatientId(id);
        }catch (PersistenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    //delete analysis by id
    public void deleteAnalysis(long id){
        try{
            analysisDAO.deleteAnalysisById(id);
        } catch (PersistenceException e){
            e.printStackTrace();
        }
    }

}
