package server.networking;

import server.manager.GenreManager;
import shared.networking.GenreServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GenreServerImpl implements GenreServer {
    private GenreManager genreManager;

    /**
     * Constructor which creates a server which will communicate with the client
     * who asks to retrieve the list of genres or to get genre id by specifying its name
     * @param genreManager - manager for the retrieval of information about genres
     * @param registry - registry of the servers on the RMI port
     */
    public GenreServerImpl(GenreManager genreManager, Registry registry)
    {
        this.genreManager = genreManager;
        try {
            UnicastRemoteObject.exportObject(this, 0);
            registry.bind("GenreServer", this);
            System.out.println("Genre Server started");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which retrieves all the genres for the combobox from the database
     * @return ArrayList of genres
     */
    @Override
    public ArrayList<String> getGenres() {
        return genreManager.getGenres();
    }

    /**
     * Method which retrieves the genre id by the name of the genre from the database
     * @param genreName - name of the genre
     * @return id of the genre
     */
    @Override public int getGenreIdByName(String genreName)
    {
        return genreManager.getGenreIdByName(genreName);
    }
}
