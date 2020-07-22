package handIn_1.source_code._util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** A class which create timer till the changing of the third state of radiator till the seconds
 */
public class MyTimer
{
    private long ms;
    private long msLeft;
    private PropertyChangeSupport support;
    private Thread countingThr;

    /**
     * constructor, which gets a parameter with the long parameter
     * @param ms amount of milliseconds
     */
    public MyTimer(long ms) {
        setTimer(ms);
        this.support = new PropertyChangeSupport(this);
    }

    public MyTimer() {
        this.support = new PropertyChangeSupport(this);
    }

    public long getMsLeft() {
        return msLeft;
    }

    public void setTimer(long ms) {
        this.ms = ms;
    }

    public void startTimer() {
        if (countingThr != null && countingThr.isAlive()) {
            countingThr.interrupt();
        }
        countingThr = new Thread(() -> {
            this.msLeft = this.ms;
            this.support.firePropertyChange("Timer start", null, this);
            while (msLeft > 0) {
                long sleep = msLeft >= 1000 ? 1000 : msLeft;
                try {
                    Thread.sleep(sleep);
                    msLeft -= sleep;
                    support.firePropertyChange("Timer tick...", null, this);
                } catch (InterruptedException e) {
                    support.firePropertyChange("Timer interrupted", null, this);
                    break;
                }
            }
            support.firePropertyChange("Timer RIIING!!!", null, this);
        });
        countingThr.start();
    }

    public void reStartTimer() {
        this.setTimer(ms);
        startTimer();
    }

    public void interrupt() {
        if (countingThr.isAlive()) {
            countingThr.interrupt();
        }
    }

    public void removeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    public void addListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        long seconds = msLeft / 1000;
        long minutes = seconds / 60;
        seconds = seconds - minutes * 60;

        String sec = seconds > 9 ? seconds + "" : "0" + seconds;
        String min = minutes > 9 ? minutes + "" : "0" + minutes;
        return min + ":" + sec;
    }
}
