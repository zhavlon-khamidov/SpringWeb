package kg.edu.alatoo.spring_web.repos;


import kg.edu.alatoo.spring_web.modules.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByIsbn(String isbn);
}
