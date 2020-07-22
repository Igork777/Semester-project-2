package handIn_1.source_code.view;

import handIn_1.source_code.core.ViewHandler;
import handIn_1.source_code.core.ViewModelFactory;
import handIn_1.source_code.view_model.TemperaturesViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TemperaturesViewController implements ViewController
{
    @FXML private TextField closerThermometerTextField;
    @FXML private TextField furtherThermometerTextField;
    @FXML private TextField outdoorThermometerTextField;
    @FXML private Label closerThermometerWarningLabel;
    @FXML private Label furtherThermometerWarningLabel;
    private ViewHandler viewHandler;
    private TemperaturesViewModel tempViewModel;


    @Override public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.viewHandler = vh;
        this.tempViewModel = vmf.getTemperaturesVM();

        closerThermometerTextField.textProperty().bind(tempViewModel.closerTemperature());
        furtherThermometerTextField.textProperty().bind(tempViewModel.furtherTemperature());
        outdoorThermometerTextField.textProperty().bind(tempViewModel.outdoorTemperature());
        closerThermometerWarningLabel.textProperty().bind(tempViewModel.closerTemperatureWarningProperty());
        furtherThermometerWarningLabel.textProperty().bind(tempViewModel.furtherTemperatureWarningProperty());
    }

    @FXML public void onSettingsButton()
    {
        try {
        viewHandler.openSettingsView();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
