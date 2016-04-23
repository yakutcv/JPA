package com.SoftServe.ita.Ch_039.IO;

import com.SoftServe.ita.Ch_039.IO.Parsers.AnalyzesParser;
import com.SoftServe.ita.Ch_039.Model.Logic.Hospital;
import com.SoftServe.ita.Ch_039.Model.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Model.Entity.AnalysisType;
import com.SoftServe.ita.Ch_039.Model.Entity.Patient;
import com.SoftServe.ita.Ch_039.IO.Exceptions.SelfFormatException;
import com.SoftServe.ita.Ch_039.IO.Interfaces.IO;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.SoftServe.ita.Ch_039.IO.Validators.SelfFormatValidator.validPatient;

public class SelfFormatIO implements IO {

    private static DateTimeFormatter format = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter format1 = DateTimeFormat.forPattern("dd/MM/yyyy");


    private static final String ANALYZES_PATTERN = "\\s([A-Z]{1,})\\s\\((.*)\\)\\s(.*)";

    @Override
    public void writeHospital(Hospital hospital, String file) throws IOException {
        FileWriter writer = new FileWriter("src\\main\\java\\SoftServe.Task_1\\output\\" + file);
        try {
            writer.write(covertToString(hospital).toString());
            writer.flush();
            writer.close();
            System.out.println("Txt file created!");
        } catch (IOException e) {
            System.out.println("Failed to record file!" + e);
        }
    }
    @Override
    public Hospital readHospital(String file) throws JAXBException, FileNotFoundException, IOException, ClassNotFoundException, SelfFormatException {
        Hospital hospital = new Hospital();
        List<String> tmpHospital = new ArrayList<>();
        long id = 1;
        String st;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\SoftServe\\Task_1\\output\\" + file))) {
            while ((st = br.readLine()) != null) {
                validPatient(AnalyzesParser.parsePatients(st));
                tmpHospital.add(st);
            }
        } catch (IOException e) {
            System.out.println("Invalid format input file!" + e);

        }


        for(String s : tmpHospital) {
            List<String> patients = AnalyzesParser.parsePatients(s);
            List<Analysis> tmpListOfAnalyzes = new ArrayList<>();
            String tmpAnalyzes[] = patients.get(3).split(",");

            for (int i = 0; i<tmpAnalyzes.length; i++) {
                if(!tmpAnalyzes[i].isEmpty()) {
                    Pattern pat = Pattern.compile(ANALYZES_PATTERN);
                    Matcher mat = pat.matcher(tmpAnalyzes[i]);
                    mat.matches();
                    Analysis tmpAnalisis = Analysis.newAnalysisBuilder()
                            .setType(AnalysisType.valueOf(mat.group(1)))
                            .setId(i+1)
                            .setDate(mat.group(2))
                            .setReport(mat.group(3))
                            .build();
                    tmpListOfAnalyzes.add(tmpAnalisis);
                }
            }

            Patient newPatient = Patient.newPatientBuilder()
                .setBirthDate(patients.get(2))
                .setAnalyzes(tmpListOfAnalyzes)
                .setName(patients.get(0))
                .setLastName(patients.get(1))
                .setId(id)
                .build();
            id++;
             hospital.addPatient(newPatient);
    }
        return hospital;
    }


    //method to convert output Hospital to correct String format
    private StringBuilder covertToString(Hospital value) {
        StringBuilder newHospital = new StringBuilder();
        String formate = "";
        Set<Patient> patients = value.getPatients();
        for (Patient p : patients) {
            newHospital.append(p.getName() + " " + p.getLastName() + " (" + p.getBirthDate().toString(format1) + "):{");
            for (Analysis analysis : p.getListAnalyzes()) {
                /*if (!(p.getList().get(p.getList().size() - 1).equals(analysis))) {}*/
                    newHospital.append(" " + analysis.getType() + " (" + analysis.getDate().toString(format) + ") " + analysis.getReport() + ",");
            }
            newHospital.append("}");
            newHospital.append("\n");
        }
        return newHospital;
    }

}


/*
for(String s : tmpList) {

        String tmpPatients [] = s.split("(\\):)");

        String tmpPatient [] = tmpPatients[0].split("(\\()");

        String tmpFullname [] = tmpPatient[0].split("\\s");

        String tmpAnalyzes []  = tmpPatients[1].replaceAll("\\{|,\\s\\}", "").split("\\,");

        Analysis analysis = new Analysis();

        List<Analysis> tmpListOfAnalizes = new ArrayList<>();

        for (int i = 0; i <tmpAnalyzes.length ; i++) {

        String tmp = tmpAnalyzes[i].replaceAll("^(\\s)", "");

        String tmpAnalizesFiels [] = tmp.split("(\\s\\()|(\\)\\s)");

        analysis = Analysis.newAnalysisBuilder()
        .setId(i)
        .setType(AnalysisType.valueOf(tmpAnalizesFiels[0]))
        .setDate(tmpAnalizesFiels[1])
        .setReport(tmpAnalizesFiels[2])
        .build();

        tmpListOfAnalizes.add(analysis);
        }
        Patient newPatient = Patient.newPatientBuilder()
        .setBirthDate(tmpPatient[1])
        .setAnalyzes(tmpListOfAnalizes)
        .setName(tmpFullname[0])
        .setLastName(tmpFullname[1])
        .setId(id)
        .build();
        id++;
        hospital.addPatient(newPatient);
        }

        return hospital;*/
