package com.SoftServe.ita.Ch_039.JPA.Service;

import com.SoftServe.ita.Ch_039.Entity.Analysis;
import com.SoftServe.ita.Ch_039.Entity.AnalysisType;
import com.SoftServe.ita.Ch_039.Entity.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ayasintc on 4/5/2016.
 */
public class AnalyzesService {

    SQLConnector connector = new SQLConnector();
    private static Statement statement ;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    private EntityManager manager = Persistence.createEntityManagerFactory("HOSPITAL").createEntityManager();
    private String ANALYSIS_TABLE_NAME = "Analysis";



    private String TABLE_NAME = "Analyzes";
    private String CREATE_ANALYZES_TABLE = "CREATE TABLE " + TABLE_NAME + " (id int NOT NULL AUTO_INCREMENT, Analysis_type text, Date text, Report text, Patient_id int, PRIMARY KEY (id), foreign key (Patient_id) REFERENCES patients(id));";
    private String DELETE_ANALYZES_TABLE = "DROP TABLE " + TABLE_NAME;
    private String CREATE_ANALYZES = "INSERT INTO " +  TABLE_NAME +" (Analysis_type, Date, Report, Patient_id) VALUES (?, ?, ?, ?)";
    private String UPDATE_ANALYZES = "UPDATE "+ TABLE_NAME+ " SET Analysis_type=?, Date=?, Report=? WHERE id = ?";
    private String DELETE_ANALYSIS_BY_ID = "DELETE FROM "+ TABLE_NAME + " WHERE id =?";
    private String READ_ANALYZES_BY_ID = "SELECT * FROM "+ TABLE_NAME + " WHERE id =?";
    private String READ_ANALYZES_BY_PATIENT_ID = "SELECT * FROM "+ TABLE_NAME + " WHERE Patient_id =?";
    private String READ_ALL_ANALYZES = "SELECT * FROM " + TABLE_NAME;










    public boolean addAnalysis(Analysis analysis, Patient patient) throws PersistenceException {
        try {
            manager.getTransaction().begin();
            manager.persist(patient);
            manager.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Couldn't add " + analysis +" into table " + ANALYSIS_TABLE_NAME);
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return false;
    }

       /* try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(CREATE_ANALYZES);
            preparedStatement.setString(1,analysis.getType().toString());
            preparedStatement.setString(2,analysis.getDateInString());
            preparedStatement.setString(3, analysis.getReport());
            preparedStatement.setLong(4, patient.getId());
            preparedStatement.executeUpdate();
            System.out.println("Analysis " + analysis + "has been added into table " + TABLE_NAME);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't add " + analysis +" into table " + TABLE_NAME);
*/


    public boolean updateAnalysis (Analysis analysis){
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(UPDATE_ANALYZES);
            preparedStatement.setString(1,analysis.getType().toString());
            preparedStatement.setString(2,analysis.getDateInString());
            preparedStatement.setString(3,analysis.getReport());
            preparedStatement.setLong(4, analysis.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't update analysis " + analysis);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
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

    public boolean deleteAnalysisById(long id) {
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(DELETE_ANALYSIS_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
            System.out.println("Analysis with id " + id + " was deleted!");
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't find analysis with : " + id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
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

    public boolean addListAnalysis(List<Analysis> analysisList, Patient patient) {
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(CREATE_ANALYZES);
            for(Analysis a : analysisList) {
                preparedStatement.setString(1, a.getType().toString());
                preparedStatement.setString(2, a.getDateInString());
                preparedStatement.setString(3, a.getReport());
                preparedStatement.setLong(4, patient.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("List Analyzes for " + patient.getName() + " was add to table Patients");
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't add List Analyzes for " + patient.getName() + " to table Patients");
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

    public Analysis getAnalyzesById(long id) {
        Analysis analysis = null;
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(READ_ANALYZES_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                analysis = Analysis.newAnalysisBuilder()
                        .setId(resultSet.getLong(1))
                        .setType(AnalysisType.valueOf(resultSet.getString(2)))
                        .setDate(resultSet.getString(3))
                        .setReport(resultSet.getString(4))
                        .build();
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't find analyzes with id: " + id);
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return analysis;
    }

    public List<Analysis> getAllAnalyzes() {
        List<Analysis> analyzes = new ArrayList<>();
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(READ_ALL_ANALYZES);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                analyzes.add(new Analysis().newAnalysisBuilder()
                        .setId(resultSet.getLong(1))
                        .setType(AnalysisType.valueOf(resultSet.getString(2)))
                        .setDate(resultSet.getString(3))
                        .setReport(resultSet.getString(4))
                        .build());
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't read analyzes from table " + TABLE_NAME);
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(analyzes);
        return analyzes;
    }


    public List<Analysis> getAllAnalyzesByPatient(Patient patient) {
        List<Analysis> analyzes = new ArrayList<>();
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(READ_ANALYZES_BY_PATIENT_ID);
            preparedStatement.setLong(1, patient.getId());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                analyzes.add(new Analysis().newAnalysisBuilder()
                        .setId(resultSet.getLong(1))
                        .setType(AnalysisType.valueOf(resultSet.getString(2)))
                        .setDate(resultSet.getString(3))
                        .setReport(resultSet.getString(4))
                        .build());
            }
            System.out.println("List Analyzes was read and add to patient " + patient.getName());
        }catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            System.out.println("Couldn't read and add List Analyzes to " + patient.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(analyzes);
        return analyzes;
    }

    public List<Analysis> getAllAnalyzesByPatientId(long id) {
        List<Analysis> analyzes = new ArrayList<>();
        try{
            connector.connect();
            preparedStatement = connector.getConnection().prepareStatement(READ_ANALYZES_BY_PATIENT_ID);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                analyzes.add(new Analysis().newAnalysisBuilder()
                        .setId(resultSet.getLong(1))
                        .setType(AnalysisType.valueOf(resultSet.getString(2)))
                        .setDate(resultSet.getString(3))
                        .setReport(resultSet.getString(4))
                        .build());
            }
            System.out.println("List Analyzes was read and add to patient with " + id);
        }catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            System.out.println("Couldn't read and add List Analyzes to patient with id" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(analyzes);
        return analyzes;
    }



}
