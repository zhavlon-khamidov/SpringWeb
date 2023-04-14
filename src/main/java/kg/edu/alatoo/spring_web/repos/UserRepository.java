package kg.edu.alatoo.spring_web.repos;

import kg.edu.alatoo.spring_web.modules.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);
}
