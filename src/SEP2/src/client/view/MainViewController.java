package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.MainViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import shared.utils.SongState;

import java.beans.PropertyChangeEvent;

public class MainViewController extends ViewController {

    @FXML
    private Label songNameId;
    @FXML
    private Label artistNameId;
    @FXML
    private ImageView playId;

    public ScrollPane mainPane;
    public BorderPane mainBorderPane;

    private ViewHandler viewHandler;
    private MainViewModel mainViewModel;

    /**
     * method initializes viewModel and viewHandler
     *also it puts the first soundtrack in the media player
     * sets volume, prints artist name and song name and
     * commands to go to the next one when the first will be finished
     *
     * @param viewModelFactory   parameter which will be used to retrieve the needed VM
     * @param viewHandler parameter which will be assigned to local variable viewHandler
     */
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        mainViewModel = viewModelFactory.getMainViewModel();
        mainViewModel.addListener(SongState.PLAY.name(), this::setSongOnPlay);
        mainViewModel.addListener(SongState.STOP.name(), this::setSongOnStop);
        songNameId.textProperty().bind(mainViewModel.songNameProperty());
        artistNameId.textProperty().bind(mainViewModel.artistNameProperty());
        mainViewModel.init();
    }

    private void setSongOnStop(PropertyChangeEvent propertyChangeEvent) {
          playId.setImage(new Image(getClass().getResource(propertyChangeEvent.getNewValue().toString()).toString(), true));
    }

    private void setSongOnPlay(PropertyChangeEvent propertyChangeEvent)
    {
          playId.setImage(new Image(getClass().getResource(propertyChangeEvent.getNewValue().toString()).toString(), true));
    }



    /**
     * stops or plays music depends on the condition of field isPlayerOn
     * depends on the situation, proper image is set and the proper value assigned to the field isPlayerOn
     *
     * @param mouseEvent event, when mouse clicks on the "play" button
     */
    public void onClickPlay(MouseEvent mouseEvent)
    {
      mainViewModel.onClickPlay();
    }

    /**
     * method goes to the next soundtrack. Last played soundtrack is stopped
     * If we are listening last soundtrack in the music storage, then by clicking on the next Song button we
     * start over from the first soundtrack
     * In the end method playTrack is called with the id of the song
     *
     * @param mouseEvent event, when mouse clicks on the "next Song" button
     */
    public void onClickNextSong(MouseEvent mouseEvent) {
        mainViewModel.onClickNextSong();
    }

    /**
     * method goes to the previous soundtrack. Last played soundtrack is stopped
     *
     * @param mouseEvent event, when mouse clicks on the "previous Song" button
     */
    public void onClickPreviousSong(MouseEvent mouseEvent) {
        mainViewModel.onClickPreviousSong();
    }
}
