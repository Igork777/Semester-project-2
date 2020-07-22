package server.database;

import shared.wrappers.Organizer;

import java.util.ArrayList;

public interface SearchOrganizerDBAccess
{
    ArrayList<Organizer> searchOrganizerByFullName(String organizerFullName);
    ArrayList<Organizer> searchOrganizerByRegion(String region);
    ArrayList<Organizer> searchOrganizerByFullNameAndRegion(String organizerFullName, String region);
    ArrayList<Organizer> searchAllOrganizers();
}
