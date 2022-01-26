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
import mazegamecoursework.Objects.VolumePicker;

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
    private VolumePicker volumePicker;
    private final ObservableList<String> combolist = FXCollections.observableArrayList("White", "Red", "Blue", "Yellow", "Green");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colourpicker.setItems(combolist);
        volumeslider.setValue(Settings.getVolume());


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("OptionsGUI.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();


    }



    public void ColourPicked(ActionEvent actionEvent) {
        Settings.setColour(colourpicker.getValue());
    }

    public void BackPressed(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) volumelabel.getScene().getWindow();
        stage.close();
    }




    public void setUpThread(){
        volumePicker = new VolumePicker(volumeslider, volumelabel);
        volumePicker.setRunnable(true);
        volumePicker.setDaemon(true);
        volumePicker.run();
    }

    public void VolumeDragDone(MouseEvent mouseEvent) {
        jhd();
    }

    private void jhd() {
        volumeslider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume  = newValue.doubleValue();
            volume  = Math.round(volume);
            volumelabel.setText("Music Volume : " + (int) volume);
            Settings.setVolume(volume);
        });
    }
}
