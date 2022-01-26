package mazegamecoursework.Objects;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class VolumePicker extends Thread implements Runnable{
    private final Slider volumeSlider;
    private final Label volumeLabel;
    protected boolean runnable = false;

    public VolumePicker(Slider volumeSlider, Label volumeLabel){
        this.volumeLabel  = volumeLabel;
        this.volumeSlider = volumeSlider;
    }

    @Override
    public void run(){

            Runnable volumeUpdater = new Runnable() {
                @Override
                public void run() {

                        Settings.setVolume(Math.round(volumeSlider.getValue() * 100) / 100);

                        volumeLabel.setText("Music Volume : " + Settings.getVolume());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            while (runnable) {
                Platform.runLater(volumeUpdater);

            }

    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }
}
