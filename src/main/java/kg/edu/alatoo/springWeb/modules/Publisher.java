package kg.edu.alatoo.springWeb.modules;

import jakarta.persistence.*;

import java.time.Year;
import java.util.Set;

@Entity
public class Publisher {

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
        this.year = Year.now().getValue();
    }

    @Id @GeneratedValue
    private long id;

    private String name;

    @Column(name = "created_year")
    private Integer year;
    private String address;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
