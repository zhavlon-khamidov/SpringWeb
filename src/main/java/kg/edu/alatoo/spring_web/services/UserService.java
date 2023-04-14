package kg.edu.alatoo.spring_web.services;

import kg.edu.alatoo.spring_web.modules.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User createUser(User user);

    User createUserWithRoles(User user, String... roles);

    User updateUser(Long id, User user);

    User updateUser(String username, User user);

    void deleteUser(String username);

    boolean userExists(String username);
}
