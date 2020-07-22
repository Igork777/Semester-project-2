package client.core;

import client.model.*;

public class ModelFactory {
    private ClientFactory clientFactory;
    private LoginModel loginModel;
    private RegisterModel registerModel;
    private RegisterOrganizerModel registerOrganizerModel;
    private RegisterMusicianModel registerMusicianModel;
    private CreateBandModel createBandModel;
    private CityModel cityModel;
    private GenreModel genreModel;
    private OrganizerProfileModel organizerProfileModel;
    private SearchBandModel searchBandModel;
    private SearchMusicianModel searchMusicianModel;
    private SearchOrganizerModel searchOrganizerModel;
    private BandModel bandModel;
    private InviteToBandModel inviteToBandModel;


    /**
     * Constructor for ModelFactory, which creates all the possible models
     * Also, assigns passed clientFactory variable to the local variable
     *
     * @param clientFactory argument to be assigned to the local variable
     */
    public ModelFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        loginModel = new LoginModelImpl(clientFactory.getLoginClient());
        registerModel = new RegisterModelImpl(clientFactory.getRegisterClient());
        registerOrganizerModel = new RegisterOrganizerModelImpl(clientFactory.getRegisterOrganizerClient());
        registerMusicianModel = new RegisterMusicianModelImpl(clientFactory.getRegisterMusicianClient());
        createBandModel = new CreateBandModelImpl(clientFactory.getCreateBandClient());
        cityModel = new CityModelImpl(clientFactory.getCityClient());
        genreModel = new GenreModelImpl(clientFactory.getGenreClient());
        organizerProfileModel = new OrganizerProfileModelImpl(clientFactory.getOrganizerProfileClient());
        searchMusicianModel = new SearchMusicianModelImpl(clientFactory.getMusicianSearchClient());
        searchOrganizerModel = new SearchOrganizerModelImpl(clientFactory.getSearchOrganizerClient());
        searchBandModel = new SearchBandModelImpl(clientFactory.getSearchBandClient());
        bandModel = new BandModelImpl(clientFactory.getBandClient());
        inviteToBandModel = new InviteToBandModelImpl(clientFactory.getInviteToBandClient());

    }

    public BandModel getBandModel() {
        return bandModel;
    }

    public CityModel getCityModel() {
        return cityModel;
    }

    public GenreModel getGenreModel() {
        return genreModel;
    }

    public CreateBandModel getCreateBandModel() {

        return createBandModel;

    }

    /**
     * getter of the clientFactory
     *
     * @return instance of the clientFactory
     */
    public ClientFactory getClientFactory() {
        return clientFactory;
    }

    /**
     * getter of the loginModel
     *
     * @return instance of the loginModel
     */
    public LoginModel getLoginModel() {
        return loginModel;
    }

    /**
     * getter of the registerModel
     *
     * @return instance of the registerModel
     */
    public RegisterModel getRegisterModel() {
        return registerModel;
    }

    /**
     * getter of the registerOrganizerModel
     *
     * @return instance of the registerOrganizerModel
     */
    public RegisterOrganizerModel getRegisterOrganizerModel() {
        return registerOrganizerModel;
    }

    /**
     * getter of the registerMusicianModel
     *
     * @return instance of the registrationMusicianModel
     */
    public RegisterMusicianModel getRegisterMusicianModel() {
        return registerMusicianModel;
    }

    /**
     * getter of the organizerProfileModel
     *
     * @return instance of the organizerProfileModel
     */
    public OrganizerProfileModel getOrganizerProfileModel() {

        return organizerProfileModel;
    }

    public SearchBandModel getSearchBandModel() {
        return searchBandModel;
    }

    public client.model.SearchMusicianModel getSearchMusicianModel() {
        return searchMusicianModel;
    }

    public SearchOrganizerModel getSearchOrganizerModel() {
        return searchOrganizerModel;
    }

    public InviteToBandModel getInviteToBandModel() {
        return inviteToBandModel;
    }
}
