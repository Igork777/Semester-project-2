package client.viewModel;

import client.model.MusicStorage;
import javafx.beans.property.SimpleStringProperty;
import shared.utils.SongState;
import shared.utils.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class MainViewModel implements Subject
{
    private SimpleStringProperty songNameProperty;
    private SimpleStringProperty artistNameProperty;
    private MusicStorage musicStorage;
    private PropertyChangeSupport support;

    public MainViewModel()
    {
        support = new PropertyChangeSupport(this);
        songNameProperty = new SimpleStringProperty();
        artistNameProperty = new SimpleStringProperty();
        musicStorage = MusicStorage.getInstance();
    }
    public void init()
    {
        musicStorage.getMediaPlayer().setVolume(0.1);
        songNameProperty.setValue((musicStorage.getSongs().get(musicStorage.getIndex()).getKey().getName()));
        artistNameProperty.setValue(musicStorage.getSongs().get(musicStorage.getIndex()).getKey().getArtist());
        musicStorage.getMediaPlayer().setOnEndOfMedia(() -> {
            musicStorage.incrementIndex();
            playTrack(musicStorage.getIndex());
        });
    }

    /**
     * this method sets song's and author's names based on the index provided
     * also this method starts the song based on the index provided
     * <p>
     * Afterwards, I wrote a lambda expression in order to go to the next song, if previous is finished
     * <p>
     * In the end of the method I set volume and check if previous soundtrack was on or off, when I pushed "nextSong" or "previous song"
     * If, it was on, when I switch to another track  -> new track will start to play instantly. If it wasn't -> then new soundtrack will be prepared but
     * not started
     *
     * @param indexx id of the soundtrack to start
     */
    private void playTrack(int indexx) {
        songNameProperty.setValue(musicStorage.getSongs().get(indexx).getKey().getName());
        artistNameProperty.setValue(musicStorage.getSongs().get(indexx).getKey().getArtist());
        musicStorage.getMediaPlayer().setOnEndOfMedia(() -> {
            musicStorage.incrementIndex();
            playTrack(musicStorage.getIndex());
        });
        musicStorage.getMediaPlayer().setVolume(0.1);
        if (musicStorage.IsPlayerOn()) {
            musicStorage.getMediaPlayer().play();
        } else {
            musicStorage.getMediaPlayer().pause();
        }
    }

    public SimpleStringProperty songNameProperty() {
        return songNameProperty;
    }


    public SimpleStringProperty artistNameProperty() {
        return artistNameProperty;
    }

    public MusicStorage getMusicStorage() {
        return musicStorage;
    }

    public void onClickPlay()
    {
        if (musicStorage.IsPlayerOn()) {
            musicStorage.getMediaPlayer().pause();
            support.firePropertyChange(SongState.PLAY.name(), null , "../../images/play.png" );
            musicStorage.turnOffPlayer();
        } else {
            musicStorage.getMediaPlayer().play();
            support.firePropertyChange(SongState.STOP.name(), null, "../../images/pause.png" );
            musicStorage.turnOnPlayer();
        }
    }

    public void onClickNextSong()
    {
        musicStorage.getMediaPlayer().stop();
        musicStorage.incrementIndex();
        playTrack(musicStorage.getIndex());
    }

    public void onClickPreviousSong() {
        musicStorage.getMediaPlayer().stop();
        musicStorage.decrementIndex();
        playTrack(musicStorage.getIndex());
    }

    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name,listener);
    }

    @Override
    public void removeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name,listener);
    }
}
