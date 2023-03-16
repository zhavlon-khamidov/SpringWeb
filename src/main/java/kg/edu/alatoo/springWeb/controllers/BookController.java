package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import kg.edu.alatoo.springWeb.repos.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookController(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("publishers", publisherRepository.findAll());
        return "book-form";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateBook(@PathVariable Long id) {
        return new ModelAndView("book-form",
                Map.of("book", bookRepository.findById(id).orElseThrow(),
                        "publishers",publisherRepository.findAll()));
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
         bookRepository.save(book);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{bookId}")
    @ResponseBody
    public void deleteBook(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
//        return "redirect:/";
    }
}
