package server;

import server.manager.*;
import server.networking.*;
import shared.networking.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunServer {
    /**
     * This method create the registry with the registry wth the port 1099. It is important. Afterwards all the servers are created.
     *
     * @param args arguments from the command prompt
     */
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(1099);
        RegisterServer registerServer = new RegisterServerImpl(new RegisterManagerImpl(), registry);
        LocalStorageServer localStorage = new LocalStorageServerImpl(new LocalStorageManagerImpl(), registry);
        CityServer cityServer = new CityServerImpl(new CityManagerImpl(), registry);
        GenreServer genreServer = new GenreServerImpl(new GenreManagerImpl(), registry);
        LoginServer loginServer = new LogInServerImpl(new LoginManagerImpl(), registry);
        RegisterMusicianServer registerMusicianServer = new RegisterMusicianServerImpl(new RegisterMusicianManagerImpl(), registry);
        RegisterOrganizerServer registerOrganizerServer = new RegisterOrganizerServerImpl(new RegisterOrganizerManagerImpl(), registry);
        OrganizerProfileServer organizerProfileServer = new OrganizerProfileServerImpl(new OrganizerProfileManagerImpl(), registry);
        CreateBandServer createBandServer = new CreateBandServerImpl(new CreateBandManagerImpl(), registry);
        SearchBandServer searchBandServer = new SearchBandServerImpl(new SearchBandManagerImpl(), registry);
        MusicianSearchServer musicianSearchServer = new MusicianSearchServerImpl(new MusicianSearchManagerImpl(), registry);
        SearchOrganizerServer searchOrganizerServer = new SearchOrganizerServerImpl(new SearchOrganizerManagerImpl(), registry);
        BandServer bandServer = new BandServerImpl(new BandManagerImpl(), registry);
        InviteToBandServer inviteToBandServer = new InviteToBandServerImpl(new InviteToBandManagerImpl(), registry);
    }
}