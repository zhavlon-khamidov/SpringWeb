package kg.edu.alatoo.spring_web.controllers;

import kg.edu.alatoo.spring_web.modules.Book;
import kg.edu.alatoo.spring_web.repos.AuthorRepository;
import kg.edu.alatoo.spring_web.repos.BookRepository;
import kg.edu.alatoo.spring_web.repos.PublisherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        return "book-form";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateBook(@PathVariable Long id) {
        return new ModelAndView("book-form",
                Map.of("book", bookRepository.findById(id).orElseThrow(),
                        "publishers",publisherRepository.findAll(),
                        "authors", authorRepository.findAll()));
    }

    @GetMapping("/cover/{bookId}")
    public ResponseEntity<byte[]> getCover(@PathVariable Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()
                || optionalBook.get().getCoverPhoto() == null
                || optionalBook.get().getCoverPhoto().length == 0)
            return ResponseEntity
                    .status(HttpStatus.TEMPORARY_REDIRECT)
                    .location(URI.create("/img/no-cover.png"))
                    .build();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(optionalBook.get().getCoverPhoto());
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book, @RequestParam MultipartFile cover, Model model) {
        try {
            if (!cover.isEmpty()) {
                book.setCoverPhoto(cover.getBytes());
            } else {
                if (book.getId() != 0)
                    book.setCoverPhoto(
                            bookRepository.findById(book.getId())
                                    .orElseThrow().getCoverPhoto()
                    );
            }
        } catch (IOException e) {
            model.addAttribute(book);
            model.addAttribute("publishers", publisherRepository.findAll());
            model.addAttribute("authors", authorRepository.findAll());

            return "book-form";
        }
        bookRepository.save(book);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{bookId}")
    @ResponseBody
    public void deleteBook(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBookWithoutConfirm(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
        return "redirect:/";
    }
}
