package client.model;

import shared.wrappers.Organizer;

import java.time.LocalDate;

public interface OrganizerProfileModel
{
    /**
     * Gets organizer info based upon userId
     * @param userId of a user
     * @return null if organizer with this userId does not exist, Organizer if organizer with this userId exists
     */
    Organizer getOrganizerInfoOfUser(int userId);

    /**
     * Calculate current age based on date of birth given
     * @param dateOfBirth LocalDate
     * @return int age
     */
    int calculateAgeFromDateOfBirth(LocalDate dateOfBirth);
}
