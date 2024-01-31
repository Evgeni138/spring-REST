package ru.gb.SpringREST.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//import ru.gb.SpringREST.model.Book;
import ru.gb.SpringREST.model.Book;
//import ru.gb.SpringREST.service.BookService;
import ru.gb.SpringREST.service.BookService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
@Tag(name = "Books")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping
    @Operation(summary = "get all books", description = "Загружает все книги, которые есть в системе")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "get book by id", description = "Загружает данные книги по её идентификатору")
    public Book getBookById(@PathVariable long id) {
        log.info("Request for getBookById received with id: {}", id);

        Book book = service.getBookById(id);

        if (book != null) {
            log.info("Found book with id {}: {}", id, book);
            return book;
        } else {
            log.warn("Book with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "delete book", description = "Удаляет книгу из системы по идентификатору")
    public void deleteBook(@PathVariable long id) {
        log.info("Request for deleteBook with id: {}", id);

        Book book = service.getBookById(id);

        if (book != null) {
            log.info("Found book with id {}: {} and deleted", id, book.getName());
            service.deleteBook(id);
        } else {
            log.warn("Book with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @PostMapping
    @Operation(summary = "add new book", description = "Добавляет новую книгу в систему")
    public Book addNewBook(@RequestBody Book book) {
        Book newBook = service.addBook(book.getName());
        return newBook;
    }
}
