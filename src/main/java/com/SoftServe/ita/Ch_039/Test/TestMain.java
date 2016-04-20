package com.SoftServe.ita.Ch_039.Test;

import com.SoftServe.ita.Ch_039.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Entity.AnalysisType;
import com.SoftServe.ita.Ch_039.Entity.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ayasintc on 3/29/2016.
 */

public class TestMain {

    public static void main(String[] args) {

        Patient first = Patient.newPatientBuilder()
                .setBirthDate("04/04/1987")
                .setName("Andrew")
                .setLastName("Yasinskiy")
                .setId(1)
                .setAnalysis(Analysis.newAnalysisBuilder()
                        .setId(1)
                        .setType(AnalysisType.BLOOD)
                        .setDate("03/02/2015 14:50")
                        .setReport("I don't know what is is...")
                        .build())
                .setAnalysis(Analysis.newAnalysisBuilder()
                        .setId(2)
                        .setType(AnalysisType.ALLERGY)
                        .setDate("03/02/2015 14:15")
                        .setReport("Good")
                        .build())
                .build();







        Analysis analysis1 = Analysis.newAnalysisBuilder()
                .setType(AnalysisType.ALLERGY)
                .setDate("03/02/2015 14:15")
                .setReport("bad")
                .build();

        Analysis analysis2 = Analysis.newAnalysisBuilder()
                .setType(AnalysisType.ALLERGY)
                .setDate("03/02/2015 14:14")
                .setReport("234234234")
                .build();

        List<Analysis> an = new ArrayList<>();
        an.add(analysis1);
        an.add(analysis2);




        Patient pat2 = Patient.newPatientBuilder()
                .setLastName("Petia")
                .setName("Vasia")
                .setBirthDate("12/02/1945")
              /*  .setAnalyzes(an)*/
                .build();



       /* analysis2.setPatient(pat2);*/

        //create
       /* EntityManagerFactory em = Persistence.createEntityManagerFactory("HOSPITAL");
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
        EntityManager manager1 = Persistence.createEntityManagerFactory("HOSPITAL").createEntityManager();
        manager1.getTransaction().begin();

        Patient p = manager1.find(Patient.class, 1l);
      /*  p.addAnalysis(analysis2);*/
        List<Analysis> dd = p.getListAnalyzes();

        p.addAnalysis(analysis1);


      /*  manager1.persist(p);*/
       /* manager1.persist(p);*/
        //manager1.remove(a);
        //a.setReport("345345");
        manager1.getTransaction().commit();
        manager1.close();


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

        /*java.util.Date date = new java.util.Date();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        System.out.println (new DateTime(date).toString(formatter));*/

        //from datetime to date


        /*SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = format.parse(dateTime.toString());
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;*/


    }


}


