/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.control;

import doctor.model.AppointmentModel;
import doctor.model.TeamModel;
import doctor.model.PatientModel;
import doctor.util.FactoryConnection;
import doctor.util.Functions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Keilah
 */
public class AppointmentCtrl {
    
    public List<AppointmentModel> getAppointments(TeamModel login) {
        // varibale contains inside the list all object that was required
        List<AppointmentModel> list = new ArrayList<>();
        //declare connection with database
        Connection conn = null;
        try {
            Functions.checkIsNull("User", login.getId() );
            // open connection 
            conn = FactoryConnection.getConnection();
            // statement and resultset 
            String sql =  " SELECT ap.id, ap.datetime, p.name as name_of_patient, p.id as patient_id, "
                    + " p.date_of_birth as date_of_birth_patient, t.id as id_of_team, t.name as name_of_team "
                    + " FROM appointment as ap "
                    + " inner join patient p on ap.patient_id = p.id "
                    + " inner join team t on ap.team_id = t.id ";
                    
            PreparedStatement p = conn.prepareStatement(sql);
            
            // get the result from above sql
            ResultSet rs = p.executeQuery();
            
            while (rs.next()) {
                AppointmentModel appointmentModel = new AppointmentModel();
                appointmentModel.setId( rs.getInt("ap.id") );
                appointmentModel.setDateOfAppintment( new java.util.Date( rs.getDate("ap.datetime").getTime() ) );
                
                PatientModel patient = new PatientModel();
                patient.setId(rs.getInt("patient_id"));
                patient.setName( rs.getString("name_of_patient") );
                patient.setDateOfBirth( rs.getDate("date_of_birth_patient") );
                
                TeamModel teamModel = new TeamModel();
                teamModel.setId( rs.getInt("id_of_team") );
                teamModel.setName( rs.getString("name_of_team") );
                
                appointmentModel.setPatient(patient);
                appointmentModel.setDoctor(teamModel);
                
                // put it on the list
                list.add(appointmentModel);
            }
        
        } catch (RuntimeException ex) {
            throw ex;
            
        } catch (SQLException ex) {

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
        } finally {
            // after execute the sql steatment should be finalize the connection with database
            try {
                if (conn != null) {
                    conn.close();
                }
                System.out.println("... Finalize the connect to database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public AppointmentModel getByPatientId(int patientId){
        AppointmentModel appointmentModel = null;
        //declare connection with database
        Connection conn = null;
        try {
            conn = FactoryConnection.getConnection();
            // statement and resultset 
            String sql = " SELECT a.*,  p.*, t.id as team_id, t.name as team_name FROM appointment a "
                    + " INNER JOIN team t on a.team_id = t.id  "
                    + " INNER JOIN patient p on a.patient_id = p.id  "
                    + " WHERE a.patient_id = ? ";

            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1, patientId);

            // get the result from above sql
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                appointmentModel = new AppointmentModel();
                appointmentModel.setId( rs.getInt("a.id") );
                appointmentModel.setDateOfAppintment(rs.getDate("a.datetime"));
                appointmentModel.setNotes(rs.getString("a.notes"));
                appointmentModel.setPrescription( rs.getString("a.prescription") );
                
                TeamModel loginModel = new TeamModel();
                loginModel.setId( rs.getInt("team_id") );
                loginModel.setName( rs.getString("team_name") );
                
                appointmentModel.setDoctor(loginModel);
                
                PatientModel patientModel = new PatientModel();
                patientModel.setId(rs.getInt("p.id"));
                patientModel.setDateOfBirth(rs.getDate("p.date_of_birth"));
                patientModel.setName(rs.getString("p.name"));
                patientModel.setAddress(rs.getString("p.address"));
                patientModel.setContactNumber(rs.getString("p.contact_number"));
                
                appointmentModel.setPatient(patientModel);
            }
            
        } catch (RuntimeException e) {
             throw e;
             
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            // after execute the sql steatment should be finalize the connection with database
            try {
                if (conn != null) {
                    conn.close();
                }
                System.out.println("... Finalize the connect to database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return appointmentModel;
    }
    
    
     public AppointmentModel save(AppointmentModel appointmentModel) {
        if (appointmentModel.getId() == null) {
            return insert(appointmentModel);
        } else {
            return update(appointmentModel);
        }
    }

    public AppointmentModel getbyId(int id){
        AppointmentModel appointmentModel = null;
        //declare connection with database
        Connection conn = null;
        try {
            conn = FactoryConnection.getConnection();
            // statement and resultset 
            String sql = " SELECT * FROM appointment a"
                    + " INNER JOIN team t on a.team_id = t.id  "
                    + " INNER JOIN patient p on a.patient_id = p.id  "
                    + " WHERE a.id = ? ";
            
            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1, id);

            // get the result from above sql
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                appointmentModel = new AppointmentModel();
                appointmentModel.setId( rs.getInt("a.id") );
                appointmentModel.setDateOfAppintment(rs.getDate("a.datetime"));
                appointmentModel.setNotes(rs.getString("a.notes"));
                appointmentModel.setPrescription( rs.getString("a.prescription") );
                
                TeamModel loginModel = new TeamModel();
                loginModel.setId( rs.getInt("t.id") );
                loginModel.setName( rs.getString("t.name") );
                
                appointmentModel.setDoctor(loginModel);
                
                PatientModel patientModel = new PatientModel();
                patientModel.setId(rs.getInt("p.id"));
                patientModel.setDateOfBirth(rs.getDate("p.date_of_birth"));
                patientModel.setName(rs.getString("p.name"));
                patientModel.setAddress(rs.getString("p.address"));
                patientModel.setContactNumber(rs.getString("p.contact_number"));
                
                appointmentModel.setPatient(patientModel);
            }
            
         } catch (RuntimeException e) {
             throw e;
             
        } catch (Exception e) {
            e.printStackTrace();
       
        } finally {
            // after execute the sql steatment should be finalize the connection with database
            try {
                if (conn != null) {
                    conn.close();
                }
                System.out.println("... Finalize the connect to database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return appointmentModel;
    } 
     
    private AppointmentModel insert(AppointmentModel appointmentModel) {
        //declare connection with database
        Connection conn = null;
        try {
            PatientCtrl patientCtrl = new PatientCtrl();
            PatientModel patient = patientCtrl.save(appointmentModel.getPatient());
            
            conn = FactoryConnection.getConnection();
            // statement and resultset 
            String sql = "INSERT INTO appointment (datetime, team_id, patient_id, notes, prescription) VALUES ( ?, ?, ?, ?, ?);";
            
            PreparedStatement p = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            p.setDate(1, new java.sql.Date( appointmentModel.getDateOfAppintment().getTime() ) );
            p.setInt(2, appointmentModel.getDoctor().getId() );
            p.setInt(3, patient.getId() );
            p.setString(4, appointmentModel.getNotes() );
            p.setString(5, appointmentModel.getPrescription() );
            
            p.executeUpdate();
            
            int lastInsertedId = 0;
            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()){
                lastInsertedId = rs.getInt(1);
            }
            return getbyId(lastInsertedId);
            
         } catch (RuntimeException e) {
             throw e;
             
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally{
             try {
                if (conn != null) {
                    conn.close();
                }
                System.out.println("... Finalize the connect to database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return appointmentModel;
    }
    
    
    private AppointmentModel update(AppointmentModel appointmentModel) {
        //declare connection with database
        Connection conn = null;
        try {
            
            PatientCtrl patientCtrl = new PatientCtrl();
            PatientModel patient = patientCtrl.save(appointmentModel.getPatient());
            
            conn = FactoryConnection.getConnection();
            // statement and resultset 
            String sql = "UPDATE appointment SET datetime = ?, team_id =?, patient_id = ?, notes = ?, prescription = ? WHERE id = ?;";
            
            PreparedStatement p = conn.prepareStatement(sql);
            p.setDate(1, new java.sql.Date( appointmentModel.getDateOfAppintment().getTime() ) );
            p.setInt(2, appointmentModel.getDoctor().getId() );
            p.setInt(3, appointmentModel.getPatient().getId() );
            p.setString(4, appointmentModel.getNotes() );
            p.setString(5, appointmentModel.getPrescription() );
            p.setInt(6, appointmentModel.getId() );
            
            p.executeUpdate();
            
            return getbyId(appointmentModel.getId());
            
         } catch (RuntimeException e) {
             throw e;
             
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally{
             try {
                if (conn != null) {
                    conn.close();
                }
                System.out.println("... Finalize the connect to database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return appointmentModel;
    }
    
    
}
