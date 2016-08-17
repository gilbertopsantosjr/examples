/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Keilah
 */
public class FactoryConnection {
    
    // JDBC driver name and database URL
   private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   private static final String DB_URL = "jdbc:mysql://localhost/clinic";

   //  Database credentials
   private static final String USER = "root";
   private  static final String PASS = "1234";
    
    public static Connection getConnection(){
        Connection conn = null;
    	try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
      
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
      
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	   
    	} catch ( Exception  e ) {
    	    // handle any errors
    	    System.out.println("Exception: " + e.getMessage()); 
    	} 

        return conn;
    }
        public static void main(String args[]) {
            Connection connection = FactoryConnection.getConnection();
            if(connection != null){
                System.out.println("Connected");
            }
    }
}

