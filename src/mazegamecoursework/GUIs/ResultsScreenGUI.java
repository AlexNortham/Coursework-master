package mazegamecoursework.GUIs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mazegamecoursework.Objects.MazeGenerator;
import mazegamecoursework.Objects.MergeSort;
import mazegamecoursework.Objects.Settings;
import mazegamecoursework.Objects.SQLClass;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ResultsScreenGUI extends Application {

    @FXML
    public Label resultsArea;

    @FXML
    public Button signOut;

    @FXML
    public Button showResultsButton;
    public Label scoreLabel;
    public Button playAgain;


    @Override
    public void start(Stage primaryStage) throws Exception {





    }

    public void signOutPressed(ActionEvent actionEvent) {
    }

    private static final String DatabaseLocation = System.getProperty("user.dir") + "\\Database.accdb"; //This creates a string describing the address of the database



    public double[] showResults() throws SQLException {
        ArrayList<Double> Scores = new ArrayList<Double>();
        String sqlQuery = ("SELECT * FROM Scores");
        Connection con = SQLClass.getConnection();
        ResultSet rs = SQLClass.query(con, sqlQuery);
        while (rs.next()) {
            Scores.add(rs.getDouble("Score"));
        }

        double[] scoreArray = new double[Scores.size()];
        for (int i = 0; i < Scores.size(); i++) {
            scoreArray[i] = Scores.get(i);
        }
        MergeSort mergeSort = new MergeSort();
        scoreArray = mergeSort.sort(scoreArray);
        rs.close();

        // SQLClass.getConnection().commit();
        // SQLClass.getConnection().close();
        con.close();
        return scoreArray;
    }

    public String asefjk(double[] scoreArray) throws SQLException {

        String[] namesArray = new String[5];
        int j = 0;

        for (int i = 0; i < 5;) {
            if(namesArray[j] == null) {
                int temp = (int) scoreArray[j];
                String sqlQuery = ("SELECT UserName FROM Scores WHERE Score=" + temp);
                Connection con = SQLClass.getConnection();
                ResultSet resultSet = SQLClass.query(con, sqlQuery);


                while(resultSet.next()){
                    namesArray[j] = resultSet.getString("UserName");

                    j++;
                    i++;
                }
                //SQLClass.getConnection().close();
                resultSet.close();
                con.close();
            }

        }

        String results = "";
        for (int i = 0; i < 5; i++) {
            results = results  + (i+1) + ". " + namesArray[i] + " - "+ scoreArray[i] + "\n";

        }
        return results;
    }

    public void showResultsPressed(ActionEvent actionEvent) throws SQLException {
        String results = asefjk(showResults());

        resultsArea.setText(results);
        scoreLabel.setText("Your score was: "+ Settings.getScore());
    }

    public void playAgainPressed(ActionEvent actionEvent) throws UnsupportedAudioFileException, LineUnavailableException, IOException {


            MazeGenerator mazeGenerator = new MazeGenerator();
            mazeGenerator.setUpBoard();
            Stage stage = (Stage) resultsArea.getScene().getWindow();
            stage.close();

    }
}
