package handIn_2.server.manager;

public class UserManagerImpl implements UserManager {
    String nickname;

    public UserManagerImpl(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getNickName() {
        return nickname;
    }
}
