package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Abstract class which is made in order to collect all the mutual data of all the views
 */
public abstract class ViewController
{
    private Parent root;
    private Scene scene;

    /**
     * Method, which initializes the controller
     * @param viewModelFactory parameter, in order to have the connection with the model layer
     * @param viewHandler parameter, in order to change views
     */
    abstract public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler);



    /**
     * Method which returns the root. Method is used for implementation of FlyWeight pattern
     * @return root of Controller
     */
    public Parent getRoot() {
        return root;
    }

    /**
     * Method is used for implementation of FlyWeight pattern. Method sets the root
     * @param root root of the Controller
     */
    public void setRoot(Parent root)
    {
        this.root = root;
        scene = new Scene(root);
    }

    /**
     * Method is used for changing cursor, when it hovers on any button. Scene is returned in ViewHandler
     * for understanding on which view the cursor has to be changed
     * @return scene
     */
    public Scene getScene()
    {
        return scene;
    }

}
