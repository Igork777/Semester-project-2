package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.OrganizerProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import shared.utils.Views;

public class OrganizerProfileController extends ViewController
{
    private ViewHandler viewHandler;
    private OrganizerProfileViewModel organizerProfileViewModel;

    @FXML
    private ImageView profilePicture;
    @FXML
    private Label fullName;
    @FXML
    private Label age;
    @FXML
    private Label city;
    @FXML
    private Label email;
    @FXML
    private Label phoneNumber;
    @FXML
    private Text biography;

    /**
     * Assigns the field variables viewHandler and organizerProfileViewModel.
     * Binds the properties with Appropriate ViewModel.
     * Requests to fetch the organizer info.
     *
     * @param viewModelFactory parameter, in order to have the connection with the model layer
     * @param viewHandler      parameter, in order to change views
     */
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.organizerProfileViewModel = viewModelFactory.getOrganizerProfileViewModel();

        fullName.textProperty().bind(organizerProfileViewModel.fullNameProperty());
        age.textProperty().bind(organizerProfileViewModel.ageProperty());
        city.textProperty().bind(organizerProfileViewModel.cityProperty());
        email.textProperty().bind(organizerProfileViewModel.emailProperty());
        phoneNumber.textProperty().bind(organizerProfileViewModel.phoneNumberProperty());
        biography.textProperty().bind(organizerProfileViewModel.biographyProperty());

        addListeners();
        if (!organizerProfileViewModel.openedBySearch()) {
            fetchOrganizer();
        }
    }

    /**
     * Adds PropertyChangeListeners for a subject
     */
    private void addListeners() {
        organizerProfileViewModel.addListener("OrganizerNotFound",
            (evt) -> viewHandler.viewToPane(Views.UserProfile.name()));
    }

    /**
     * Attempts to fetch organizer relevant information
     */
    private void fetchOrganizer() {
        organizerProfileViewModel.getOrganizerInfo();
    }
}
