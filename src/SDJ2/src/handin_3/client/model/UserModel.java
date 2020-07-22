package handin_3.client.model;

import handin_3.shared.Subject;


public interface UserModel extends Subject {
    void createNewUser(String nickname);
}
