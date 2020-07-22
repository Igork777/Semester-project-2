package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.BandProfileViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import shared.utils.Views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class BandProfileController extends ViewController {
    @FXML
    public Label manageId;
    @FXML
    private Label postsId;
    @FXML
    private Label galleryId;
    @FXML
    private Label membersId;
    @FXML
    private Label aboutUsId;
    @FXML
    private Label nameOfTheBandId;
    @FXML
    private Circle circleId;
    @FXML
    private AnchorPane contentBandProfile;
    @FXML
    private Label settingOrLeave;
    private ArrayList<Label> navigationBar;
    private ViewModelFactory viewModelFactory;
    private BandProfileViewModel bandProfileViewModel;
    private ViewHandler viewHandler;
    private boolean isViewedByAdmin;

    private void navigationBarHighlight(Label label) {
        for (Label l : navigationBar) {
            if (l == label) {
                l.setStyle("-fx-text-fill: orangered");
                l.setDisable(true);
            } else {
                l.setStyle("-fx-text-fill: black");
                l.setDisable(false);
            }
        }
    }
    public void onPostClicked(MouseEvent mouseEvent) {
        navigationBarHighlight(postsId);
    }

    public void onGalleryClicked(MouseEvent mouseEvent) {
        navigationBarHighlight(galleryId);
    }

    private Object getController(Node node)
    {
        Object controller = null;
        do {
            controller = node.getUserData();
            node = node.getParent();
        } while (controller == null && node != null);
        return controller;
    }

    public void onAboutUsClicked(MouseEvent mouseEvent) {
        navigationBarHighlight(aboutUsId);
        Node node;
        try {

            node = (Node) FXMLLoader.load(getClass().getResource("BandAboutUs.fxml"));
            BandAboutUsController bandAboutUsController = (BandAboutUsController) getController(node);
            contentBandProfile.getChildren().clear();
            contentBandProfile.getChildren().setAll(node);
            bandAboutUsController.init(viewModelFactory, viewHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onMembersClicked(MouseEvent mouseEvent)
    {
        navigationBarHighlight(membersId);
        Node node;
        try
        {
            node = (Node)FXMLLoader.load(getClass().getResource("BandMembers.fxml"));
            BandMembersController bandMembersController = (BandMembersController) getController(node);
            contentBandProfile.getChildren().clear();
            contentBandProfile.getChildren().setAll(node);
            bandMembersController.init(viewModelFactory, viewHandler);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onSettingsClicked(MouseEvent mouseEvent) {
        navigationBarHighlight(settingOrLeave);
    }

    public void onInviteClicked(MouseEvent mouseEvent) {

    }


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        navigationBar = new ArrayList<>(Arrays.asList(postsId, galleryId, membersId, aboutUsId, manageId, settingOrLeave));
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        bandProfileViewModel = this.viewModelFactory.getBandProfileViewModel();
        circleId.setStroke(Color.BLACK);
        isViewedByAdmin = bandProfileViewModel.getViewer().getUserId() == bandProfileViewModel.getCurrentBand().getBandAdministratorId();

        if (isViewedByAdmin) {
            manageId.setText("Invite");
        } else {
            manageId.setText("Leave");
        }

        nameOfTheBandId.textProperty().bind(bandProfileViewModel.nameOfTheBandPropertyProperty());
        circleId.setFill(new ImagePattern(new Image(getClass().getResource("../../images/band_default.jpg").toString(), false)));
        bandProfileViewModel.feed();
        navigationBarHighlight(null);
        contentBandProfile.getChildren().clear();
    }


    public void onManageClicked(MouseEvent mouseEvent) {
        navigationBarHighlight(manageId);
        if (isViewedByAdmin) { // ADMIN VIEW
            viewHandler.viewToPane(Views.InviteToBandView.name());
            InviteToBandViewController inviteToBandViewController = (InviteToBandViewController) ViewControllerFactory.getViewController(Views.InviteToBandView.name());
            inviteToBandViewController.setCurrentBand(nameOfTheBandId.getText());
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Band Leaving");
            alert.setHeaderText("Band Leaving.");
            alert.setContentText("You are about to leave the band. Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    if (bandProfileViewModel.leaveBand()) {
                        viewHandler.viewToPane(Views.MyBands.name());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
