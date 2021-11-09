package mazegamecoursework.GUIs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mazegamecoursework.Objects.EmailValidation;
import mazegamecoursework.Objects.PasswordHasher;
import mazegamecoursework.SQLClass;


import javax.swing.*;
import java.io.IOException;

public class CreateAccountGUI extends Application {
    public TextField EnterEmail;
    public TextField EnterPassword;
    public TextField EnterUsername;
    public TextField CodeArea;
    public Label EmailLabel;
    public Label PasswordLabel;
    public Label UsernameLabel;
    public Label CodeLabel;
    public Button SendVerCode;
    public Button Confirm;
    public Label ErrorLabel;
    public Button Back;

    private String code;
    private String email;
    private String password;
    private String username;



    public void SendVerCodePressed(ActionEvent actionEvent) {
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
                    EmailLabel.setVisible(false);
                    PasswordLabel.setVisible(false);
                    UsernameLabel.setVisible(false);
                    SendVerCode.setText("Send Another Code");
                    CodeArea.setVisible(true);
                    Confirm.setVisible(true);
                    CodeLabel.setVisible(true);

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
    }

    public void ConfirmPressed(ActionEvent actionEvent) {
        String userCode = CodeArea.getText();
        if (userCode.equals(code)) {
            PasswordHasher passhash = new PasswordHasher();
            String hashedPassword = passhash.HashString(password);
            if (!hashedPassword.equals("")) {
                String command = "INSERT INTO Accounts VALUES ('" + username + "', '" + hashedPassword + "', '" + email + "')";
                SQLClass.insert(SQLClass.getConnection(), command);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }catch(Exception e){
                    e.printStackTrace();
                }
                Stage stage = (Stage) EnterEmail.getScene().getWindow();
                stage.close();
            } else {
                JFrame panel = new JFrame();
                JOptionPane.showMessageDialog(panel, "An error occured while storing your details, you have been returned to the main menu");
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("InitialMenu.fxml"));

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }catch(Exception e){
                    e.printStackTrace();
                }
                Stage stage = (Stage) EnterEmail.getScene().getWindow();
                stage.close();
            }

        } else {
            ErrorLabel.setText("Incorrect Code");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CreateAccountGUI.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();




    }



    public void BackPressed(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InitialMenu.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage oldStage = (Stage) EnterEmail.getScene().getWindow();
        oldStage.close();
    }
}
