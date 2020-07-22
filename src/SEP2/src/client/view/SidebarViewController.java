package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.LocalStorageImpl;
import client.viewModel.SideBarViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import shared.utils.Views;

public class SidebarViewController extends ViewController {
    @FXML
    private ImageView bandsImageId;
    @FXML
    private Label chatId;
    @FXML
    private ImageView chatImageId;
    @FXML
    private Label bandId;
    private ViewHandler viewHandler;
    private SideBarViewModel sideBarViewModel;
    private LocalStorageImpl localStorage = LocalStorageImpl.getInstance();

    /**
     * init method for sidebar view controller
     *
     * @param viewModelFactory parameter, in order to have the connection with the model layer
     * @param viewHandler      parameter, in order to change views
     */
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        sideBarViewModel = viewModelFactory.getSideBarViewModel();
    }

    public void onClickBands(MouseEvent mouseEvent) {
        isMusicianRegistered();
        viewHandler.viewToPane(Views.CreateOrOpenBand.name());
    }

    public void onClickChat(MouseEvent mouseEvent) {

    }

    /**
     * Method triggered after organizer profile label is pressed.
     * Attempts to retrieve information whether logged in user already has an organizer profile.
     * If organizer profile exists, switch to organizer view, otherwise continue to register an organizer.
     */
    public void onOrganizerProfileClicked() {
        if (sideBarViewModel.isOrganizerRegistered()) {
            viewHandler.viewToPane(Views.OrganizerProfile.name());
        } else {
            viewHandler.viewToPane(Views.RegisterOrganizer.name());
        }
    }

    /**
     * Method triggered after musician profile click
     *
     * @param mouseEvent
     */
    public void onMusicianProfileClicked(MouseEvent mouseEvent)
    {

        if (isMusicianRegistered()) {
            viewHandler.viewToPane(Views.RegisterMusicianView.name());
        } else {
            viewHandler.viewToPane(Views.MusicianView.name());
        }
    }

    private boolean isMusicianRegistered() {
        localStorage.fetchMusicianInfo(localStorage.getCurrentUser().getUserId());
        localStorage.fetchPlayedInstruments(localStorage.getCurrentUser().getUserId());
        return sideBarViewModel.isMusicianRegistered();
    }

    public void onUserProfileClick(MouseEvent mouseEvent) {
        viewHandler.viewToPane(Views.UserProfile.name());
    }

    public void setMusicianMode() {
        chatId.setDisable(false);
        chatImageId.setDisable(false);
        bandId.setDisable(false);
        bandsImageId.setDisable(false);
    }

    public void setUserMode() {
        chatId.setDisable(true);
        chatImageId.setDisable(true);
        bandId.setDisable(true);
        bandsImageId.setDisable(true);
    }

    public void setOrganizerMode() {
        chatId.setDisable(false);
        chatImageId.setDisable(false);
        bandId.setDisable(true);
        bandsImageId.setDisable(true);
    }

    public void onClickSearch(MouseEvent mouseEvent){
        viewHandler.viewToPane(Views.SearchView.name());
    }
}
