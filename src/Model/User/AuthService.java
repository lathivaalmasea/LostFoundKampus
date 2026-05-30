package Model.User;

import java.util.Optional;

public interface AuthService {

    Optional<ModelUser> authenticate(String username, String password);
}
