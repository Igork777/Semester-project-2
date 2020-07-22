package shared.utils;

/**
 * Class which stores name of the song and artist inside
 */
public class Song
{
    private String name,artist ;

    /**
     * Constructor which initial fields name and artist
     * @param name parameter, which value will be assigned to the field name
     * @param artist parameter, which value will be assigned to the field artist
     */
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    /**
     * Getter, which returns the name of the song
     * @return value of the field name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter, which returns the name of the artist
     * @return value of the field artist
     */
    public String getArtist() {
        return artist;
    }
}
