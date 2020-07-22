package handIn_1.source_code;


import handIn_1.source_code.core.ModelFactory;
import handIn_1.source_code.core.ViewHandler;
import handIn_1.source_code.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class TemperatureApp extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        ModelFactory modelFactory = new ModelFactory();
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory,stage);
        viewHandler.start();

        modelFactory.getCloserThermometerThr().start();
        modelFactory.getFurtherThermometerThr().start();
        modelFactory.getOutdoorThermometerThr().start();
    }
}
