package client.viewModel;

import client.model.InviteToBandModel;
import client.model.LocalStorageImpl;
import javafx.beans.property.SimpleStringProperty;
import shared.wrappers.Band;
import shared.wrappers.Musician;

public class InviteToBandViewModel {
    private SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
    private LocalStorageImpl localStorage = LocalStorageImpl.getInstance();
    private InviteToBandModel inviteToBandModel;

    public InviteToBandViewModel(InviteToBandModel inviteToBandModel)
    {
        this.inviteToBandModel = inviteToBandModel;
    }


    public SimpleStringProperty simpleStringPropertyProperty() {
        return simpleStringProperty;
    }

    public void inviteToBand(Musician musicianSelected, String nameCurrentBand)
    {
        localStorage.setLocalBandByName(nameCurrentBand);
        Band band = localStorage.getLocalBand();
      if(inviteToBandModel.inviteToBand(musicianSelected, band))
      {
        simpleStringProperty.setValue("Musician " + musicianSelected.getNickname() + " is added successfully to the band " + band.getNameOfTheBand());
      }
      else
      {
          simpleStringProperty.setValue("An error occurred. Musician " + musicianSelected.getNickname() + " is already a member of the band " + band.getNameOfTheBand());
      }
    }


}
