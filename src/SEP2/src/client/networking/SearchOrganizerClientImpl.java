package client.networking;

import shared.networking.SearchOrganizerServer;
import shared.wrappers.Organizer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class SearchOrganizerClientImpl implements SearchOrganizerClient, Remote
{
    private SearchOrganizerServer searchOrganizerServer;

    public SearchOrganizerClientImpl() {
        try
        {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.searchOrganizerServer = (SearchOrganizerServer) registry.lookup("SearchOrganizerServer");
        }
        catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Organizer> searchOrganizer(Organizer organizer) {
        try {
            return searchOrganizerServer.searchOrganizer(organizer);
        }
        catch (RemoteException e) {
            System.out.println("Could not connect to the server");
            return null;
        }
    }
}
