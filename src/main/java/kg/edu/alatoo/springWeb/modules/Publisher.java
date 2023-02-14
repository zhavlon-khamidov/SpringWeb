package kg.edu.alatoo.springWeb.modules;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Publisher {

    @Id @GeneratedValue
    private long id;

    private String name;

    @Column(name = "created_year")
    private Long year;
    private String address;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;



}
