package kg.edu.alatoo.springWeb;

import kg.edu.alatoo.springWeb.modules.Author;
import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.modules.Publisher;
import kg.edu.alatoo.springWeb.repos.AuthorRepository;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import kg.edu.alatoo.springWeb.repos.PublisherRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class InitData implements InitializingBean {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public InitData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void afterPropertiesSet() {
        List<Author> authors = new ArrayList<>();
        Author artur = new Author("Artur", "Konandoel");
        authors.add(artur);
        Author djek = new Author("Djek", "London");
        authors.add(djek);
        authorRepository.saveAll(authors);

        List<Book> books = new ArrayList<>();
        books.add(new Book("Sherlock Holms", "3541354").addAuthor(djek));
        books.add(new Book("Database", "43435435").addAuthor(djek).addAuthor(artur));
        books.add(new Book("Alchemist", "54343433").addAuthor(artur));
        System.out.println("Before save: " + books);
        bookRepository.saveAll(books);
        System.out.println("After save: "+ books);


        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher("Springer"));


        publisherRepository.saveAll(publishers);





    }
}
