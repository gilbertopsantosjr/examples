/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.util;

import doctor.view.LoginView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;

/**
 *
 * @author Keilah
 */
public class Functions {

    public static void checkIsNull(String field, String arg) {
        if (arg == null || arg.equals("")) {
            throw new RuntimeException(field + " can't be empty !");
        }
    }

    public static void checkIsNull(String field, Object arg) {
        if (arg == null) {
            throw new RuntimeException(field + " can't be empty !");
        }
    }

    public static void showScreen(JFrame screen) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                screen.setVisible(Boolean.TRUE);
                screen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        });
    }
    
    public static void logout(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView screen = new LoginView();
                screen.setVisible(Boolean.TRUE);
                screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    public static int calcAge(Date dataNascInput) {
        int age = 0;
        try {
            Calendar dateOfBirth = new GregorianCalendar();
            dateOfBirth.setTime(dataNascInput);
            // Cria um objeto calendar com a data atual
            Calendar today = Calendar.getInstance();
            // Obtém a idade baseado no ano
            age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
            dateOfBirth.add(Calendar.YEAR, age);
            if (today.before(dateOfBirth)) {
                age--;
            }
        } catch (Exception e) {

        }
        return age;

    }

}
