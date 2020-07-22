package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import shared.utils.Utils;
import shared.utils.Views;


import java.io.IOException;


public class FirstWindowController extends ViewController
{
    /**
     * FXML fields
     */
    @FXML
    public Label logInId;
    @FXML
    public Label signUpId;
    @FXML
    private MediaView mediaViewId;
    @FXML
    private MediaPlayer mediaPlayerVideo = null;
    private Media mediaVideo = null;

    private ViewHandler viewHandler;
    private boolean isFirstTime = true;

    /**
     * The method initializes viewHandler and viewModel
     * Also the background video and media player start to play
     * As long as we don't need to create a lot of instances of the video,
     * we check if this window was opened for the first time
     *
     * @param viewModelFactory parameter which will be assigned to the local variable
     * @param viewHandler      parameter which will be assigned to local field viewHandler
     */
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        if (isFirstTime)
        {
            String s = Utils.getMutualPath();
            mediaVideo = new Media("file:///" + s + "/src/SEP2/src/videos/Fire.mp4");
            mediaPlayerVideo = new MediaPlayer(mediaVideo);

            mediaPlayerVideo.setVolume(0);
            mediaPlayerVideo.setCycleCount(MediaPlayer.INDEFINITE);
            mediaViewId.setMediaPlayer(mediaPlayerVideo);
            mediaPlayerVideo.play();
            isFirstTime = false;
        }
        this.viewHandler = viewHandler;

    }

    /**
     * Some logic if user clicks on the picture Sign Up
     * @param mouseEvent click of the mouse
     */
    public void onClickSignUp(MouseEvent mouseEvent) {
        viewHandler.viewToPane(Views.RegisterView.name());
    }

    /**
     * If users clicks on LogIn label, than new window opens
     *
     * @param mouseEvent click of the mouse
     */
    public void onClickLogIn(MouseEvent mouseEvent) throws IOException {
        viewHandler.viewToPane(Views.LogIn.name());
    }


    /**
     * If mouse hovers Sign Up label, then picture changes the color and cursor changes to hand
     *
     * @param mouseEvent event, when mouse hovers on Sign Up label
     */
    public void onMouseEnteredSignUp(MouseEvent mouseEvent) {

        signUpId.setStyle("-fx-text-fill: yellow");
    }

    /**
     * If mouse doesn't hover on Sign Up label anymore, then previous color is set and cursor changes to arrow
     *
     * @param mouseEvent event, when mouse doesn't hover on Sign Up label anymore
     */
    public void onMouseExitedSignUp(MouseEvent mouseEvent) {
        signUpId.setStyle("-fx-text-fill: f50000");

    }

    /**
     * If mouse hovers  Log In label, then label color changes and cursor changes to hand
     *
     * @param mouseEvent event, when mouse hovers on Log In label
     */
    public void onMouseEnteredLogIn(MouseEvent mouseEvent) {
        logInId.setStyle("-fx-text-fill: yellow");

    }

    /**
     * If mouse doesn't hover on Log In label anymore, then previous color is set and cursor changes to arrow
     *
     * @param mouseEvent event, when mouse doesn't hover on Log In label anymore
     */
    public void onMouseExitedLogIn(MouseEvent mouseEvent) {
        logInId.setStyle("-fx-text-fill:  #2256f0");
    }
}
