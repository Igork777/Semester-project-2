package client.model;

import client.networking.GenreClient;

import java.util.ArrayList;

public class GenreModelImpl implements GenreModel {
    private GenreClient genreClient;

    /**
     * Constructor assigning the client to the model
     * @param genreClient client for the model
     */
    public GenreModelImpl(GenreClient genreClient) {
        this.genreClient = genreClient;
    }

    /**
     * Method which retrieves all the genres for the combobox from the database
     * @return ArrayList of genres
     */
    @Override
    public ArrayList<String> getGenres() {
        return genreClient.getGenres();
    }

    /**
     * Method which retrieves the genre id by the name of the genre from the database
     * @param genreName - name of the genre
     * @return id of the genre
     */
    @Override public int getGenreIdByName(String genreName)
    {
        return genreClient.getGenreIdByName(genreName);
    }
}
