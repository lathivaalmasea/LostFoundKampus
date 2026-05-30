package Model.User;

import java.util.Optional;

public class UserAuthService implements AuthService {

    private final InterfaceDAOUser userDao;

    public UserAuthService(InterfaceDAOUser userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<ModelUser> authenticate(String username, String password) {
        return Optional.ofNullable(userDao.login(username, password));
    }
}
