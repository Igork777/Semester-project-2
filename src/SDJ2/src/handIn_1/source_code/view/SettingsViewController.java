package handIn_1.source_code.view;

import handIn_1.source_code.core.ViewHandler;
import handIn_1.source_code.core.ViewModelFactory;
import handIn_1.source_code.model.thermometer.Thermometer;
import handIn_1.source_code.view_model.SettingsViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;

public class SettingsViewController implements ViewController
{
    private ViewHandler viewHandler;
    private SettingsViewModel viewModel;
    @FXML private TextField radiatorPowerLevelTextField;
    @FXML private Button radiatorPowerLevelDownButton;
    @FXML private Button radiatorPowerLevelUpButton;
    @FXML private Label radiatorTimerLabel;
    @FXML private TextField highPointTextField;
    @FXML private TextField lowPointTextField;
    @FXML private Button saveBtn;
    private String oldValueHighPoint, oldValueLowPoint;

    private NumberStringConverter converter = new NumberStringConverter();

    @Override
    public void init(ViewHandler vh, ViewModelFactory viewModelFactory)
    {
        viewHandler = vh;
        this.viewModel = viewModelFactory.getSettingsVM();
        radiatorPowerLevelTextField.textProperty().bind(viewModel.radiatorPowerLevel().asString());
        radiatorTimerLabel.textProperty().bind(viewModel.radiatorTimer());
        highPointTextField.textProperty().bindBidirectional(viewModel.highPointProperty(), converter);
        lowPointTextField.textProperty().bindBidirectional(viewModel.lowPointProperty(), converter);
        highPointTextField.textProperty().setValue(String.valueOf((int)Thermometer.getMax()));
        lowPointTextField.textProperty().setValue(String.valueOf((int)Thermometer.getMin()));
        oldValueHighPoint = String.valueOf((int)Thermometer.getMax());
        oldValueLowPoint = String.valueOf((int)Thermometer.getMin());
    }

    public void onRadPowerUpButton(ActionEvent actionEvent)
    {
        viewModel.radiatorUp();
    }
    public void onRadPowerDownButton(ActionEvent actionEvent) {
        viewModel.radiatorDown();
    }
    public void onClickSave(ActionEvent actionEvent) throws IOException
    {
        try
        {
            Integer.parseInt(highPointTextField.textProperty().getValue());
            Integer.parseInt(lowPointTextField.textProperty().getValue());
        }
        catch (Exception ex)
        {
            highPointTextField.textProperty().setValue(oldValueHighPoint);
            lowPointTextField.textProperty().setValue(oldValueLowPoint);
            return;
        }
        if(!(viewModel.validateCriticalPoints()))
        {
            highPointTextField.textProperty().setValue(oldValueHighPoint);
            lowPointTextField.textProperty().setValue(oldValueLowPoint);
            return;
        }
        oldValueHighPoint = highPointTextField.textProperty().getValue();
        oldValueLowPoint = lowPointTextField.textProperty().getValue();
        Thermometer.setMax(Double.parseDouble(oldValueHighPoint));
        Thermometer.setMin(Double.parseDouble(oldValueLowPoint));

    }

    public void onClickChangeToTemperatures(ActionEvent actionEvent) throws IOException
    {
        viewHandler.openTemperatureView();
    }
}
