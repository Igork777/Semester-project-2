package client.model;

import client.networking.SearchOrganizerClient;
import shared.wrappers.Organizer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class SearchOrganizerModelImpl implements SearchOrganizerModel
{
    private SearchOrganizerClient searchOrganizerClient;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SearchOrganizerModelImpl(SearchOrganizerClient client) {
        searchOrganizerClient = client;
    }

@Override
    public ArrayList<Organizer> search(Organizer organizer) {
        return searchOrganizerClient.searchOrganizer(organizer);
    }

    @Override
    public void organizerProfileOf(int orgId) {
        support.firePropertyChange("SearchingForOrganizer", null, orgId);
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
}
