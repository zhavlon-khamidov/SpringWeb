package kg.edu.alatoo.spring_web.repos;

import kg.edu.alatoo.spring_web.modules.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Set<Author> findAll();
}
