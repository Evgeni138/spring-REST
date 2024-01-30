package ru.gb.SpringREST.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//import ru.gb.SpringREST.model.Book;
import ru.gb.SpringREST.model.Book2;
//import ru.gb.SpringREST.service.BookService;
import ru.gb.SpringREST.service.BookService2;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService2 service;

    @GetMapping
    public List<Book2> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping(path = "/{id}")
    public Book2 getBookById(@PathVariable long id) {
        log.info("Request for getBookById received with id: {}", id);

        Book2 book = service.getBookById(id);

        if (book != null) {
            log.info("Found book with id {}: {}", id, book);
            return book;
        } else {
            log.warn("Book with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable long id) {
        log.info("Request for deleteBook with id: {}", id);

        Book2 book = service.getBookById(id);

        if (book != null) {
            log.info("Found book with id {}: {} and deleted", id, book.getName());
            service.deleteBook(id);
        } else {
            log.warn("Book with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @PostMapping
    public Book2 addNewBook(@RequestBody Book2 book) {
        Book2 newBook = service.addBook(book.getName());
        return newBook;
    }
}
