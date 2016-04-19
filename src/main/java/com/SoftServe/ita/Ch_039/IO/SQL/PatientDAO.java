package com.SoftServe.ita.Ch_039.IO.SQL;

import com.SoftServe.ita.Ch_039.Entity.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by ayasintc on 4/5/2016.
 */
public class PatientDAO {

    SQLConnector connector = new SQLConnector();
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private String TABLE_NAME = "Patients";
    private String CREATE_PATIENT_TABLE = "CREATE TABLE  "+ TABLE_NAME + " (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, Name text NOT NULL, Last_name text NOT NULL, Birth_date text NOT NULL, Status BIT DEFAULT TRUE)";
    private String DELETE_PATIENT_TABLE = "DROP TABLE " + TABLE_NAME;
    private String CREATE_PATIENT = "INSERT INTO " + TABLE_NAME + " (Name, Last_name, Birth_date, Status) VALUES (?, ?, ?, ?)";
    private String DELETE_PATIENT = "DELETE FROM " + TABLE_NAME + " WHERE id =?";
    private String UPDATE_PATIENT = "UPDATE " + TABLE_NAME + " Patients SET Name =?, Last_name=?, Birth_date=?, Status=? WHERE id =?";
    private String UPDATE_PATIENT_STATUS = "UPDATE " + TABLE_NAME + " Patients SET Status =FALSE WHERE id =?";
    private String GET_PATIENT_BY_ID = "SELECT * FROM "+ TABLE_NAME +" WHERE id =?";
    private String GET_ALL_PATIENTS = "SELECT * FROM "+TABLE_NAME+" WHERE Status=TRUE";
    private String GET_ALL_PATIENTS_WITH_STATUS_FALSE = "SELECT * FROM "+TABLE_NAME+" WHERE Status=FALSE";
    private String GET_PATIENT_BY_ID_WITH_ALL_ANALYZES = "SELECT * FROM "+ TABLE_NAME +" WHERE id =? AND Status = TRUE";

    public boolean createPatientTable() {
        try{
            connector.connect();
            statement = connector.getConnection().createStatement();
            statement.executeUpdate(CREATE_PATIENT_TABLE);
            System.out.println("Patients table created!");
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't create table Patient!");
        }
        catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deletePatientTable() {
        try{
            connector.connect();
            statement = connector.getConnection().createStatement();
            statement.executeUpdate(DELETE_PATIENT_TABLE);
            System.out.println("Patients table delete!");
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Table " +  TABLE_NAME + " don't found!");
        }
        catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean addPatient(Patient patient){
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(CREATE_PATIENT);
            preparedStatement.setString(1,patient.getName());
            preparedStatement.setString(2,patient.getLastName());
            preparedStatement.setString(3,patient.getBirthDateInString());
            preparedStatement.setBoolean(4, patient.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("Patients " + patient.getFullName() + " has been added into table " + TABLE_NAME);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't add Patient into table!");
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deletePatientById(long id) {
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(DELETE_PATIENT);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Patient with id " + id + " was deleted!");
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't find patient id: " + id);
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updatePatient (Patient patient){
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(UPDATE_PATIENT);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3,patient.getBirthDateInString());
            preparedStatement.setBoolean(4, patient.getStatus());
            preparedStatement.setLong(5, patient.getId());
            preparedStatement.executeUpdate();
            System.out.println("Patient with id " + patient.getFullName() + " was updateded!");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't update patient " + patient);
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean addListPatients(List<Patient> patients) {
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(CREATE_PATIENT);
            for(Patient p : patients) {
                preparedStatement.setString(1, p.getName());
                preparedStatement.setString(2, p.getLastName());
                preparedStatement.setString(3, p.getBirthDateInString());
                preparedStatement.setBoolean(4, p.getStatus());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("List patient was add!");
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't add List Patients to table " + TABLE_NAME);
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Patient getPatientById(long id) {
        Patient patient = null;
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(GET_PATIENT_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                patient = Patient.newPatientBuilder()
                        .setId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setLastName(resultSet.getString(3))
                        .setBirthDate(resultSet.getString(4))
                        .setStatus(resultSet.getBoolean(5))
                        .build();
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't find patient with id: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        }
        return patient;
    }

    public synchronized List<Patient> getAllPatients() throws SQLException{
        List<Patient> patients = new ArrayList<>();
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(GET_ALL_PATIENTS);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                patients.add(new Patient().newPatientBuilder()
                        .setId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setLastName(resultSet.getString(3))
                        .setBirthDate(resultSet.getString(4))
                        .setStatus(resultSet.getBoolean(5))
                        .build());
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't read patients from table " + TABLE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        }
        Collections.sort(patients);
        return patients;
    }

    public List<Patient> getAllPatientsWithStatusFalse() {
        List<Patient> patients = new ArrayList<>();
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(GET_ALL_PATIENTS_WITH_STATUS_FALSE);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                patients.add(new Patient().newPatientBuilder()
                        .setId(resultSet.getInt(1))
                        .setName(resultSet.getString(2))
                        .setLastName(resultSet.getString(3))
                        .setBirthDate(resultSet.getString(4))
                        .setStatus(resultSet.getBoolean(5))
                        .build());
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't read patients from table " + TABLE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        }
        Collections.sort(patients);
        return patients;
    }

    public boolean changeStatusPatientToFalse(Patient patient) {
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(UPDATE_PATIENT_STATUS);
            preparedStatement.setLong(1, patient.getId());
            preparedStatement.executeUpdate();
            System.out.println("Patients status with name " + patient.getFullName() + " was changed!");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't update patient status " + patient);
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public Patient getPatientByIdWithAllAnalyzes(long id) {
       Patient patient = null;
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(GET_PATIENT_BY_ID_WITH_ALL_ANALYZES);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                patient = Patient.newPatientBuilder()
                        .setId(resultSet.getLong(1))
                        .setName(resultSet.getString(2))
                        .setLastName(resultSet.getString(3))
                        .setBirthDate(resultSet.getString(4))
                        .setStatus(resultSet.getBoolean(5))
                        .setAnalyzes(new ArrayList<>(new AnalyzesDAO().getAllAnalyzesByPatientId(resultSet.getLong(1))))
                        .build();
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't find patient with id" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        }
        return patient;
    }







}
