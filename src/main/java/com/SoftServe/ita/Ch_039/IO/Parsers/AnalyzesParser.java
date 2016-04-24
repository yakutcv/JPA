package com.SoftServe.ita.Ch_039.IO.Parsers;

import com.SoftServe.ita.Ch_039.IO.Exceptions.SelfFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.SoftServe.ita.Ch_039.IO.Exceptions.ExceptionList.DEFAULT;

public class AnalyzesParser {

    private static final String ANALYZES_PATTERN = "\\s([A-Z]{1,})\\s\\((.*)\\)\\s(.*)";
    private static final String INPUT_PATTERN = "([A-Z]{1}[a-z]{1,})\\s([A-Z]{1}[a-z]{1,})\\s\\((.*)\\)\\:\\{(.*)\\}";

    public static List<String> parsePatients(String string) throws SelfFormatException {
        List<String> patients = new ArrayList<>();
        try{
            Pattern p = Pattern.compile(INPUT_PATTERN);
            Matcher m = p.matcher(string);
            m.matches();
            patients.add(m.group(1));
            patients.add(m.group(2));
            patients.add(m.group(3));
            try{
                patients.add(m.group(4));
            }catch (Exception e) {
            }
        }catch (Exception e) {
            throw new SelfFormatException(DEFAULT);
        }
        return patients;
    }

    public static List<String> parseAnalyzes(String string) throws SelfFormatException {
        List<String> analyzes = new ArrayList<>();
        try{
            Pattern p = Pattern.compile(ANALYZES_PATTERN);
            Matcher m = p.matcher(string);
            m.matches();
            analyzes.add(m.group(1));
            analyzes.add(m.group(2));
            analyzes.add(m.group(3));
        }catch (Exception e) {
            throw new SelfFormatException(DEFAULT);
        }
        return analyzes;
    }
}
