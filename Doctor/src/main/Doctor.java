/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import doctor.view.LoginView;

/**
 *
 * @author Keilah
 */
public class Doctor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
    
}
