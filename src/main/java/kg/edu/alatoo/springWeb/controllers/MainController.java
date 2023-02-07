package kg.edu.alatoo.springWeb.controllers;

import kg.edu.alatoo.springWeb.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
