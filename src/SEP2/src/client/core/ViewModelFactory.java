package client.core;

import client.model.CreateBandModel;
import client.viewModel.*;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private LoginViewModel logInViewModel;
    private RegisterViewModel registerViewModel;
    private RegisterOrganizerViewModel registerOrganizerViewModel;
    private RegisterMusicianViewModel registerMusicianViewModel;
    private UserViewModel userViewModel;
    private OrganizerProfileViewModel organizerProfileViewModel;
    private SideBarViewModel sideBarViewModel;
    private MusicianViewModel musicianViewModel;
    private MainViewModel mainViewModel;
    private CreateBandViewModel createBandViewModel;
    private SearchBandViewModel searchBandViewModel;
    private SearchMusicianViewModel searchMusicianViewModel;
    private SearchOrganizerViewModel searchOrganizerViewModel;
    private BandProfileViewModel bandProfileViewModel;
    private BandAboutUsViewModel bandAboutUsViewModel;
    private MyBandsViewModel myBandsViewModel;
    private BandItemViewModel bandItemViewModel;
    private InviteToBandViewModel inviteToBandViewModel;
    private BandMembersViewModel bandMembersViewModel;

    /**
     * The constructor of ViewModelFactory, which creates all possible view models and assigns passed model factory to the
     * local model Factory
     * @param modelFactory is passed to be assigned to the local variable
     */
    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        logInViewModel = new LoginViewModel(modelFactory.getLoginModel());
        registerViewModel = new RegisterViewModel(modelFactory.getRegisterModel(), modelFactory.getCityModel());
        registerOrganizerViewModel = new RegisterOrganizerViewModel(modelFactory.getRegisterOrganizerModel());
        registerMusicianViewModel = new RegisterMusicianViewModel(modelFactory.getRegisterMusicianModel());
        userViewModel = new UserViewModel();
        sideBarViewModel = new SideBarViewModel(modelFactory.getOrganizerProfileModel());
        musicianViewModel = new MusicianViewModel();
        mainViewModel = new MainViewModel();
        createBandViewModel = new CreateBandViewModel(modelFactory.getCreateBandModel(), modelFactory.getCityModel(), modelFactory.getGenreModel());
        searchBandViewModel = new SearchBandViewModel(modelFactory.getSearchBandModel(), modelFactory.getGenreModel(), modelFactory.getCityModel());
        CreateBandModel createBandModel = modelFactory.getCreateBandModel();
        createBandViewModel = new CreateBandViewModel(createBandModel, modelFactory.getCityModel(), modelFactory.getGenreModel());
        bandProfileViewModel = new BandProfileViewModel(modelFactory.getBandModel());
        bandAboutUsViewModel = new BandAboutUsViewModel();
        myBandsViewModel = new MyBandsViewModel(modelFactory.getBandModel());
        bandItemViewModel = new BandItemViewModel();
        inviteToBandViewModel = new InviteToBandViewModel(modelFactory.getInviteToBandModel());
        bandMembersViewModel = new BandMembersViewModel(modelFactory.getBandModel());
    }

    public BandMembersViewModel getBandMembersViewModel() {
        return bandMembersViewModel;
    }

    public BandItemViewModel getBandItemViewModel() {
        return bandItemViewModel;
    }

    public BandAboutUsViewModel getBandAboutUsViewModel() {
        return bandAboutUsViewModel;
    }

    public BandProfileViewModel getBandProfileViewModel() {
        return bandProfileViewModel;
    }

    public CreateBandViewModel getCreateBandViewModel()
    {
        return createBandViewModel;
    }

    public MainViewModel getMainViewModel()
    {
        return mainViewModel;
    }

    public MusicianViewModel getMusicianViewModel() {
        return musicianViewModel;
    }

    public SideBarViewModel getSideBarViewModel() {
        return sideBarViewModel;
    }

    /**
     * getter which returns LogInViewModel
     * @return instance of the LoginViewModel
     */
    public LoginViewModel getLogInViewModel() {
        return logInViewModel;
    }

    /**
     * getter which returns RegisterViewModel
     * @return instance of the RegisterViewModel
     */
    public RegisterViewModel getRegisterViewModel() {
        return registerViewModel;
    }

    public MyBandsViewModel getMyBandsViewModel() {
        return myBandsViewModel;
    }

    /**
     * getter which returns RegisterOrganizerViewModel
     * @return instance of the RegisterOrganizerViewModel
     */
    public RegisterOrganizerViewModel getRegisterOrganizerViewModel()
    {
        return registerOrganizerViewModel;
    }

    /**
     * getter which returns ModelFactory
     * @return instance of the ModelFactory
     */
    public ModelFactory getModelFactory() {
        return modelFactory;
    }

    /**
     * getter which returns RegisterMusicianViewModel
     * @return instance of the RegisterMusicianViewModel
     */
    public RegisterMusicianViewModel getRegisterMusicianViewModel() {
        return registerMusicianViewModel;
    }

    /**
     * getter which returns UserViewModel
     * @return instance of the UserViewModel
     */
    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    /**
     * getter which returns OrganizerProfileViewModel
     * @return instance of the OrganizerProfileViewModel
     */
    public OrganizerProfileViewModel getOrganizerProfileViewModel() {
        if (organizerProfileViewModel == null)
            organizerProfileViewModel = new OrganizerProfileViewModel(modelFactory.getOrganizerProfileModel(), modelFactory.getSearchOrganizerModel());
        return organizerProfileViewModel;
    }

    public SearchBandViewModel getSearchBandViewModel() {
        if (searchBandViewModel == null){
            searchBandViewModel = new SearchBandViewModel(modelFactory.getSearchBandModel(), modelFactory.getGenreModel(), modelFactory.getCityModel());
        }
        return searchBandViewModel;
    }

    public SearchMusicianViewModel getSearchMusicianViewModel() {
        if (searchMusicianViewModel == null){
            searchMusicianViewModel = new SearchMusicianViewModel(modelFactory.getSearchMusicianModel());
        }
        return searchMusicianViewModel;
    }

    public SearchOrganizerViewModel getSearchOrganizerViewModel() {
        if (searchOrganizerViewModel == null){
            searchOrganizerViewModel = new SearchOrganizerViewModel(modelFactory.getSearchOrganizerModel(), modelFactory.getCityModel());
        }
        return searchOrganizerViewModel;
    }

    public InviteToBandViewModel getInviteToBandViewModel() {
        return inviteToBandViewModel;
    }
}
