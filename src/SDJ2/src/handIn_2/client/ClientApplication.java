package handIn_2.client;

import handIn_2.client.core.ClientFactory;
import handIn_2.client.core.ModelFactory;
import handIn_2.client.core.ViewHandler;
import handIn_2.client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ClientFactory clientFactory = new ClientFactory();
        ModelFactory modelFactory = new ModelFactory(clientFactory);
        ViewModelFactory VMFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(VMFactory, stage);
        viewHandler.start();
    }
}
