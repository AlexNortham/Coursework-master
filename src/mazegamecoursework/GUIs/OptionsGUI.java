package mazegamecoursework.GUIs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mazegamecoursework.Objects.Settings;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsGUI extends Application implements Initializable {

    @FXML
    private Slider volumeslider;
    @FXML
    private Label volumelabel;
    @FXML
    private ComboBox<String> colourpicker;
    @FXML
    private Label colourlabel;
    @FXML
    private Button Back;
    Settings settings;

    private ObservableList<String> combolist = FXCollections.observableArrayList("White", "Red", "Blue", "Yellow", "Green");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colourpicker.setItems(combolist);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("OptionsGUI.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();

        volumeslider.setValue(settings.getVolume());
    }



    public void ColourPicked(ActionEvent actionEvent) {
        settings.setColour((String) colourpicker.getValue());
    }

    public void BackPressed(ActionEvent actionEvent) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GameStartGUI.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage = (Stage) volumelabel.getScene().getWindow();
        stage.close();
    }


    public void VolumeDragDone(MouseDragEvent mouseDragEvent) {
        settings.setVolume(Math.round(volumeslider.getValue() *100)/100);

        volumelabel.setText("Music Volume : "+settings.getVolume());
    }
}
