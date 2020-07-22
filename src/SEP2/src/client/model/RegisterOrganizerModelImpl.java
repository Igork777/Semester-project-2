package client.model;

import client.networking.RegisterOrganizerClient;
import shared.utils.Checker;
import shared.wrappers.Organizer;

public class RegisterOrganizerModelImpl implements RegisterOrganizerModel {

    private RegisterOrganizerClient client;
    private Organizer organizer;
    private Checker checker;

    /**
     * Constructor instantiating the client
     *
     * @param registerOrganizerClient parameter from the client factory
     */

    public RegisterOrganizerModelImpl(RegisterOrganizerClient registerOrganizerClient) {
        this.client = registerOrganizerClient;
        checker = new Checker();
    }

    /**
     * @param fullName  - full name of the organizer
     * @param biography - biography of the organizer
     * @param email     - email of the organizer
     * @param telNo     - telephone number of the organizer
     * @return
     */

    @Override
    public String registerOrganizer(String fullName, String biography, String email, String telNo) {
        if (fullName == null || fullName.equals("") || fullName.replace(" ", "").length() == 0) {
            return "Specify a name!";
        } else if (!(checker.isValidFullName(fullName))) {
            return "Invalid character in name!";
        } else if ((email == null || email.equals("")) && (telNo == null || telNo.equals(""))) {
            return "Specify email, phone, or both!";
        } else if (!(email == null || email.equals("")) && (telNo == null || telNo.equals("")) && !(checker.isValidEmail(email))) {
            return "Invalid email!";
        } else if (!(email == null || email.equals("")) && !(telNo == null || telNo.equals("")) && (checker.isValidEmail(email)) && !(checker.isValidTelNo(telNo))) {
            return "Invalid phone number!";
        } else if (!(email == null || email.equals("")) && !(telNo == null || telNo.equals("")) && !(checker.isValidEmail(email)) && (checker.isValidTelNo(telNo))) {
            return "Invalid email!";
        } else if ((email == null || email.equals("")) && !(telNo == null || telNo.equals("")) && !(checker.isValidTelNo(telNo))) {
            return "Invalid phone number!";
        } else if (!(email == null || email.equals("")) && !(telNo == null || telNo.equals("")) && !(checker.isValidTelNo(telNo)) && !(checker.isValidEmail(email))) {
            return "Invalid phone and mail!";
        } else if (biography == null || biography.equals("") || biography.replace(" ", "").length() == 0) {
            return "Specify a biography!";
        }
          else if(!checker.isValidBio(biography))
        {
            return "Biography is too long!";
        }

        int userId = LocalStorageImpl.getInstance().getCurrentUser().getUserId();
        organizer = new Organizer(fullName, biography, userId, email, telNo);
        return client.registerOrganizer(organizer);
    }
}
