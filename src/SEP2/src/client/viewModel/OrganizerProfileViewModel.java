package client.viewModel;

import client.model.LocalStorageImpl;
import client.model.OrganizerProfileModel;
import client.model.SearchOrganizerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.utils.Subject;
import shared.wrappers.Organizer;
import shared.wrappers.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class OrganizerProfileViewModel implements Subject
{
    private StringProperty fullName;
    private StringProperty age;
    private StringProperty city;
    private StringProperty phoneNumber;
    private StringProperty email;
    private StringProperty biography;
    private User userMap;

    private OrganizerProfileModel organizerProfileModel;
    private LocalStorageImpl localStorage;


    private boolean openedBySearch = false;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructor initiating properties and assigning relevant models
     * @param organizerProfileModel model relevant for organizer view
     */
    public OrganizerProfileViewModel(OrganizerProfileModel organizerProfileModel, SearchOrganizerModel searchOrganizerModel) {
        this.fullName = new SimpleStringProperty();
        this.age = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.phoneNumber = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.biography = new SimpleStringProperty();

        this.organizerProfileModel = organizerProfileModel;
        localStorage = LocalStorageImpl.getInstance();

        searchOrganizerModel.addListener("SearchingForOrganizer", this::searchForOrganizerDetected);
    }

    private void searchForOrganizerDetected(PropertyChangeEvent event) {
        openedBySearch = true;
        Organizer organizer = organizerProfileModel.getOrganizerInfoOfUser((int) event.getNewValue());
        if (organizer == null) {
            triggerOrganizerNotFound();
        }
        else {
            setOrganizerProfileInfo(organizer);
        }
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                openedBySearch = false;
            }
            catch (InterruptedException e) {openedBySearch = false;}
        }).start();
    }

    /**
     * Attempts to retrieve necessary information from local storage and organizer model.
     */
    public void getOrganizerInfo() {
        userMap = localStorage.getCurrentUser();
        int userId = userMap.getUserId();
        Organizer organizer = organizerProfileModel.getOrganizerInfoOfUser(userId);
        if (organizer == null) {
            triggerOrganizerNotFound();
        }
        else {
            setOrganizerProfileInfo(organizer);
        }
    }

    private void triggerOrganizerNotFound() {
        support.firePropertyChange("OrganizerNotFound", null, null);
    }

    /**
     * Sets all information of an organizer after retrieving of the information from model went well
     * @param organizer encapsulated information
     */
    private void setOrganizerProfileInfo(Organizer organizer) {
        fullName.setValue(organizer.getFullName());
        phoneNumber.setValue(organizer.getTelNo());
        email.setValue(organizer.getEmail());
        biography.setValue(organizer.getBiography());
        age.setValue(getAgeFromDateOfBirth(organizer.getDateOfBirth()));
        city.setValue(organizer.getCity());
    }

    /**
     * Requests a calculation of age from date of birth from model
     * @return age parsed to String
     */
    private String getAgeFromDateOfBirth(LocalDate date) {
        int age = organizerProfileModel.calculateAgeFromDateOfBirth(date);
        return Integer.toString(age);
    }

    /**
     * Full name property getter
     * @return StringProperty-fullName
     */
    public StringProperty fullNameProperty() {
        return fullName;
    }

    /**
     * age property getter
     * @return StringProperty-age
     */
    public StringProperty ageProperty() {
        return age;
    }

    /**
     * City property getter
     * @return StringProperty-City
     */
    public StringProperty cityProperty() {
        return city;
    }

    /**
     * Phone number property getter
     * @return StringProperty-Phone number
     */
    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    /**
     * email property getter
     * @return StringProperty-email
     */
    public StringProperty emailProperty() {
        return email;
    }

    /**
     * Biography property getter
     * @return StringProperty-Biography
     */
    public StringProperty biographyProperty() {
        return biography;
    }

    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        if (name == null || name.equals("")) {
            support.addPropertyChangeListener(listener);
        }
        else {
            support.addPropertyChangeListener(name, listener);
        }
    }

    @Override
    public void removeListener(String name, PropertyChangeListener listener) {
        if (name == null || name.equals("")) {
            support.removePropertyChangeListener(listener);
        }
        else {
            support.removePropertyChangeListener(name, listener);
        }
    }

    public boolean openedBySearch() {
        return openedBySearch;
    }
}
