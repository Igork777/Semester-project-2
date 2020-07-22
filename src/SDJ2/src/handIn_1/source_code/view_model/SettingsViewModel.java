package handIn_1.source_code.view_model;

import handIn_1.source_code.model.radiator.Radiator;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SettingsViewModel {
    private SimpleIntegerProperty radiatorPowerLevel, highPoint, lowPoint;
    private SimpleStringProperty radiatorTimer;
    private Radiator radiator;

    public SettingsViewModel(Radiator radiator) {
        this.radiator = radiator;
        radiatorPowerLevel = new SimpleIntegerProperty();
        radiatorTimer = new SimpleStringProperty();
        highPoint = new SimpleIntegerProperty();
        lowPoint = new SimpleIntegerProperty();

        this.radiator.addListener(evt ->
        {
            Object oldV = evt.getOldValue();
            Object newV = evt.getNewValue();
            String evtName = evt.getPropertyName();

            System.out.println(evtName);

            if (evtName.equals("Inner radiator state change")) {
                System.out.println(oldV + "->" + newV);
                radiatorPowerLevel.setValue((int) newV);
            } else if (evtName.equals("Timer start") || evtName.equals("Timer tick...")) {
                Platform.runLater(() -> {
                    radiatorTimer.setValue(newV.toString());
                });
            } else if (evtName.equals("Timer interrupted") || evtName.equals("Timer RIIING!!!")) {
                Platform.runLater(() -> {
                    radiatorTimer.setValue("");
                });
            }

        });
    }


    public boolean validateCriticalPoints() {
        if (lowPoint.getValue() < 0 || highPoint.getValue() < 0)
            return false;
        return lowPoint.getValue() < highPoint.getValue();
    }

    public void radiatorUp() {
        radiator.turnUp();
        int newLvl = radiator.getPowerState().getPower();
        this.radiatorPowerLevel.set(newLvl);
    }

    public void radiatorDown() {
        radiator.turnDown();
        int newLvl = radiator.getPowerState().getPower();
        this.radiatorPowerLevel.set(newLvl);
    }

    public void setHighPoint(int highPoint) {
        this.highPoint.set(highPoint);
    }

    public void setLowPoint(int lowPoint) {
        this.lowPoint.set(lowPoint);
    }

    public SimpleIntegerProperty radiatorPowerLevel() {
        return radiatorPowerLevel;
    }

    public SimpleStringProperty radiatorTimer() {
        return radiatorTimer;
    }

    public SimpleIntegerProperty highPointProperty() {
        return highPoint;
    }

    public SimpleIntegerProperty lowPointProperty() {
        return lowPoint;
    }

    public Radiator getRadiator() {
        return radiator;
    }
}
