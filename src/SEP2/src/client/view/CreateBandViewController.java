package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.CreateBandViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import shared.utils.Views;

public class CreateBandViewController extends ViewController {
    @FXML
    private TextField nameOfTheBandId;
    @FXML
    private TextField emailId;
    @FXML
    private TextField telephoneId;
    @FXML
    private TextArea bioId;
    @FXML
    private DatePicker foundationDateId;
    @FXML
    private ComboBox generId;
    @FXML
    private Label errorNameBand;
    @FXML
    private Label emailError;
    @FXML
    private Label telephoneError;
    @FXML
    private Label cityErrorLabel;
    @FXML
    private Label genreErrorLabel;
    @FXML
    private Label foundationDateErrorLabel;
    @FXML
    private ComboBox cityId;
    @FXML
    private Label errorCreatingBand;
    @FXML
    private Label bioErrorLabel;
    private ViewHandler viewHandler;
    private boolean isFirstTimeCreateBandIsOpened = true;
    private CreateBandViewModel createBandViewModel;


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        createBandViewModel = viewModelFactory.getCreateBandViewModel();
        createBandViewModel.nameOfTheBandProperty().bind(nameOfTheBandId.textProperty());
        createBandViewModel.emailProperty().bind(emailId.textProperty());
        createBandViewModel.telephoneProperty().bind(telephoneId.textProperty());
        createBandViewModel.foundationDateProperty().bind(foundationDateId.valueProperty());
        createBandViewModel.genreProperty().bindBidirectional(generId.valueProperty());
        createBandViewModel.cityProperty().bindBidirectional(cityId.valueProperty());
        createBandViewModel.emailErrorProperty().bindBidirectional(emailError.textProperty());
        createBandViewModel.nameErrorProperty().bindBidirectional(errorNameBand.textProperty());
        createBandViewModel.telephoneErrorProperty().bindBidirectional(telephoneError.textProperty());
        createBandViewModel.errorCreatingBandProperty().bindBidirectional(errorCreatingBand.textProperty());
        createBandViewModel.cityErrorProperty().bindBidirectional(cityErrorLabel.textProperty());
        createBandViewModel.getFoundationDateErrorProperty().bindBidirectional(foundationDateErrorLabel.textProperty());
        createBandViewModel.genreErrorProperty().bindBidirectional(genreErrorLabel.textProperty());
        createBandViewModel.bioProperty().bindBidirectional(bioId.textProperty());
        createBandViewModel.bioError().bindBidirectional(bioErrorLabel.textProperty());

        if (isFirstTimeCreateBandIsOpened)
        {
            generId.getItems().addAll(createBandViewModel.getGenres());
            cityId.getItems().addAll(createBandViewModel.getCities());
            isFirstTimeCreateBandIsOpened = false;
        }
    }

    public void onClickCreate(MouseEvent mouseEvent)
    {


        createBandViewModel.create();
        if (errorCreatingBand.textProperty().getValue().equals("Band created successfully!")) {
            nameOfTheBandId.textProperty().setValue("");
            emailId.textProperty().setValue("");
            telephoneId.textProperty().setValue("");
            foundationDateId.valueProperty().setValue(null);
            generId.valueProperty().setValue(null);
            cityId.valueProperty().setValue(null);
            bioId.textProperty().setValue("");
            errorCreatingBand.textProperty().setValue("");
            viewHandler.viewToPane(Views.BandProfile.name());
        }
    }
}
