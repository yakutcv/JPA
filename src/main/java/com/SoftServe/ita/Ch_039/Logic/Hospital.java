package com.SoftServe.ita.Ch_039.Logic;

import com.SoftServe.ita.Ch_039.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Entity.AnalysisType;
import com.SoftServe.ita.Ch_039.Entity.Patient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Created by ayasintc on 3/29/2016.
 */

@XmlRootElement(name="Hospital")

public class Hospital implements Serializable{

    private Set<Patient> patients = new HashSet<Patient>();

    @XmlElementWrapper(name="Patients")
    @XmlElement(name="Patient")
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
    //add new patient

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    //get by age
    public List<Patient> getByAge(int age) {
        List<Patient> pat = new ArrayList<Patient>(patients);
        Iterator<Patient> i = pat.iterator();
        while (i.hasNext()) {
            if (!(i.next().getAge() == (age))) i.remove();
        }
        return pat;
    }

    //get by name
    public List<Patient> getByName(String name) {
        List<Patient> pat = new ArrayList<Patient>(patients);
        Iterator<Patient> i = pat.iterator();
        while (i.hasNext()) {
            if (!(i.next().getName().equals(name))) i.remove();
        }
        return pat;
    }

    //sort by age
    public List<Patient> sortByAge() {
        List<Patient> pat = new ArrayList<Patient>(patients);
        Collections.sort(pat, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        return pat;
    }

    //sort by lasName
    public List<Patient> sortByLastName() {
        List<Patient> pat = new ArrayList<Patient>(patients);
        Collections.sort(pat, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return pat;
    }

   /* //get by AnalysisType stage 1
    public List<Patient> getByAnalysisType (final AnalysisType type) {
        List<Patient> pat = new ArrayList<Patient>(patients);
        return pat.stream().filter(item ->
               ! item.getList().stream().filter(i -> i.getType().equals(type)).collect(Collectors.toList()).isEmpty())
                .collect(Collectors.toList());
    }*/


    /* //get by AnalysisType stage 2
     public List<Patient> getByAnalisisType(AnalysisType type){
         List<Patient> pat = new ArrayList<>();
         Iterator<Patient> i = patients.iterator();
         while(i.hasNext()) {
             Patient p=i.next();
             List<Analysis> analyzes = p.getList();
             Iterator<Analysis> b = analyzes.iterator();
             while(b.hasNext()) {
                 if(b.next().getType().equals(type)) {
                     pat.add(p);
                     break;
                 }
             }
         }
         return pat;
     }
 */
    //get by AnalysisType stage 3
    public List<Patient> getByAnalisisType(AnalysisType type) {
        List<Patient> pat = new ArrayList<>();
        for (Patient p : patients) {
            List<Analysis> a = p.getListAnalyzes();
            for (Analysis b : a) {
                if (b.getType().equals(type)) {
                    pat.add(p);
                }
            }
        }
        return pat;
    }
    //not passed Analyzes
    public void checkForPassedAnalysis(AnalysisType type) {
        boolean flag = true;
        String s="";
       m:{
        for (Patient p : patients) {
            List<Analysis> a = p.getListAnalyzes();
            for (Analysis b : a) {
                if(b.getType().equals(type)) {
                    s="Analysis " + type + "  passed.";
                    break m;
                }
            }
        }
           s="Analysis " + type + " don't passed any patient";
    }
        System.out.println(s);
}
    public void checkUnpassedAnalysis(){
        String s="";
        for (Patient p : patients) {
            List<Analysis> a = p.getListAnalyzes();
            for (Analysis b : a) {
                for(AnalysisType aType : AnalysisType.values()){
                    if(b.getType().equals(aType))break;
                    s = "Analysis " + b.getType() + " Never passed";
                    break;
                }
                }
            }
        System.out.println(s);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Patient value: patients) {
            builder.append(value);
        }
        String patient = builder.toString();

        return "patients" + "\n" +
                patient;
    }

}
