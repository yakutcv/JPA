package com.SoftServe.ita.Ch_039.Test;

import com.SoftServe.ita.Ch_039.Model.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Model.Entity.AnalysisType;
import com.SoftServe.ita.Ch_039.Model.Entity.Patient;
import com.SoftServe.ita.Ch_039.Model.DAO.AnalyzesDAO;
import com.SoftServe.ita.Ch_039.Model.DAO.PatientDAO;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        Patient first = Patient.newPatientBuilder()
                .setBirthDate("04/04/1987")
                .setName("Andrew")
                .setLastName("Yasinskiy")
                .build();

        Patient second = Patient.newPatientBuilder()
                .setBirthDate("04/04/1987")
                .setName("Jora")
                .setLastName("Jora")
                .build();
        Patient third = Patient.newPatientBuilder()
                .setBirthDate("04/04/1987")
                .setName("Andrew")
                .setLastName("Andrew")
                .setStatus(false)
                .build();

        Patient pat2 = Patient.newPatientBuilder()
                .setLastName("Jora")
                .setName("Vasia")
                .setBirthDate("12/02/1945")
              /*  .setAnalyzes(an)*/
                .build();

        Analysis analysis1 = Analysis.newAnalysisBuilder()
                .setType(AnalysisType.BIOPSY)
                .setDate("03/02/2015 14:15")
                .setReport("sdfsdfsdgxvbcvbcvb")
                .build();

        Analysis analysis2 = Analysis.newAnalysisBuilder()
                .setType(AnalysisType.BIOPSY)
                .setDate("03/02/2015 14:14")
                .setReport("234234234")
                .build();


        Analysis analysis3 = Analysis.newAnalysisBuilder()
                .setType(AnalysisType.ALLERGY_2)
                .setDate("03/02/2015 14:14")
                .setReport("bbbbbbbbbb")
                .build();

        List<Analysis> an = new ArrayList<>();
        an.add(analysis1);
        an.add(analysis2);


        PatientDAO patientDAO = new PatientDAO();
        AnalyzesDAO analyzesDAO = new AnalyzesDAO();


        System.out.println(patientDAO.getAllPatientsWithStatusFalse());









       /* analysis2.setPatient(pat2);*/

        //create
        /*EntityManagerFactory em = Persistence.createEntityManagerFactory("HOSPITAL");
        EntityManager manager = em.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(pat2);
        manager.getTransaction().commit();
        manager.close();
        em.close();*/

        //add method 2
        /*EntityManager manager1 = Persistence.createEntityManagerFactory("HOSPITAL").createEntityManager();
        manager1.getTransaction().begin();
        manager1.persist(pat2);
        manager1.getTransaction().commit();
        manager1.close();
        System.out.println(pat2);*/

        //delete
       /* EntityManagerFactory em = Persistence.createEntityManagerFactory("HOSPITAL");
        EntityManager manager = em.createEntityManager();
        manager.getTransaction().begin();
        Patient patient = manager.find(Patient.class, (long)1);
        System.out.println(patient);
        manager.remove(patient);
        manager.getTransaction().commit();
        manager.close();
        em.close();*/

        //read
        /*EntityManager manager1 = Persistence.createEntityManagerFactory("HOSPITAL").createEntityManager();
        manager1.getTransaction().begin();
        Patient p = manager1.find(Patient.class, 1l);
        p.addAnalysis(analysis2);
        //manager1.remove(a);
        //a.setReport("345345");
        manager1.getTransaction().commit();
        manager1.close();*/


       /* EntityManager manager1 = Persistence.createEntityManagerFactory("HOSPITAL").createEntityManager();
        manager1.getTransaction().begin();
        Patient patient = manager1.find(Patient.class, 1l);
        patient.addAnalysis(analysis2);
        manager1.persist(patient);
        manager1.getTransaction().commit();
        manager1.close();*/


        //update
      /*  EntityManager man = Persistence.createEntityManagerFactory("HOSPITAL").createEntityManager();
        man.getTransaction().begin();
        patient.setListAnalyzes(an);
        man.merge(patient);
        System.out.println(patient);
        man.getTransaction().commit();
        man.close();*/

    }


}


