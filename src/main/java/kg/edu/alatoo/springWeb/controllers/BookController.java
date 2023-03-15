package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/addbook")
    public String books() {
        return "main";
    }

    @PostMapping("/addbook")
    public String addBook(Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(name = "sq") String sQuerry) {
        System.out.println("test");
        System.out.println(bookRepository.findAllByTitleContainsOrIsbn(sQuerry, sQuerry));
        return "main";
    }

    @PostMapping("assignbook")
    public String assignbook(@RequestParam Long bookId, @RequestParam Long borrowerId) {

        Book book = bookRepository.findById(bookId).orElseThrow();




        return "redirect:/";
    }
}
