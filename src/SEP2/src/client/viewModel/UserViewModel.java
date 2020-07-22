package client.viewModel;

import client.model.LocalStorageImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserViewModel
{
    private StringProperty name;
    private StringProperty city;
    private StringProperty dateOfBirth;
    private StringProperty region;
    private LocalStorageImpl localStorage;

    /**
     * Constructor, which creates string property and initialization
     * of the localStorageImpl variable
     */
    public UserViewModel()
    {
        name = new SimpleStringProperty();
        city = new SimpleStringProperty();
        dateOfBirth = new SimpleStringProperty();
        region = new SimpleStringProperty();
        localStorage = LocalStorageImpl.getInstance();
    }

    /**
     * Method, which sets value to the properties from the local Storage
     */
    public void feed()
    {
        name.setValue(localStorage.getCurrentUser().getNickname());
        city.setValue(localStorage.getCurrentUser().getCity());
        dateOfBirth.setValue(""+localStorage.getCurrentUser().getDateOfBirth());
        region.setValue(localStorage.getCurrentUser().getRegion());
    }

    /**
     * getter of the Name String property
     * @return NameStringProperty
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * getter of the City String property
     * @return CityStringProperty
     */
    public StringProperty cityProperty() {
        return city;
    }

    /**
     * getter of the Date of birth String property
     * @return DateOfBirthString property
     */
    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    /**
     * getter of the Regions String property
     * @return RegionString property
     */
    public StringProperty regionProperty() {
        return region;
    }
}
