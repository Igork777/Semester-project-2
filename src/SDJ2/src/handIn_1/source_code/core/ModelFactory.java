package handIn_1.source_code.core;

import handIn_1.source_code.model.radiator.Radiator;
import handIn_1.source_code.model.thermometer.Thermometer;

import java.beans.PropertyChangeEvent;
import java.util.Random;

public class ModelFactory
{
    private Thermometer closerThermometer, furtherThermometer, outdoorThermometer;
    private Radiator radiator;
    private Thread outdoorThermometerThr, closerThermometerThr, furtherThermometerThr;
    public ModelFactory ()
    {
        radiator = new Radiator();
        this.outdoorThermometer = new Thermometer(7)
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt) { }

            @Override
            public void run()
            {
                this.propertyChangeSupport.firePropertyChange("Outdoor Thermometer", null, this.getTemp());
                while(true){
                    try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
                    reCalculateTemperature();
                }
            }

            @Override
            public double reCalculateTemperature() {
                double t0 = this.getTemp();
                double left = t0 - Thermometer.getMin();
                double right = Thermometer.getMax() - t0;
                int sign = Math.random() * (left + right) > left ? 1 : -1;
                t0 += sign * Math.random();
                setTemp(t0);
                this.propertyChangeSupport.firePropertyChange("Outdoor Thermometer", null, t0);
                return t0;
            }

        };

        this.closerThermometer = new Thermometer(10, 1) {
            private Random rand = new Random();

            @Override
            public void run() {
                double temperature;

                this.propertyChangeSupport.firePropertyChange("Closer Thermometer", null, this.getTemp());
                while(true){
                    int sleepTime = rand.nextInt(4000) + 4000;
                    try { Thread.sleep(sleepTime); } catch (InterruptedException e) { e.printStackTrace(); }
                    reCalculateTemperature();
                }
            }

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String evtName = evt.getPropertyName();

                if(evtName.equals("Radiator State Change")){
                    reCalculateTemperature();
                }
            }

            @Override
            public double reCalculateTemperature()
            {
                int p = radiator.getPowerState().getPower();
                double t = this.getTemp();
                int d = this.getDist();
                double t0 = outdoorThermometer.getTemp();
                long lastMeasured = this.getLastMeasured();
                long newMeasure = this.updateLastMeasured();
                long s = newMeasure - lastMeasured;
                double tMax = Math.min(11 * p + 10, 11 * p + 10 + t0);
                tMax = Math.max(Math.max(t, tMax), t0);
                double heaterTerm = 0;

                if (p > 0) {
                    double den = Math.max((tMax * (20 - 5 * p) * (d + 5)), 0.1);
                    heaterTerm = 30 * s * Math.abs(tMax - t) / den;
                }

                double outdoorTerm = (t - t0) * s / 250.0;
                t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);

                setTemp(t);
                this.propertyChangeSupport.firePropertyChange("Closer Thermometer", null, t);
                return t;
            }
        };
        this.furtherThermometer = new Thermometer(10, 7)
        {
            private Random rand = new Random();

            @Override
            public void run() {
                this.propertyChangeSupport.firePropertyChange("Further Thermometer", null, this.getTemp());
                while(true){
                    int sleepTime = rand.nextInt(4000) + 4000;
                    try { Thread.sleep(sleepTime); } catch (InterruptedException e) { e.printStackTrace(); }
                    reCalculateTemperature();
                }
            }

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String evtName = evt.getPropertyName().toString();

                if(evtName.equals("Radiator State Change")){
                    reCalculateTemperature();
                }
            }

            @Override
            public double reCalculateTemperature() {
                int p = radiator.getPowerState().getPower();
                double t = this.getTemp();
                int d = this.getDist();
                double t0 = outdoorThermometer.getTemp();
                long lastMeasured = this.getLastMeasured();
                long newMeasure = this.updateLastMeasured();
                long s = newMeasure - lastMeasured;

                double tMax = Math.min(11 * p + 10, 11 * p + 10 + t0);
                tMax = Math.max(Math.max(t, tMax), t0);
                double heaterTerm = 0;

                if (p > 0) {
                    double den = Math.max((tMax * (20 - 5 * p) * (d + 5)), 0.1);
                    heaterTerm = 30 * s * Math.abs(tMax - t) / den;
                }

                double outdoorTerm = (t - t0) * s / 250.0;
                t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);

                setTemp(t);
                this.propertyChangeSupport.firePropertyChange("Further Thermometer", null, t);
                return t;
            }
        };

//        radiator.addListener("Radiator State Change", closerThermometer);
//        radiator.addListener("Radiator State Change", furtherThermometer);

        outdoorThermometerThr = new Thread(outdoorThermometer);
        closerThermometerThr = new Thread(closerThermometer);
        furtherThermometerThr = new Thread(furtherThermometer);

    }
    public Thermometer getCloserThermometer()
    {
        return closerThermometer;
    }
    public Thermometer getFurtherThermometer()
    {
        return furtherThermometer;
    }
    public Thermometer getOutdoorThermometer()
    {
        return outdoorThermometer;
    }

    public Thread getOutdoorThermometerThr() {
        return outdoorThermometerThr;
    }

    public Thread getCloserThermometerThr() {
        return closerThermometerThr;
    }

    public Thread getFurtherThermometerThr() {
        return furtherThermometerThr;
    }

    public Radiator getRadiator()
    {
        return radiator;
    }
}
