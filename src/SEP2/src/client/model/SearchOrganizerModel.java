package client.model;

import shared.utils.Subject;
import shared.wrappers.Organizer;

import java.util.ArrayList;

public interface SearchOrganizerModel extends Subject
{
    ArrayList<Organizer> search(Organizer organizer);
    void organizerProfileOf(int orgId);
}
