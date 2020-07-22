package client.core;

import client.view.MainViewController;
import client.view.SidebarViewController;
import client.view.ViewController;
import client.view.ViewControllerFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import shared.utils.Views;

import java.io.IOException;

/**
 * The class opens certain windows int the application
 */
public class ViewHandler {
    /**
     * Important fields, that help to build the class
     */
    private Stage stage = null;
    private Scene scene;
    private ViewController ctrl = null;
    private ViewModelFactory viewModelFactory;
    private MainViewController mainViewController = (MainViewController) ViewControllerFactory.getViewController(Views.MainView.name());
    private ScrollPane mainPane = mainViewController.mainPane;

    /**
     * Constructor, that provides the viewModelFactory
     * @param viewModelFactory paramter, that will be assigned to local ViewModelFactory field
     */
    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    /**
     *this method starts the scene
     * Two threads download two massive views in the background
     *  to accelerate the program. Then two windows start the main view
     *  with the media player and first window with the primary menu.
     * @throws IOException method open() can call this Exception
     */
    public void start() throws IOException {
        stage = new Stage();

        Thread threadDownloadRegisterView = new Thread(() ->
                ViewControllerFactory.getViewController(Views.RegisterView.name())
        );

        Thread threadDownloadLogInView = new Thread(() ->
            ViewControllerFactory.getViewController(Views.LogIn.name())
        );
        try {
            threadDownloadRegisterView.start();
            threadDownloadLogInView.start();
            threadDownloadRegisterView.join();
            threadDownloadLogInView.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        openView(Views.MainView.name());
        viewToPane(Views.FirstWindow.name());
    }

    /**
     * Method which opens the view based on the enum name, that is passed. It is done in order to prevent some mistakes
     * that can occur during writing simple strings. Name of the window is taken from the enum too.
     * In this program not all the views will have view models. First window doesn't have a view model. That's why open
     * method has two arguments. The second argument is view model. If null passed as View Model
     * parameter, then it means that user wants to open first window.Otherwise, proper view model will be passed.
     * Also all the windows are not resizable
     *
     * @param window name of the FXML file, which has to be opened in form of enum
     * @throws IOException method load() can call IOException
     */
    public void openView(String window) throws IOException {
        ctrl = ViewControllerFactory.getViewController(window);
        ctrl.init(viewModelFactory, this);
        stage.setTitle(window);
        scene = ctrl.getScene();
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Sets the content of the sidebar
     *
     * @param window the name of the view to display as sidebar
     */
    public void setSidebar(String window) {
        ctrl = ViewControllerFactory.getViewController(window);
        ctrl.init(viewModelFactory, this);
        Parent root = ctrl.getRoot();
        mainViewController.mainBorderPane.setLeft(root);
        stage.sizeToScene();
    }

    /**
     * Displays the given view in mainPane
     *
     * @param window name of the FXML file (no extention)
     */
    public void viewToPane(String window) {
        ViewController viewController = ViewControllerFactory.getViewController(Views.Sidebar.name());
        if(window.equals(Views.UserProfile.name()) || window.equals(Views.RegisterOrganizer.name()) || window.equals(Views.RegisterMusicianView.name()))
        {
            ((SidebarViewController)viewController).setUserMode();
        }
        else if (window.equals(Views.MusicianView.name()))
        {
            ((SidebarViewController)viewController).setMusicianMode();
        }
        //Sets sidebar feature to be relevant for organizer
        else if (window.equals(Views.OrganizerProfile.name()))
        {
            ((SidebarViewController)viewController).setOrganizerMode();
        }
        ctrl = ViewControllerFactory.getViewController(window);
        ctrl.init(viewModelFactory, this);
        Parent root = ctrl.getRoot();
        mainViewController.mainBorderPane.setCenter(root);
        stage.sizeToScene();
    }

    /**
     * Appends the given Root object to the giver Pane object
     * @param root root to be appended
     * @param pane pane to which append
     */
    public void rootToPane(Parent root, Pane pane) {
        pane.getChildren().add(root);
        stage.sizeToScene();
    }

    /**
     * Displays the given view in the given pane
     *
     * @param window name of the FXML file (no extention)
     * @param pane   the pane to put the view in
     */
    public void viewToPane(String window, Pane pane) {
        ctrl = ViewControllerFactory.getViewController(window);
        ctrl.init(viewModelFactory, this);
        Parent root = ctrl.getRoot();
        pane.getChildren().add(root);
        stage.sizeToScene();
    }

    public void viewToPane(String window, Pane pane, boolean append) {
        ctrl = ViewControllerFactory.getViewController(window);
        ctrl.init(viewModelFactory, this);
        Parent root = ctrl.getRoot();
        if(!append){ pane.getChildren().clear(); }
        pane.getChildren().add(root);
        stage.sizeToScene();
    }


    /**
     * getter that returns viewModelFactory
     *
     * @return viewModelFactory
     */
    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }

}
