package shared.networking;

import shared.wrappers.Organizer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SearchOrganizerServer extends Remote
{
    ArrayList<Organizer> searchOrganizer(Organizer organizer) throws RemoteException;
}
