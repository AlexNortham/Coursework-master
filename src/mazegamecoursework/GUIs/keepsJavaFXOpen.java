package mazegamecoursework.GUIs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class keepsJavaFXOpen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("keepsJavaFXOpen.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        Stage stage = new Stage();
        startActualProgram(stage);




    }

    private void startActualProgram(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("InitialMenu.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }
}
