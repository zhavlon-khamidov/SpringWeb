package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Controller + ResponseBody = RestController
@RestController
public class MainController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping({"/","/hello"})
    public String hello() {

        System.out.println(bookRepository.findAll());

        return "Hello World";
    }

    @GetMapping("/isbn/{isbn}")
    public Book getByIsbn(@PathVariable(name = "isbn") String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

}
