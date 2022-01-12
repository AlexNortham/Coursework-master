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
import mazegamecoursework.Objects.PasswordHasher;
import mazegamecoursework.SQLClass;
import mazegamecoursework.Objects.Settings;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginGUI extends Application {
    public TextField EnterEmail;
    public TextField EnterPassword;
    public Label EmailLabel;
    public Label PasswordLabel;
    public Label Error;
    public Button LoginButton;
    public Button Back;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void LoginPressed(ActionEvent actionEvent) {
        String email = EnterEmail.getText();
        String password = EnterPassword.getText();
        String sqlQuery = ("SELECT * FROM Accounts where EmailAddress = '" + email + "'");
        Connection con = SQLClass.getConnection();
        ResultSet rs = SQLClass.query(con, sqlQuery);
        try {
            while (rs.next()) {
                String databaseEmail = rs.getString("EmailAddress");
                String databasePasswordHashed = rs.getString("Password");
                PasswordHasher passhash = new PasswordHasher();
                String hashedPassword = passhash.HashString(password);
                if(email.equals(databaseEmail) && hashedPassword.equals(databasePasswordHashed)){
                    Settings.setEmail(email);
                    Settings.setName(rs.getString("Username"));

                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("GameStartGUI.fxml"));

                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Stage stage = (Stage) EnterEmail.getScene().getWindow();
                    stage.close();

                }else{

                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        Error.setVisible(true);
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
