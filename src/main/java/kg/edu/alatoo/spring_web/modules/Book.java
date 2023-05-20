package kg.edu.alatoo.spring_web.modules;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue
    private long id;
    private String title;
    private String isbn;

    private int publishedYear;

    private byte[] coverPhoto;

    @ManyToMany
    private Set<Author> authors;

    @ManyToOne
    private Publisher publisher;

    public Book(String title, String isbn) {
            this.title = title;
            this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    public Book addAuthor(Author author) {
        if (authors == null)
            authors = new HashSet<>();

        authors.add(author);
        return this;
    }
}
