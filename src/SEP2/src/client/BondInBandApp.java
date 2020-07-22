package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * The class which actually build the project
 */
public class BondInBandApp extends Application {
    private ClientFactory clientFactory;

    /**
     * Method, which creates all the basic elements in the program
     *
     * @param stage mandatory argument, which is here because of the extension of the Application class
     * @throws Exception is thrown from method start() in View Handler class
     */
    @Override
    public void start(Stage stage)  {
        clientFactory = new ClientFactory();
        ModelFactory modelFactory = new ModelFactory(clientFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        try {
            viewHandler.start();
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stop()
    {
        System.exit(0);
    }
}
