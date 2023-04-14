package kg.edu.alatoo.spring_web.controllers;

import kg.edu.alatoo.spring_web.modules.Book;
import kg.edu.alatoo.spring_web.repos.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller + ResponseBody = RestController
@Controller
public class MainController {

    private final BookRepository bookRepository;

    public MainController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping({"/","/index"})
    public String index(Model model) {

        model.addAttribute("word", "Hello World!");
        model.addAttribute("books", bookRepository.findAll());

        return "main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "<h1>Welcome admin</h1>";
    }

    @GetMapping("/isbn/{isbn}")
    @ResponseBody
    public Book getByIsbn(@PathVariable(name = "isbn") String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

}
