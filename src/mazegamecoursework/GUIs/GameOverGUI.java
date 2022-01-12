package mazegamecoursework.GUIs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mazegamecoursework.Objects.MazeGenerator;

import javax.swing.*;
import java.io.IOException;

public class GameOverGUI extends Application {



    public Button showResults;





    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GameOverGUI.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();

    }



    public void showResultsPressed(ActionEvent actionEvent) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("ResultsScreenGui.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = (Stage) showResults.getScene().getWindow();
        stage.close();
    }
}
