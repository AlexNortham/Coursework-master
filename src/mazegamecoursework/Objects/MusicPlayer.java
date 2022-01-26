package mazegamecoursework.Objects;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Random;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


import javax.sound.sampled.*;


public class MusicPlayer extends Application {
    private final String dir = System.getProperty("user.dir");
    private final String songFileName=dir+"\\music\\MazeSong.wav";
    private final String[] songArrays = {dir+"\\music\\MazeSong1.wav", dir+"\\music\\MazeSong2.mp3", dir+"\\music\\MazeSong3.mp3", dir+"\\music\\MazeSong4.mp3"};
    private final double userVolume;
    Clip clip;

    public MusicPlayer(){
        this.userVolume = Settings.getVolume();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private MediaPlayer mediaPlayer;
    public void playSong(){
        Random random = new Random();
        int x = random.nextInt(4);
        Media media = new Media((Paths.get(songArrays[x]).toUri().toString()));
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(userVolume/100);
        mediaPlayer.play();




    }

    public void stopSong(){
        mediaPlayer.stop();
    }


}
