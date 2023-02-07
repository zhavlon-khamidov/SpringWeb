package kg.edu.alatoo.springWeb.repos;

import kg.edu.alatoo.springWeb.modules.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Set<Author> findAll();
}
