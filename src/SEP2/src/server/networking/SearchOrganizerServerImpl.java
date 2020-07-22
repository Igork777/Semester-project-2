package server.networking;

import server.manager.SearchOrganizerManager;
import shared.networking.SearchOrganizerServer;
import shared.wrappers.Organizer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SearchOrganizerServerImpl implements SearchOrganizerServer
{

    private SearchOrganizerManager searchOrganizerManager;

    public SearchOrganizerServerImpl(SearchOrganizerManager searchOrganizerManager, Registry registry) {
        this.searchOrganizerManager = searchOrganizerManager;
        try {
            UnicastRemoteObject.exportObject(this, 0);
            registry.bind("SearchOrganizerServer", this);
            System.out.println("Search organizer server started!");
        }
        catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException("Search organizer server failed to start");
        }
    }

    @Override
    public ArrayList<Organizer> searchOrganizer(Organizer organizer) {
        return searchOrganizerManager.searchOrganizer(organizer);
    }
}
