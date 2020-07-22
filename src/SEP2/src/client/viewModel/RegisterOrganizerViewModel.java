package client.viewModel;

import client.model.RegisterOrganizerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegisterOrganizerViewModel
{

  private RegisterOrganizerModel registerOrganizerModel;

  private StringProperty fullName;
  private StringProperty biography;
  private StringProperty error;
  private StringProperty telNo;
  private StringProperty email;

  /**
   * Constructor instantiating the properties and model for the organizer registration
   * @param registerOrganizerModel parameter is the model for the organizer registration received from
   * the model factory
   */

  public RegisterOrganizerViewModel(RegisterOrganizerModel registerOrganizerModel)
  {
    this.registerOrganizerModel = registerOrganizerModel;
    //initializing properties
    fullName = new SimpleStringProperty();
    biography = new SimpleStringProperty();
    error = new SimpleStringProperty();
    telNo = new SimpleStringProperty();
    email = new SimpleStringProperty();

  }

  /**
   * Gets full name StringProperty
   * @return StringProperty fullName
   */
  public StringProperty fullNameProperty()
  {
    return fullName;
  }

  /**
   * Gets biography StringProperty
   * @return StringProperty biography
   */

  public StringProperty biographyProperty()
  {
    return biography;
  }

  /**
   * Gets error label StringProperty
   * @return errorLabel StringProperty
   */
  public StringProperty errorProperty()
  {
    return error;
  }

  /**
   * Gets telephone number StringProperty
   * @return Telephone number StringProperty
   */
  public StringProperty telNoProperty()
  {
    return telNo;
  }

  /**
   * Gets email StringProperty
   * @return Email StringProperty
   */

  public StringProperty emailProperty()
  {
    return email;
  }

  /**
   * Method which attempts to register the user as an organizer. Before
   * sending data to the server, several checks are in place to see if the passed data is valid
   * @return boolean true if user was registered as an organizer, false if the user could not be registered
   * as an organizer
   */

  public boolean registerOrganizer()
  {
    String string = registerOrganizerModel.registerOrganizer(fullName.get(), biography.get(), email.get(), telNo.get());
    error.setValue(string);
    return string.equals("Happy organizing!");
  }
}
