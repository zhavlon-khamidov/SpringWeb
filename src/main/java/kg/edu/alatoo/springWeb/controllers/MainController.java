package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller + ResponseBody = RestController
@Controller
public class MainController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping({"/",})
    public String main(Model model) {

        System.out.println(bookRepository.findAll());

        model.addAttribute("word", "Hello World!");
        model.addAttribute("books", bookRepository.findAll());

        return "main";
    }

    @GetMapping("/isbn/{isbn}")
    @ResponseBody
    public Book getByIsbn(@PathVariable(name = "isbn") String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

}
