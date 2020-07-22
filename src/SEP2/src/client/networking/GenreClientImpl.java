package client.networking;

import shared.networking.CreateBandServer;
import shared.networking.GenreServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class GenreClientImpl implements GenreClient
{
    private GenreServer genreServer;

    /**
     * Constructor for the client establishing a connection to the server. This clients
     * attempts to retrieve genre related information from the database
     */
    public GenreClientImpl() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.genreServer = (GenreServer) registry.lookup("GenreServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which retrieves all the genres for the combobox from the database
     * @return ArrayList of genres
     */
    @Override
    public ArrayList<String> getGenres() {
        try {
            return genreServer.getGenres();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    /**
     * Method which retrieves the genre id by the name of the genre from the database
     * @param genreName - name of the genre
     * @return id of the genre
     */
    @Override public int getGenreIdByName(String genreName)
    {
        try
        {
            return genreServer.getGenreIdByName(genreName);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
