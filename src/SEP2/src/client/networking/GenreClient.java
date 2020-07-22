package client.networking;

import java.util.ArrayList;

public interface GenreClient
{
    /**
     * Method which retrieves all the genres for the combobox from the database
     * @return ArrayList of genres
     */
    ArrayList<String> getGenres();

    /**
     * Method which retrieves the genre id by the name of the genre from the database
     * @param genreName - name of the genre
     * @return id of the genre
     */
    int getGenreIdByName(String genreName);
}
