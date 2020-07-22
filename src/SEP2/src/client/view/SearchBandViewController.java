package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.BandItemViewModel;
import client.viewModel.SearchBandViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.utils.Views;
import shared.wrappers.Band;

public class SearchBandViewController extends ViewController
{
	@FXML private TableView<Band> tableView;
	@FXML private TableColumn<Band,String> nameCol;
	@FXML private TableColumn<Band, String> cityCol;
	@FXML private TextField bandNameTextField;
	@FXML private ComboBox<String> regionComboBox;
	@FXML private ComboBox<String> genreComboBox;

	private SearchBandViewModel searchBandViewModel;
	private BandItemViewModel bandItemViewModel;
	private ViewHandler viewHandler;
	boolean isFirstTimeOpened = true;

	@Override public void init(ViewModelFactory viewModelFactory,
			ViewHandler viewHandler)
	{
		this.viewHandler = viewHandler;
		this.searchBandViewModel = viewModelFactory.getSearchBandViewModel();
		this.bandItemViewModel = viewModelFactory.getBandItemViewModel();
		bandNameTextField.textProperty().bindBidirectional(searchBandViewModel.bandNameProperty());
		regionComboBox.valueProperty().bindBidirectional(searchBandViewModel.regionProperty());
		genreComboBox.valueProperty().bindBidirectional(searchBandViewModel.genreProperty());
		if(isFirstTimeOpened)
		{
			genreComboBox.getItems().add(0, null);
			regionComboBox.getItems().add(0,null);
			genreComboBox.getItems().addAll(searchBandViewModel.genresForComboBox());
			regionComboBox.getItems().addAll(searchBandViewModel.getRegionsForComboBox());
		}
		nameCol.setCellValueFactory(new PropertyValueFactory<>("nameOfTheBand"));
		cityCol.setCellValueFactory(new PropertyValueFactory<>("cityName"));
		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2)
			{
				bandItemViewModel.feed(tableView.getSelectionModel().getSelectedItem().getNameOfTheBand());
				viewHandler.viewToPane(Views.BandProfile.name());
			}
		});
	}



	public void search()
	{
		tableView.getItems().clear();
		searchBandViewModel.search();
		showResults();
	}

    private void showResults() {
        ObservableList<Band> list = searchBandViewModel.listOfResults();
        tableView.setItems(list);
    }
}

