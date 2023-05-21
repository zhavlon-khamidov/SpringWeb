package kg.edu.alatoo.spring_web.controllers;

import kg.edu.alatoo.spring_web.modules.Book;
import kg.edu.alatoo.spring_web.repos.AuthorRepository;
import kg.edu.alatoo.spring_web.repos.BookRepository;
import kg.edu.alatoo.spring_web.repos.PublisherRepository;
import kg.edu.alatoo.spring_web.utils.CoverPhotoUtil;
import lombok.RequiredArgsConstructor;
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
import java.nio.file.NoSuchFileException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final CoverPhotoUtil coverPhotoUtil;


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

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book, @RequestParam MultipartFile cover) throws IOException {
        if (!cover.isEmpty()){
            String originalFilename = cover.getOriginalFilename();
            assert originalFilename != null;
            String fileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
            coverPhotoUtil.save(cover.getBytes(),fileName);
            book.setCoverImage(fileName);
        }
         bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping({"/cover/{coverImage}","/cover/"})
    @ResponseBody
    public ResponseEntity<byte[]> getCoverPhoto(@PathVariable(required = false) String coverImage) throws IOException {
        System.out.println("Getting image " + coverImage);
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(coverPhotoUtil.get(coverImage));
        } catch (NoSuchFileException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).location(URI.create("/img/no-cover.png")).build();
        }
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
