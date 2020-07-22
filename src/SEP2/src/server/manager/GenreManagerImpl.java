package server.manager;

import server.database.GenreDBAccess;
import server.database.GenreDBAccessImpl;

import java.util.ArrayList;

public class GenreManagerImpl implements GenreManager
{
    private GenreDBAccess genreDBAccess;

    /**
     * Constructor initializing the Genre database access
     */
    public GenreManagerImpl() {
        genreDBAccess = new GenreDBAccessImpl();
    }

    /**
     * Method which retrieves all the genres for the combobox from the database
     * @return ArrayList of genres
     */

    @Override
    public ArrayList<String> getGenres() {
        return genreDBAccess.getGenres();
    }

    /**
     * Method which retrieves the genre id by the name of the genre from the database
     * @param genreName - name of the genre
     * @return id of the genre
     */
    @Override public int getGenreIdByName(String genreName)
    {
        return genreDBAccess.getGenreIdByName(genreName);
    }
}
