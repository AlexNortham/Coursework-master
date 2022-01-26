package mazegamecoursework.Objects;

import javax.swing.*;

public class Timer extends Thread implements Runnable {
    protected boolean runnable;
    private double time= 0;
    private final JLabel timeLabel;

    public Timer(JLabel timeLabel){
        this.timeLabel = timeLabel;
    }
    @Override
    public void run(){
        synchronized (this){
            while (runnable){
                timeLabel.setText(Double.toString(time));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time = time + 0.1;
                time = (double) Math.round(time*100)/100;
            }
        }
    }

    public double getTime() {
        return time;
    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }
}
