package handIn_1.source_code.core;

import handIn_1.source_code.view.SettingsViewController;
import handIn_1.source_code.view.TemperaturesViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
    private Scene temperaturesViewScene, settingViewScene;
    private Stage stage;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory vmf, Stage stage){
        this.stage = stage;
        viewModelFactory = vmf;
    }

    public void start() throws IOException
    {
        openTemperatureView();
    }
    public void openTemperatureView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/TemperaturesView.fxml"));
        Parent root = loader.load();
        TemperaturesViewController temperaturesViewController = loader.getController();
        temperaturesViewController.init(this, viewModelFactory);
        stage.setTitle("Temperatures");
        temperaturesViewScene = new Scene(root);
        stage.setScene(temperaturesViewScene);
        stage.show();
    }
    public void openSettingsView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/SettingsView.fxml"));
        Parent root = loader.load();
        SettingsViewController settingsViewController = loader.getController();
        settingsViewController.init(this, viewModelFactory);
        stage.setTitle("Settings");
        settingViewScene = new Scene(root);
        stage.setScene(settingViewScene);
        stage.show();
    }
}
