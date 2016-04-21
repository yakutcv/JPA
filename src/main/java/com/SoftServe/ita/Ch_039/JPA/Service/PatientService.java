package com.SoftServe.ita.Ch_039.JPA.Service;

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
public class PatientService {
    private EntityManager manager = Persistence.createEntityManagerFactory("HOSPITAL").createEntityManager();
    private String TABLE_NAME = "Patient";

    public boolean addPatient(Patient patient) throws PersistenceException{
        try {
            manager.getTransaction().begin();
            manager.persist(patient);
            manager.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Couldn't add patient!");
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }


    public boolean deletePatientById(long id) throws PersistenceException{
        try{
        manager.getTransaction().begin();
        manager.remove(manager.find(Patient.class, id));
        manager.getTransaction().commit();
        return true;
        }catch (PersistenceException e) {
            System.out.println("Couldn't delete patient with id: " + id);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }


    public boolean updatePatient (Patient patient) throws PersistenceException{
        try{
            manager.getTransaction().begin();
            manager.merge(patient);
            manager.getTransaction().commit();
            return true;
        }catch (PersistenceException e) {
            System.out.println("Couldn't update patient " + patient);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }

    public Patient getPatientById(long id) throws PersistenceException{
        Patient patient = null;
        try{
            manager.getTransaction().begin();
            patient = manager.getReference(Patient.class, id);
            manager.getTransaction().commit();
        }catch (PersistenceException e) {
            System.out.println("Couldn't find patient with id: " + id);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return patient;
    }

    public synchronized List<Patient> getAllPatients() throws PersistenceException{
        List<Patient> patients = new ArrayList<>();
        try{
            manager.getTransaction().begin();
            Query query = manager.createNamedQuery("GET_ALL_PATIENTS", Patient.class);
            patients = query.getResultList();
            manager.getTransaction().commit();
        }catch (PersistenceException e) {
            System.out.println("Couldn't read patients from table " + TABLE_NAME);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        Collections.sort(patients);
        return patients;
    }

    public List<Patient> getAllPatientsWithStatusFalse() throws PersistenceException{
        List<Patient> patients = new ArrayList<>();
        try{
            manager.getTransaction().begin();
            Query query = manager.createNamedQuery("GET_ALL_PATIENTS_WITH_STATUS_FALSE", Patient.class);
            patients = query.getResultList();
            manager.getTransaction().commit();
        }catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("Couldn't read patients from table " + TABLE_NAME);
            manager.getTransaction().rollback();
        }
        Collections.sort(patients);
        return patients;
    }

    public boolean changeStatusPatientToFalse(Patient patient)throws PersistenceException{
        try{
            manager.getTransaction().begin();
            patient.setStatus(false);
            manager.merge(patient);
            manager.getTransaction().commit();
            return true;
        }catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("Couldn't update patient status " + patient);
            manager.getTransaction().rollback();
        }
        return false;
    }


    //!!! don't working

    public Patient getPatientByIdWithAllAnalyzes(long id)throws PersistenceException {
        Patient patient = null;
        try{
            manager.getTransaction().begin();



            Query query = manager.createNamedQuery("GET_PATIENT_BY_ID_WITH_ALL_ANALYZES", Patient.class);
            query.setParameter("id",id);
            patient = (Patient) query.getSingleResult();


            manager.getTransaction().commit();
        }catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("Couldn't find patient with id " + id);
            manager.getTransaction().rollback();
        }
        return patient;
    }
}
