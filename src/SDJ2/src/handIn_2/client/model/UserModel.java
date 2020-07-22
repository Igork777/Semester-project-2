package handIn_2.client.model;

import handIn_2.shared.Subject;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.prefs.PreferenceChangeListener;

public interface UserModel extends Model
{
    void setNickName(String nickName) throws IOException;
    void requestNewUser(String nickname);
    void requestSetNickname(String nickname);
    String getNickName();
    boolean isNewUser();
}
