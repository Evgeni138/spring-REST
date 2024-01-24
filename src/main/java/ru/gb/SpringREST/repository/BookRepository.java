package ru.gb.SpringREST.repository;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.gb.SpringREST.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Data
public class BookRepository {

    private final List<Book> books;

    public BookRepository(List<Book> books) {
        this.books = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
            new Book("Война и мир"),
            new Book("Мертвые души"),
            new Book("Чистый код")
                ));
    }

    public Book getBookById(long id) {
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Book addBook(String name) {
        Book book = new Book(name);
        books.add(book);
        return book;
    }

    public void deleteBook(long id) {
        books.removeIf(book -> book.getId() == id);
    }
}
