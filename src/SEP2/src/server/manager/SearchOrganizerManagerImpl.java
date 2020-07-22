package server.manager;

import server.database.SearchOrganizerDBAccess;
import server.database.SearchOrganizerDBImpl;
import shared.wrappers.Organizer;

import java.util.ArrayList;

public class SearchOrganizerManagerImpl implements SearchOrganizerManager
{
    private SearchOrganizerDBAccess searchOrganizerDBAccess;

    /**
     * Constructor creating Database access for searching organizers
     */
    public SearchOrganizerManagerImpl() {
        searchOrganizerDBAccess = new SearchOrganizerDBImpl();
    }

    /**
     * Unwraps the Organizer to check what criteria were filled out and selects a database method - query to execute
     * to retrieve the correct organizers
     * @param organizer searched for by client
     * @return list of organizers satisfying the criteria
     */
    @Override
    public ArrayList<Organizer> searchOrganizer(Organizer organizer) {
        String region = organizer.getRegion();
        String fullName = organizer.getFullName();
        if (region != null && fullName != null) {
            return searchOrganizerDBAccess.searchOrganizerByFullNameAndRegion(fullName, region);
        }
        else if (region != null) {
            return searchOrganizerDBAccess.searchOrganizerByRegion(region);
        }
        else if (fullName != null) {
            return searchOrganizerDBAccess.searchOrganizerByFullName(fullName);
        }
        else {
            return searchOrganizerDBAccess.searchAllOrganizers();
        }
    }
}
