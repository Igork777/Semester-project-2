package client.networking;

import shared.networking.SearchBandServer;
import shared.wrappers.Band;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class SearchBandClientImpl implements SearchBandClient
{
	private SearchBandServer searchBandServer;

	/**
	 * Constructor establishing a connection to the search band server. The client will
	 * makes a request to search for bands based on specified criteria.
	 */
	public SearchBandClientImpl()
	{
		try
		{
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			this.searchBandServer = (SearchBandServer) registry.lookup("SearchBandServer");
		}
		catch (RemoteException | NotBoundException e)
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
		try
		{
			return searchBandServer.search(band);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
