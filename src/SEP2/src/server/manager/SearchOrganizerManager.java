package server.manager;

import shared.wrappers.Organizer;

import java.util.ArrayList;

public interface SearchOrganizerManager
{
    /**
     * Retrieve a list of Organizers based upon specified criteria
     * @param organizer searched for by client
     * @return list of organizers satisfying the criteria
     */
    ArrayList<Organizer> searchOrganizer(Organizer organizer);
}
