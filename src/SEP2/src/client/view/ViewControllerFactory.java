package client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.utils.Views;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * This is the class which helps to create FlyWeight pattern. Here, we don't load views from FXML all the time.
 * In this, if the view will be opened for the first time - than it will be loaded from FXML. Otherwise, we will just take
 * already done ViewController from the HashMap
 */
public class ViewControllerFactory {

    private static HashMap<String, ViewController> viewControllers = new HashMap<>();

    /**
     * Method which returns new or already made controller.Method is thread safe
     *
     * @param controllerName name of the view
     * Only critical places are synchronized, where static shared resources are used.
     * @return proper controller
     */
    public static ViewController getViewController(String controllerName) {

        ViewController viewController;
        synchronized (ViewControllerFactory.class)
        {
             viewController = viewControllers.get(controllerName);
        }
        if (viewController == null) {
            URL location = null;
            Path currentRelativePath = Paths.get("");
            String s = (currentRelativePath.toAbsolutePath().toString()).replace("\\", "/");
            String path = "file:/" + s + "/src/SEP2/src/client/view/";
            FXMLLoader loader = new FXMLLoader();
            try {
                String fxmlPath = path + controllerName + ".fxml";
                location = new URL(fxmlPath);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            loader.setLocation(location);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            viewController = loader.getController();
            viewController.setRoot(root);
            synchronized (ViewControllerFactory.class)
            {
                viewControllers.put(controllerName, viewController);
            }
        }
        return viewController;
    }

    /**
     * initializes and returns a new instance of the needed viewController
     * @param viewName
     * @return
     */
    public static ViewController getNewViewController(String viewName) {
        URL location = null;
        Path currentRelativePath = Paths.get("");
        String s = (currentRelativePath.toAbsolutePath().toString()).replace("\\", "/");
        String path = "file:/" + s + "/src/SEP2/src/client/view/";
        FXMLLoader loader = new FXMLLoader();
        try {
            String fxmlPath = path + viewName + ".fxml";
            location = new URL(fxmlPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        loader.setLocation(location);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ViewController viewController = loader.getController();
        viewController.setRoot(root);
        return viewController;
    }
}
