package mazegamecoursework.oldguis;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mazegamecoursework.Objects.EmailValidation;
import mazegamecoursework.Objects.PasswordHasher;
import mazegamecoursework.SQLClass;

public class CreateAccount extends javax.swing.JFrame {

    private String code;
    private String email;
    private String password;
    private String username;

    public CreateAccount() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EnterEmail = new javax.swing.JTextField();
        EnterPassword = new javax.swing.JTextField();
        SendVerCode = new javax.swing.JButton();
        ErrorLabel = new javax.swing.JLabel();
        CodeArea = new javax.swing.JTextField();
        Confirm = new javax.swing.JButton();
        EnterUsername = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        EnterEmail.setText("Email Address");
        EnterEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnterEmailActionPerformed(evt);
            }
        });

        EnterPassword.setText("Password");

        SendVerCode.setText("Send Verification Code");
        SendVerCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendVerCodeActionPerformed(evt);
            }
        });

        CodeArea.setText("Enter Code Here");
        CodeArea.setVisible(false);

        Confirm.setText("Confirm");
        Confirm.setVisible(false);
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        EnterUsername.setText("Username");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EnterUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(ErrorLabel)
                    .addComponent(EnterPassword)
                    .addComponent(EnterEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(CodeArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SendVerCode, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(EnterEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SendVerCode))
                .addGap(25, 25, 25)
                .addComponent(EnterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CodeArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Confirm))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnterEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnterEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EnterEmailActionPerformed

    private void SendVerCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendVerCodeActionPerformed
        email = EnterEmail.getText();
        password = EnterPassword.getText();
        username = EnterUsername.getText();
        if (!(username.equals("")) && !(password.equals(""))) {
            EmailValidation ev = new EmailValidation(email);
            ev.getNewCode();
            code = ev.getCode();
            boolean matches = ev.validateAddress();
            if (matches = true) {
                boolean sent = ev.sendEmail();
                if (sent != true) {
                    ErrorLabel.setText("Email couldn't be sent, please check your email and try again");
                    EnterEmail.setText("");
                    EnterPassword.setText("");
                } else {
                    EnterEmail.setVisible(false);
                    EnterPassword.setVisible(false);
                    EnterUsername.setVisible(false);
                    SendVerCode.setText("Send Another Code");
                    CodeArea.setVisible(true);
                    Confirm.setVisible(true);

                }
            } else {
                ErrorLabel.setText("Invalid email, please check and try again");
                EnterEmail.setText("");
                EnterPassword.setText("");
                EnterUsername.setText("");
            }
        } else {
            ErrorLabel.setText("Invalid username or password, please check and try again");
            EnterEmail.setText("");
            EnterPassword.setText("");
            EnterUsername.setText("");
        }
    }//GEN-LAST:event_SendVerCodeActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        String userCode = CodeArea.getText();
        if (userCode.equals(code)) {
            PasswordHasher passhash = new PasswordHasher();
            String hashedPassword = passhash.HashString(password);
            if (!hashedPassword.equals("")) {
                String command = "INSERT INTO Accounts VALUES ('" + username + "', '" + hashedPassword + "', '" + email + "')";
                SQLClass.insert(SQLClass.getConnection(), command);
                LoginGUI lg = new LoginGUI();
                lg.setVisible(true);
                this.dispose();
            } else {
                JFrame panel = new JFrame();
                JOptionPane.showMessageDialog(panel, "An error occured while storing your details, you have been returned to the main menu");
                InitialMenu im = new InitialMenu();
                im.setVisible(true);
                this.dispose();
            }

        } else {
            ErrorLabel.setText("Incorrect Code");
        }
    }//GEN-LAST:event_ConfirmActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CodeArea;
    private javax.swing.JButton Confirm;
    private javax.swing.JTextField EnterEmail;
    private javax.swing.JTextField EnterPassword;
    private javax.swing.JTextField EnterUsername;
    private javax.swing.JLabel ErrorLabel;
    private javax.swing.JButton SendVerCode;
    // End of variables declaration//GEN-END:variables
}
