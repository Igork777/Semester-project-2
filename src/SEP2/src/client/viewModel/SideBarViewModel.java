package client.viewModel;

import client.model.LocalStorageImpl;
import client.model.OrganizerProfileModel;
import shared.wrappers.Organizer;

public class SideBarViewModel
{
    private LocalStorageImpl localStorage;
    private OrganizerProfileModel organizerProfileModel;

    public SideBarViewModel(OrganizerProfileModel organizerProfileModel)
    {
        localStorage = LocalStorageImpl.getInstance();
        this.organizerProfileModel = organizerProfileModel;
    }
    public boolean isMusicianRegistered()
    {
        return localStorage.getMusician() == null;
    }

    /**
     * Uses organizer model, to figure out if a user relevant to this client is already registered
     * @return true if organizer is registered for this user; false if organizer is not registered for this user
     */
    public boolean isOrganizerRegistered() {
        Organizer organizer = organizerProfileModel.getOrganizerInfoOfUser(localStorage.getCurrentUser().getUserId());
        return organizer != null;
    }

}
