package kg.edu.alatoo.springWeb.repos;


import kg.edu.alatoo.springWeb.modules.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByIsbn(String isbn);
}
