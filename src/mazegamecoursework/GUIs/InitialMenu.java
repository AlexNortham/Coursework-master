package mazegamecoursework.GUIs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InitialMenu extends Application {
    public Button AccountButton;
    public Button LoginButton;
    public Button quit;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("InitialMenu.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);


        primaryStage.show();

    }

    public void CreateAccountPressed(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateAccountGUI.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = (Stage) AccountButton.getScene().getWindow();
        stage.close();
    }

    public void LoginPressed(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = (Stage) AccountButton.getScene().getWindow();
        stage.close();
    }

    public void QuitPressed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
