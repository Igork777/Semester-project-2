package client.model;

import client.networking.OrganizerProfileClient;
import shared.wrappers.Organizer;

import java.time.LocalDate;
import java.time.Period;

public class OrganizerProfileModelImpl implements OrganizerProfileModel
{
    private OrganizerProfileClient organizerProfileClient;

    /**
     * Constructor setting a client to the model
     * @param organizerProfileClient organizer profile client
     */
    public OrganizerProfileModelImpl(OrganizerProfileClient organizerProfileClient) {
        this.organizerProfileClient = organizerProfileClient;
    }

    /**
     * Gets organizer info based upon userId
     * @param userId of a user
     * @return null if organizer with this userId does not exist, Organizer if organizer with this userId exists
     */
    @Override
    public Organizer getOrganizerInfoOfUser(int userId) {
        return organizerProfileClient.getOrganizerInfo(userId);
    }

    /**
     * Calculate current age based on date of birth given
     * @param dateOfBirth LocalDate
     * @return int age
     */
    @Override
    public int calculateAgeFromDateOfBirth(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
