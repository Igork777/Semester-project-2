package handIn_1.source_code.model.radiator;

public class Power3State implements RadiatorState {
    private final int POWER = 3;
    private Radiator radiator;
    private Thread powerThread;
    private int millisecondsToWait = 10000;

    public Power3State(Radiator radiator) {
        this.radiator = radiator;
        radiator.timer.setTimer(millisecondsToWait);
        powerThread = new Thread(() ->
        {
            try {
                radiator.timer.startTimer();
                Thread.sleep(millisecondsToWait);
                radiator.support.firePropertyChange("Inner radiator state change", 3, 2);
                radiator.setPowerState(turnDown());
            } catch (InterruptedException e) {
                radiator.timer.interrupt();
                System.out.println("As you wish, my user!");
            }
        });
        powerThread.start();
    }

    @Override
    public RadiatorState turnUp() {
        powerThread.interrupt();
        return new Power3State(radiator);
    }

    @Override
    public RadiatorState turnDown() {
        powerThread.interrupt();
        return new Power2State(radiator);
    }

    @Override
    public int getPower() {
        return POWER;
    }
}
