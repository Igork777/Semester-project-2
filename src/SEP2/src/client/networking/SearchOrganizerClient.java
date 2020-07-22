package client.networking;

import shared.wrappers.Organizer;

import java.util.ArrayList;

public interface SearchOrganizerClient
{
    ArrayList<Organizer> searchOrganizer(Organizer organizer);
}
