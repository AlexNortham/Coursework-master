/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegamecoursework;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import mazegamecoursework.GUIs.keepsJavaFXOpen;


public class MazeGameCoursework extends Application {

    //String dir = getClass().getResource("GUIs\\InitialMenu.fxml");
    
    public static void main(String[] args) {


        Platform.runLater(() -> {
            keepsJavaFXOpen keepsJavaFXOpen = new keepsJavaFXOpen();
            Stage stage = new Stage();
            try {
                keepsJavaFXOpen.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
