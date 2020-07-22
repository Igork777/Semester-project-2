package handIn_1.source_code.view_model;

import handIn_1.source_code.model.thermometer.Thermometer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;

public class TemperaturesViewModel {
    private StringProperty closerTemperature;
    private StringProperty furtherTemperature;
    private StringProperty outdoorTemperature;
    private StringProperty closerTemperatureWarning;
    private StringProperty furtherTemperatureWarning;

    public TemperaturesViewModel(Thermometer closerTM, Thermometer furtherTM, Thermometer outdoorTM) {
        closerTemperature = new SimpleStringProperty();
        furtherTemperature = new SimpleStringProperty();
        outdoorTemperature = new SimpleStringProperty();
        closerTemperatureWarning = new SimpleStringProperty();
        furtherTemperatureWarning = new SimpleStringProperty();

        // LISTENERS
        closerTM.addListener(evt ->
        {
            if (evt.getPropertyName().equals("Closer Thermometer")) {
                double newValue = (double) evt.getNewValue();
                String val = new DecimalFormat("##.##").format(newValue) + "";
                Platform.runLater(() -> {
                    closerTemperature.setValue(val);
                    if (newValue < Thermometer.getMin())
                        closerTemperatureWarning.setValue("Too cold");
                    else if (newValue > Thermometer.getMax())
                        closerTemperatureWarning.setValue("Too hot");
                    else {
                        closerTemperatureWarning.setValue("");
                    }
                });
            }
        });

        furtherTM.addListener(evt ->
        {
            if (evt.getPropertyName().equals("Further Thermometer")) {
                double newValue = (double) evt.getNewValue();
                String val = new DecimalFormat("##.##").format(newValue) + "";
                Platform.runLater(() -> {
                    furtherTemperature.setValue(val);
                    if (newValue < Thermometer.getMin())
                        furtherTemperatureWarning.setValue("Too cold");
                    else if (newValue > Thermometer.getMax())
                        furtherTemperatureWarning.setValue("Too hot");
                    else {
                        furtherTemperatureWarning.setValue("");
                    }
                });
            }
        });

        outdoorTM.addListener(evt ->
        {
            if (evt.getPropertyName().equals("Outdoor Thermometer")) {
                double newValue = (double) evt.getNewValue();
                String val = new DecimalFormat("##.##").format(newValue) + "";
                Platform.runLater(() -> {
                    outdoorTemperature.setValue(val);
                });
            }
        });
    }

    public StringProperty closerTemperature() {
        return closerTemperature;
    }

    public StringProperty furtherTemperature() {
        return furtherTemperature;
    }

    public StringProperty outdoorTemperature() {
        return outdoorTemperature;
    }

    public StringProperty closerTemperatureWarningProperty() {
        return closerTemperatureWarning;
    }

    public StringProperty furtherTemperatureWarningProperty() {
        return furtherTemperatureWarning;
    }
}
