package com.SoftServe.ita.Ch_039.Test;

import com.SoftServe.ita.Ch_039.Entity.Patient;

import javax.persistence.*;

/**
 * Created by ayasintc on 3/29/2016.
 */

public class TestMain {

    public static void main(String[] args) {

        Patient pat = Patient.newPatientBuilder()
                .setStatus(true)
                .setLastName("Vasia")
                .setName("Vasia")
                .setBirthDate("10/05/1940")
                .build();

        EntityManagerFactory em = Persistence.createEntityManagerFactory("HOSPITAL");
        EntityManager manager = em.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(pat);
        manager.getTransaction().commit();
        manager.close();
        em.close();


    }


}


