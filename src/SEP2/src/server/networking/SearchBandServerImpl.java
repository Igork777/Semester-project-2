package server.networking;

import server.manager.SearchBandManager;
import shared.networking.SearchBandServer;
import shared.wrappers.Band;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SearchBandServerImpl implements SearchBandServer
{

	private SearchBandManager searchBandManager;

	/**
	 * Constructor which initializes the server the client is calling on.
	 * @param searchBandManager - manager for the searching of bands
	 * @param registry - registry of the servers on the RMI port
	 */
	public SearchBandServerImpl(SearchBandManager searchBandManager, Registry registry)
	{
		try
		{
			UnicastRemoteObject.exportObject(this,0);
			this.searchBandManager = searchBandManager;
			registry.bind("SearchBandServer", this);
			System.out.println("Search band server started!");
		}
		catch (RemoteException | AlreadyBoundException e)
		{
			e.printStackTrace();
		}
	}

/**
 * Method which extracts bands from the database with matching information from the
 * band parameter
 * @param band this parameter contains the search criteria the search method will match
 * @return ArrayList of Band objects which concur to the search condition. These objects contain
 * the name of the band, the city the band usually plays in and bandId of the band
 */
	@Override public ArrayList<Band> search(Band band)
	{
		return searchBandManager.search(band);
	}
}
