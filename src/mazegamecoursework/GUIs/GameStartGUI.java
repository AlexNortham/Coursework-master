package mazegamecoursework.GUIs;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mazegamecoursework.Objects.MazeGenerator;
import mazegamecoursework.Objects.Settings;

import java.net.URL;
import java.util.ResourceBundle;

public class GameStartGUI extends Application {
    @FXML
    private Button playgame;
    @FXML
    private Button options;
    @FXML
    private Button signout;
    private String email;
    private double volume;
    private String colour;
    private Settings settings;




    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GameStartGUI.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);

            primaryStage.show();
            Platform.setImplicitExit(false);
        }catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void PlayGameDone(ActionEvent actionEvent) {
        MazeGenerator mz = new MazeGenerator();
        mz.setUpBoard();
        Stage stage = (Stage) playgame.getScene().getWindow();
        stage.close();
    }

    public void OptionsDone(ActionEvent actionEvent) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("OptionsGUI.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = (Stage) options.getScene().getWindow();

    }

    public void SignOutDone(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = (Stage) playgame.getScene().getWindow();
        stage.hide();
    }


}

