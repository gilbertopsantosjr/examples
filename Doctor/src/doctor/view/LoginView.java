/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.view;

import doctor.control.LoginCtrl;
import doctor.model.TeamModel;
import doctor.util.Functions;
import javax.swing.JOptionPane;

/**
 *
 * @author Keilah
 */
public class LoginView extends javax.swing.JFrame {

    /**
     * Creates new form LoginView
     */
    public LoginView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        regNumberInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Register Number:");

        jLabel2.setText("Password:");

        regNumberInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regNumberInputActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(loginButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(regNumberInput, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(regNumberInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(loginButton)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regNumberInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regNumberInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regNumberInputActionPerformed

    

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        try {
            TeamModel login = new TeamModel();
            login.setPassword(passwordInput.getText());
            login.setRegNumber(regNumberInput.getText());

            LoginCtrl control = new LoginCtrl();
            TeamModel teamMember = control.authenticate(login);
           
            AppointmentListView d = new AppointmentListView(teamMember);
            d.setTitle("Hi, " + teamMember.getName());
            Functions.showScreen(d);
            
            // Destroy this JFrame object
            dispose();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, " System failure ! \n " + e.getMessage());
        }
    }//GEN-LAST:event_loginButtonActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField passwordInput;
    private javax.swing.JTextField regNumberInput;
    // End of variables declaration//GEN-END:variables
}
