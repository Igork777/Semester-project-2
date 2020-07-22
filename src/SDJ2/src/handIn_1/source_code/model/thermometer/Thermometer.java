package handIn_1.source_code.model.thermometer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Instant;

public abstract class Thermometer implements ThermometerModel, Runnable, PropertyChangeListener {
    private double temp;
    private int dist = -1;
    private long lastMeasured;
    private static double min = 10;
    private static double max = 30;
    protected PropertyChangeSupport propertyChangeSupport;

    public Thermometer(double temp, int dist) {
        this.temp = temp;
        this.dist = dist;
        this.lastMeasured = Instant.now().getEpochSecond();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public Thermometer(double temp) {
        this(temp, -1);
    }

    public double getTemp() {
        return this.temp;
    }

    protected void setTemp(double t) {
        this.temp = t;
    }

    public int getDist() {
        return dist;
    }

    public static void setMin(double t) {
        min = t;
    }

    public static void setMax(double t) {
        max = t;
    }

    public static double getMin() {
        return min;
    }

    public static double getMax() {
        return max;
    }

    protected long updateLastMeasured() {
        this.lastMeasured = Instant.now().getEpochSecond();
        return this.lastMeasured;
    }

    public long getLastMeasured() {
        return this.lastMeasured;
    }

    public void addListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
