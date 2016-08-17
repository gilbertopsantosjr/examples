/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.control;

import doctor.model.PatientModel;
import doctor.util.FactoryConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Keilah
 */
public class PatientCtrl {

    public PatientModel getbyId(int id) {
        PatientModel patientModel = null;
        //declare connection with database
        Connection conn = null;
        try {
            conn = FactoryConnection.getConnection();
            // statement and resultset 
            String sql = " SELECT * FROM patient WHERE id = ? ";

            PreparedStatement p = conn.prepareStatement(sql);
            p.setInt(1, id);

            // get the result from above sql
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                patientModel = new PatientModel();
                patientModel.setId(rs.getInt("id"));
                patientModel.setDateOfBirth(rs.getDate("date_of_birth"));
                patientModel.setName(rs.getString("name"));
                patientModel.setAddress(rs.getString("address"));
                patientModel.setContactNumber(rs.getString("contact_number"));
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
        return patientModel;
    }

    public PatientModel save(PatientModel patient) {
        if (patient.getId() == null) {
            return insert(patient);
        } else {
            return update(patient);
        }
    }

    private PatientModel insert(PatientModel patientParam) {
        PatientModel patient = null;
        //declare connection with database
        Connection conn = null;
        try {
            conn = FactoryConnection.getConnection();
            // statement and resultset 
            String sql = "INSERT INTO patient ( name, address, date_of_birth, contact_number) "
                    + " VALUES (?, ?, ?, ?);";
            
            PreparedStatement p = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            p.setString(1, patientParam.getName());
            p.setString(2, patientParam.getAddress());
            p.setDate(3, new java.sql.Date( patientParam.getDateOfBirth().getTime() ) );
            p.setString(4, patientParam.getContactNumber());
            
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
        return patient;
    }

    private PatientModel update(PatientModel patientParam) {
        PatientModel patient = null;
        //declare connection with database
        Connection conn = null;
        try {
            conn = FactoryConnection.getConnection();
            // statement and resultset 
            String sql = "UPDATE patient SET name =?, address=?, contact_number=?, date_of_birth=? WHERE id = ?;";

            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, patientParam.getName());
            p.setString(2, patientParam.getAddress());
            p.setString(3, patientParam.getContactNumber());
            p.setDate(4, new java.sql.Date( patientParam.getDateOfBirth().getTime() ) );
            p.setInt(5, patientParam.getId());
            
            p.executeUpdate();
            
            return getbyId( patientParam.getId() );
            
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
        return patient;
    }
}
