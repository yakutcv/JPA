package com.SoftServe.ita.Ch_039.JPA.Service;

import com.SoftServe.ita.Ch_039.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Entity.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ayasintc on 4/5/2016.
 */
public class AnalyzesService {

    private EntityManager manager = Persistence.createEntityManagerFactory("HOSPITAL").createEntityManager();
    private String ANALYSIS_TABLE_NAME = "Analysis";

    public boolean addAnalysis(Analysis analysis, Patient patient) throws PersistenceException {
        try {
            manager.getTransaction().begin();
            analysis.setPatient(patient);
            manager.persist(analysis);
            manager.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Couldn't add " + analysis + " into table " + ANALYSIS_TABLE_NAME);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }

    public boolean deleteAnalysisById(long id) throws PersistenceException {
        try {
            manager.getTransaction().begin();
            manager.remove(manager.find(Analysis.class, id));
            manager.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Couldn't find analysis with id : " + id);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }

    public boolean updateAnalysis(Analysis analysis) throws PersistenceException {
        try {
            manager.getTransaction().begin();
            manager.merge(analysis);
            manager.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Couldn't update analysis " + analysis);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }

    public Analysis getAnalyzesById(long id) throws PersistenceException {
        Analysis analysis = null;
        try {
            manager.getTransaction().begin();
            analysis = manager.getReference(Analysis.class, id);
            manager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Couldn't find analyzes with id: " + id);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return analysis;
    }

    public boolean addListAnalysis(List<Analysis> analysisList, Patient patient) throws PersistenceException {
        try {
            manager.getTransaction().begin();
            for (Analysis a : analysisList) {
                a.setPatient(patient);
                manager.persist(a);
            }
            manager.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("Couldn't add List Analyzes for " + patient.getName() + " to table Patients");
            manager.getTransaction().rollback();
        }
        return false;
    }

    public synchronized List<Analysis> getAllAnalyzes() throws PersistenceException {
        List<Analysis> analyzes = new ArrayList<>();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNamedQuery("GET_ALL_ANALYZES", Analysis.class);
            analyzes = query.getResultList();
            manager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Couldn't read analyzes from table " + ANALYSIS_TABLE_NAME);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        Collections.sort(analyzes);
        return analyzes;
    }

    public List<Analysis> getAllAnalyzesByPatient(Patient patient) throws PersistenceException {
        List<Analysis> analyzes = new ArrayList<>();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNamedQuery("GET_ANALYZES_BY_PATIENT", Analysis.class);
            analyzes = query.setParameter("patient", patient).getResultList();
            manager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Couldn't read List Analyzes from " + patient.getName());
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        Collections.sort(analyzes);
        return analyzes;
    }


    public List<Analysis> getAllAnalyzesByPatientId(long id) throws PersistenceException {
        List<Analysis> analyzes = new ArrayList<>();
        try {
            manager.getTransaction().begin();
            Query query = manager.createNamedQuery("GET_ANALYZES_BY_PATIENT_ID", Analysis.class);
            analyzes = query.setParameter("id", id).getResultList();
            manager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Couldn't read List Analyzes from patient with id" + id);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        Collections.sort(analyzes);
        return analyzes;
    }
}

