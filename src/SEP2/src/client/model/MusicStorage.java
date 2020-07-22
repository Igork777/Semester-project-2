package client.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Pair;
import shared.utils.Song;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class which stores and changes soundtracks
 * All the fields and methods in this class are static for easier access to them
 * In this class we use Singleton pattern, because we need only one instance of the MusicStorage
 */
public class MusicStorage {
    private static MusicStorage musicStorage;



    /**
     * ArrayList of pairs, which store information about song and author in the first pair "Song" and object Media with
     * the path to the song. Object Media is needed in order to play the song
     */
    private ArrayList<Pair<Song, Media>> songs;
    /**
     * Field which represents the condition of the mediaplayer. This is needed when we change views and we want to move player
     * in the same condition to another window
     */
    private boolean isPlayerOn = false;
    /**
     * index of playing soundtrack. This is need in order to retrieve data about name of the song and artist
     */
    private int index;
    /**
     * Actual variable of mediaPlayer
     */
    private MediaPlayer mediaPlayer = null;

    /**
     *      Firstly, the mutual part of the for all the songs is created
     *      Secondly, Arraylist with pairs Song and Media fills
     *      As a parameter every media takes mutual part for all the songs, and adds unique part.
     *      As a result, object Media has the correct path as a parameter
     *      index is chosen randomly, that means that random soundtrack puts in the mediaplayer
     */
    private MusicStorage()
    {
        songs = new ArrayList<>();
        Path currentRelativePath = Paths.get("");
        String s = (currentRelativePath.toAbsolutePath().toString()).replace("\\", "/");
        String path = "file:///" + s + "/src/SEP2/src/music/";
        songs.add(new Pair<>(new Song("Electrical War", "BeatHanger"), new Media(path + "BeatHanger-Electrical_War.wav")));
        songs.add(new Pair<>(new Song("The Hypnotist", "BeatHanger"), new Media(path + "BeatHanger-The_Hypnotist.wav")));
        songs.add(new Pair<>(new Song("Don't stay", "Back On Earth"), new Media(path + "Back_On_Earth_-_Don_t_Stay.mp3")));
        songs.add(new Pair<>(new Song("Smells Like Teen Spirit", "Nirvana"), new Media(path + "Nirvana-Smells_Like_Teen_Spirit.mp3")));
        songs.add(new Pair<>(new Song("Something Happened", "Paper Clips"), new Media(path + "Paper_Clips_-_Something_Happened.mp3")));
        songs.add(new Pair<>(new Song("Cold Inside", "Rude"), new Media(path + "Rude_-_Cold_Inside.mp3")));
        songs.add(new Pair<>(new Song("01 Stormwings", "Strontium"), new Media(path + "Strontium_-_01_Stormwings.mp3")));
        songs.add(new Pair<>(new Song("Waste My Time", "The Devil Music Co."), new Media(path + "The_Devil_Music_Co._-_Waste_My_Time.mp3")));
        Random rand = new Random();
        index = rand.nextInt(rand.nextInt((songs.size() - 1) + 0) + 1) + 0;
        setMedia(songs.get(index).getValue());
    }

    public Pair<Song, Media> getActualSong()
    {
        return songs.get(index);
    }

    /**
     * This method checks if MusicStorage was already created. If it wasn't new database is created, otherwise already creted instance is returned. It is useful, because we will not create a lot of unused objects with the same functionality
     * @return this MusicStorage
     */
    public synchronized static MusicStorage getInstance()
    {
        if(musicStorage == null)
        {
            musicStorage = new MusicStorage();
        }
        return musicStorage;
    }




    /**
     * The methods sets new soundtrack for playing
     *
     * @param media soundtrack that has to be executed
     */
    private void setMedia(Media media) {
        mediaPlayer = new MediaPlayer(media);
    }

    /**
     * Method, which returns mediaplayer
     *
     * @return mediaplayer
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }


    /**
     * Getter for the ArrayList songs
     *
     * @return returns all the songs from the storage
     */
    public  ArrayList<Pair<Song, Media>> getSongs() {
        return songs;
    }

    /**
     * returns true or false depending on condition of the player
     *
     * @return boolean variable, which determines the condition of the mediaplayer
     */
    public boolean IsPlayerOn() {
        return isPlayerOn;
    }


    /**
     * Method changes the condition of the player to turned on
     */
    public void turnOnPlayer() {
        isPlayerOn = true;
    }

    /**
     * Method changes the condition of the player to turned off
     */
    public void turnOffPlayer() {
        isPlayerOn = false;
    }

    /**
     * Method launches next song and changes the index
     * If there is the last song in the soundtrack -> first song is put in the mediaplayer
     */
    public void incrementIndex() {
        if (index == songs.size() - 1) {
            index = 0;
        } else {
            index++;
        }
        setMedia(songs.get(index).getValue());
    }

    /**
     * Method launches previous song and changes the index
     * If there is the first song in the soundtrack -> last song is put in the mediaplayer
     */
    public void decrementIndex() {
        if (index == 0) {
            index = songs.size() - 1;
        } else {
            index--;
        }
        setMedia(songs.get(index).getValue());
    }

    /**
     * Getter of the index of the song
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }
}
