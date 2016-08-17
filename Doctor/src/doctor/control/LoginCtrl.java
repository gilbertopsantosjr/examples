/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.control;

import doctor.model.TeamModel;
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
public class LoginCtrl {

    public List<TeamModel> getAllMembersByProfile(String profile) {
        List<TeamModel> list = new ArrayList<TeamModel>();
        //declare connection with database
        Connection conn = null;
        try {
             // open connection 
            conn = FactoryConnection.getConnection();
            // statement and resultset 
            String sql =  " SELECT * FROM team WHERE profile = ? ";
                    
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, profile);
            
            // get the result from above sql
            ResultSet rs = p.executeQuery();
            
            while (rs.next()) {
                TeamModel lm = new TeamModel();
                lm.setId(rs.getInt("id"));
                lm.setName(rs.getString("name"));
                list.add(lm);
            }
            
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
        return list;
    }

    public TeamModel authenticate(TeamModel login) {
        TeamModel teamMemberFound = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            
            Functions.checkIsNull("Reg Number", login.getRegNumber());
            Functions.checkIsNull("Password", login.getPassword());
            
            // open connection 
            conn = FactoryConnection.getConnection();
            // statement and resultset tem a ver com o banco e nao com a conexao em si

            PreparedStatement p = conn.prepareStatement("select * from team where "
                    + "reg_number = ? and password = ?");
            p.setString(1, login.getRegNumber());
            p.setString(2, login.getPassword());
            rs = p.executeQuery();

            while (rs.next()) {
                teamMemberFound = new TeamModel();
                teamMemberFound.setId( rs.getInt("id") );
                teamMemberFound.setProfile(rs.getString("profile"));
                teamMemberFound.setName(rs.getString("name"));
            }

            if (teamMemberFound == null) {
                throw new RuntimeException(" user can't be found !");
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
                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
                 System.out.println("... Finalize the connect to database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return teamMemberFound;
    }

}
