package com.SoftServe.ita.Ch_039.IO.Validators;

import com.SoftServe.ita.Ch_039.Entity.AnalysisType;
import com.SoftServe.ita.Ch_039.IO.Exceptions.ExceptionList;
import com.SoftServe.ita.Ch_039.IO.Exceptions.SelfFormatException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelfFormatValidator {

    private static final String NAME_PATTERN = "[A-Z]{1}[a-z]{2,20}";
    private static final String REPORT_PATTERN = ".{1,200}";
    private static final String ANALYZES_PATTERN = "\\s([A-Z,a-z]{1,})\\s\\((.*)\\)\\s(.*)";
    private static final String INPUT_PATTERN = "(([A-Z,a-z]{1,})\\s([A-Z,a-z]{1,})\\s\\(.*\\))\\:\\{(\\w*\\s\\(.*\\)\\s(\\w*\\s*){1,}\\,|\\,){1,}\\s*\\}";

    private static DateTimeFormatter format = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter format2 = DateTimeFormat.forPattern("dd/MM/yyyy");

    public static boolean validPatient(List<String> patients) throws SelfFormatException {
        List<String> tmpPatietns = patients;

        if(!validName(tmpPatietns.get(0))) {
            throw new SelfFormatException(ExceptionList.INVALID_NAME);
        }
        if(!validName(tmpPatietns.get(1))) {
            throw new SelfFormatException(ExceptionList.INVALID_LAST_NAME);
        }
        if(!validBirthDate(tmpPatietns.get(2))) {
            throw new SelfFormatException(ExceptionList.INVALID_BIRTH_DATE);
        }

        //analyzes
        String tmpAnalyzes[] = tmpPatietns.get(3).split(",");
        Pattern pat = Pattern.compile(ANALYZES_PATTERN);
        for(String st : tmpAnalyzes) {
            if(!st.isEmpty()) {
                try{
                    Matcher mat = pat.matcher(st);
                    mat.matches();
                    validAnalysisType(mat.group(1));
                    validAnalyzesDate(mat.group(2));
                }catch (SelfFormatException e) {
                }
            }
        }
        return true;
    }

    public static boolean validReport (String report){
        if(validInputValue(report, REPORT_PATTERN)) {
            return true;
        }else {
            return false;
        }
    }

    public static boolean validBirthDate(String date) {
        try{
            DateTime dateTime = DateTime.parse(date, format2);
        }catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static boolean validAnalyzesDate(String date) throws SelfFormatException {
        try{
            DateTime dateTime = DateTime.parse(date, format);
        }catch (Exception e) {
            throw new SelfFormatException(ExceptionList.INVALID_ANALYZES_DATE);
        }
        return true;
    }

    public static boolean validInputValue(String string, String regexp){
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(string);
        return m.matches();
    }

    public static boolean validName(String name) {
        if(validInputValue(name, NAME_PATTERN)) {
            return true;
        }else {
            return false;
        }
    }

    public static boolean validAnalysisType (String string) throws SelfFormatException {
        try{
            AnalysisType.valueOf(string);
            return true;
        }catch (Exception e) {
            throw new SelfFormatException(ExceptionList.INVALID_ANALYZES_TYPE);
        }
    }

    public static String convertDate (String value) {
        String tmp ="";
        if(value.length()==1) {
            tmp = "0"+value;
        }else {
            tmp = value;
        }
            return tmp;
    }

    public static void main(String[] args) {

        String strt = "Andrew Yasinskiy (04/04/1987 14:00):{HORMONES (03/02/2015 14:10) Good Analyzes, ALLERGY (03/02/2015 14:15) Good, }";

        String string = "Petia Petrushkin (04/04/1954):{BLOOD (03/01/2014 14:15) Yeap, ALLERGY (03/01/2016 14:15) Mu-ha-ha, }";
        String string2 = " HORMONES (03/02/2015 14:50) I don't know what is is...";

        String ffff = " I don't know what is is";

        //ALLERGY (02/01/2016 14:40) Good Analysis.Very good!, BLOOD (02/01/2016 14:44) Simple good,}

        String patternsss = "(([A-Z,a-z]{1,})\\s([A-Z,a-z]{1,})\\s\\(.*\\))\\:\\{(\\w*\\s\\(.*\\)\\s(\\w*\\s*){1,}\\,|\\,){1,}\\s*\\}";


        String pat5 = "(([A-Z,a-z]{1,})\\s([A-Z,a-z]{1,})\\s\\(.*\\))\\:\\{(\\w*\\s\\(.*\\)\\s(\\w*\\s*){1,}\\,|\\s){1,}\\s*\\}";

        String pat6 = "([A-Z]{1}[a-z]{1,})\\s([A-Z]{1}[a-z]{1,})\\s\\((.*)\\)\\:\\{(.*)\\}";
        String pat7 = "([A-Z]{1}[a-z]{1,})\\s([A-Z]{1}[a-z]{1,})";




        String analyzes = "\\s([A-Z]{1,})\\s\\((.*)\\)\\s(.*)";

        Pattern p = Pattern.compile(analyzes);
        Matcher m = p.matcher(string2);



        System.out.println(m.matches());
        System.out.println(m.groupCount());
        //System.out.println(m.group(0));



        String ggf = "(\\((\\d*\\/){2,3}\\))";
        String ggfe = "(\\d*\\/)+";


    }
}
