package client.model;

import client.networking.CreateBandClient;
import shared.wrappers.Band;
import shared.utils.Checker;



public class CreateBandModelImpl implements CreateBandModel{
    private CreateBandClient client;
    private Checker checker;

    public CreateBandModelImpl(CreateBandClient client) {
        this.client = client;
        checker = new Checker();
    }

    @Override
    public String create(Band band)
    {
        if(band.getNameOfTheBand() == null || band.getNameOfTheBand().equals(""))
        {
            return "Name of the band can't be empty";
        }
         if (band.getNameOfTheBand().replace(" ", "").length() < Checker.MIN_LENGTH_NAME)
        {
            return "Name of the band is too short";
        }
        if (band.getNameOfTheBand().length() > Checker.MAX_LENGTH_NAME)
        {
            return "Name is too long!";
        }

        if(band.getDate() == null){
            return "Please input the foundation date";
        }
        if(((band.getEmail() == null || band.getEmail().equals(""))) && ((band.getTelephone() == null || (band.getTelephone().equals("")))))
        {
            return "Please input email or telephone number";
        }
        if((band.getEmail() != null && !(band.getEmail().equals(""))) && !(checker.isValidEmail(band.getEmail())))
        {
                return "Email is incorrect";
        }
        if((band.getTelephone() != null && !(band.getTelephone().equals(""))) && !(checker.isValidTelNo(band.getTelephone())))
        {
                return "Telephone number is incorrect";
        }
        if(band.getGenreId() < 1){
            return "Please select genre";
        }

        if(band.getCityId() < 1){
            return "Please select your city";
        }
        if(!(checker.isValidBio(band.getBio())))
        {
            return "Biography is too long!";
        }
        String responseOnCreationTheBand = client.create(band);
        return responseOnCreationTheBand;
    }

}
