package com.SoftServe.ita.Ch_039.Service;

import com.SoftServe.ita.Ch_039.DAO.PatientDAO;
import com.SoftServe.ita.Ch_039.Model.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Model.Entity.Patient;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    PatientDAO patientDAO = new PatientDAO();

    //add patient
    public void addPatient(String name, String lastName, String birthDate){
        Patient patient = Patient.newPatientBuilder()
                .setBirthDate(birthDate)
                .setLastName(lastName)
                .setName(name)
                .setStatus(true)
                .build();
        try{
            patientDAO.addPatient(patient);
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    //update patient
    public void updatePatient(long id, String name, String lastName, String birthDate){
        Patient patient = Patient.newPatientBuilder()
                .setBirthDate(birthDate)
                .setLastName(lastName)
                .setName(name)
                .setId(id)
                .build();
        try{
            patientDAO.updatePatient(patient);
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    //update patient 2
    public void updatePatient(Patient patient){
        try{
            patientDAO.updatePatient(patient);
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }


    //get all patients
    public List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();
        try {
            return patients = patientDAO.getAllPatients();
        }catch (PersistenceException e){
            e.printStackTrace();
        }
        return null;
    }

    //get patient by id
    public Patient getPatientById(long id){
        try {
            return patientDAO.getPatientById(id);
        }catch (PersistenceException e){
            e.printStackTrace();
        }
        return null;
    }
    //delete patient to check on the availability of analyzes

    public void deletePatient(long id) {
        Patient patient = patientDAO.getPatientById(id);
        List<Analysis> analyzes = new AnalysisService().getAllAnalyzesByPatientId(id);

        if(analyzes.isEmpty()) {
            try{
                patientDAO.deletePatientById(id);
            }catch (PersistenceException e) {
                e.printStackTrace();
            }
        }else{
            try{
                patientDAO.changeStatusPatientToFalse(patient);
            }catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
    }

    //admin service
    public List<Patient> getAllPatientsWithStatusFalse(){
        List<Patient> patients = new ArrayList<>();
        try {
            return patients = patientDAO.getAllPatientsWithStatusFalse();
        }catch (PersistenceException e){
            e.printStackTrace();
        }
        return null;
    }

    //all patients and patients with status false
    public List<Patient> getAllPatientsA(){
        List<Patient> patients = new ArrayList<>();
        try {
            return patients = patientDAO.getAllPatientsA();
        }catch (PersistenceException e){
            e.printStackTrace();
        }
        return null;
    }

    //delete patient by id
    public void deletePatientByIdA(long id){
        try {
            patientDAO.deletePatientById(id);
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }
}
