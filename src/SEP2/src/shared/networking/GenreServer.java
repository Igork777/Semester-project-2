package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GenreServer extends Remote {


    /**
     * Method which retrieves all the genres for the combobox from the database
     * @return ArrayList of genres
     * @throws RemoteException - RMI exception
     */
    ArrayList<String> getGenres() throws RemoteException;

    /**
     * Method which retrieves the genre id by the name of the genre from the database
     * @param genreName - name of the genre
     * @return id of the genre
     * @throws RemoteException - RMI exception
     */
    int getGenreIdByName(String genreName) throws RemoteException;
}
