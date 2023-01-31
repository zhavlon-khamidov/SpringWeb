package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.modules.Book;
import kg.edu.alatoo.springWeb.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// Controller + ResponseBody = RestController
@RestController
public class MainController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping({"/","/hello"})
    public String hello() {

        System.out.println(bookRepo.findAll());

        return "Hello World";
    }

    @GetMapping("/create")
    public String createBook() {
        Book sherlock_holms = new Book("Sherlock Holms", "3541354");
        bookRepo.save(sherlock_holms);
        return "Sherlock Holms book added";
    }
}
