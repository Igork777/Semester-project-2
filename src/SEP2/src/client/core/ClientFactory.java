package client.core;

import client.networking.*;

public class ClientFactory {
    private LoginClient loginClient;
    private RegisterClient registerClient;
    private LocalStorageClient localStorageClient;
    private RegisterMusicianClientImpl registerMusicianClient;
    private RegisterOrganizerClient registerOrganizerClient;
    private CreateBandClient createBandClient;
    private CityClient cityClient;
    private GenreClient genreClient;
    private OrganizerProfileClient organizerProfileClient;
    private MusicianSearchClient musicianSearchClient;
    private SearchOrganizerClient searchOrganizerClient;
    private BandClient bandClient;
    private SearchBandClient searchBandClient;
    private InviteToBandClient inviteToBandClient;

    /**
     * Constructor for the ClientFactory, which creates all the possible clients
     */
    public ClientFactory() {
        loginClient = new LoginClientImpl();
        registerClient = new RegisterClientImpl();
        localStorageClient = new LocalStorageClientImpl();
        registerMusicianClient = new RegisterMusicianClientImpl();
        registerOrganizerClient = new RegisterOrganizerClientImpl();
        organizerProfileClient = new OrganizerProfileClientImpl();
        createBandClient = new CreateBandClientImpl();
        bandClient = new BandClientImpl();
        cityClient = new CityClientImpl();
        genreClient = new GenreClientImpl();
        searchBandClient = new SearchBandClientImpl();
        musicianSearchClient = new MusicianSearchClientImpl();
        searchOrganizerClient = new SearchOrganizerClientImpl();
        inviteToBandClient = new InviteToBandClientImpl();
    }

    /**
     * Getter for the SearchBand client
     *
     * @return instance of SearchBandClient
     */
    public SearchBandClient getSearchBandClient() {
        return searchBandClient;
    }

    public BandClient getBandClient() {
        return bandClient;
    }

    public MusicianSearchClient getMusicianSearchClient() {
        return musicianSearchClient;
    }

    /**
     * Getter for the city client
     *
     * @return instance city client
     */
    public CityClient getCityClient() {
        return cityClient;
    }

    /**
     * Getter for the genre client
     *
     * @return instance genre client
     */
    public GenreClient getGenreClient() {
        return genreClient;
    }

    public CreateBandClient getCreateBandClient() {
        return createBandClient;
    }

    /**
     * getter of the LoginClient
     *
     * @return instance of loginClient
     */
    public LoginClient getLoginClient() {
        return loginClient;
    }

    /**
     * getter of the RegisterClient
     *
     * @return instance of the registerClient
     */
    public RegisterClient getRegisterClient() {
        return registerClient;
    }

    /**
     * getter of the LocalStorageClient
     *
     * @return instance of the localStorageClient
     */
    public LocalStorageClient getLocalStorageClient() {
        return localStorageClient;
    }

    /**
     * getter of the registerMusicianClient
     *
     * @return instance of the registerMusicianClient
     */
    public RegisterMusicianClient getRegisterMusicianClient() {
        return registerMusicianClient;
    }

    /**
     * getter of the registerOrganizerClient
     *
     * @return instance of the registerOrganizerClient
     */
    public RegisterOrganizerClient getRegisterOrganizerClient() {
        return registerOrganizerClient;
    }

    /**
     * getter of the organizerProfileClient
     *
     * @return instance of the organizerProfileClient
     */
    public OrganizerProfileClient getOrganizerProfileClient() {
        return organizerProfileClient;
    }

    /**
     * getter of the search organizer client
     *
     * @return instance of the SearchOrganizerClient
     */
    public SearchOrganizerClient getSearchOrganizerClient() {
        return searchOrganizerClient;
    }

    public InviteToBandClient getInviteToBandClient() {
        return inviteToBandClient;
    }
}
