package kg.edu.alatoo.spring_web.services;

import kg.edu.alatoo.spring_web.exceptions.UsernameAlreadyExistsException;
import kg.edu.alatoo.spring_web.modules.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User createUser(User user) throws UsernameAlreadyExistsException;

    User createUserWithRoles(User user, String... roles) throws UsernameAlreadyExistsException;

    User updateUser(Long id, User user);

    User updateUser(String username, User user);

    void deleteUser(String username);

    boolean userExists(String username);
}
