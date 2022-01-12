package mazegamecoursework.Objects;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


import javax.sound.sampled.*;

public class MusicPlayer {
    private String dir = System.getProperty("user.dir");
    private String songFileName= dir + "\\music\\MazeSong.wav";
    private double userVolume;
    Clip clip;

    public MusicPlayer(){
        this.userVolume = Settings.getVolume();
    }


    public void playSong(){



        InputStream song;
        try{
            song = new FileInputStream(new File("MazeSong.wav"));
            AudioStream audioStream = new AudioStream(song);
            AudioPlayer.player.start(audioStream);
        }catch(Exception e){
            e.printStackTrace();
        }

        File amogus = new File(songFileName);
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(amogus));
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }


    }


}
