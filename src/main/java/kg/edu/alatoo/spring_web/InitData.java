package kg.edu.alatoo.spring_web;

import kg.edu.alatoo.spring_web.modules.Author;
import kg.edu.alatoo.spring_web.modules.Book;
import kg.edu.alatoo.spring_web.modules.Publisher;
import kg.edu.alatoo.spring_web.repos.AuthorRepository;
import kg.edu.alatoo.spring_web.repos.BookRepository;
import kg.edu.alatoo.spring_web.repos.PublisherRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class InitData implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(getClass());

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
        log.info("Before save: {}", books);
        bookRepository.saveAll(books);
        log.info("After save: {}", books);


        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher("Springer"));


        publisherRepository.saveAll(publishers);





    }
}
