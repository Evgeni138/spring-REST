package ru.gb.SpringREST.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import ru.gb.SpringREST.model.Book;
import ru.gb.SpringREST.model.Book;
//import ru.gb.SpringREST.repository.BookRepository;
import ru.gb.SpringREST.repository.BookRepository;
//import ru.gb.SpringREST.repository.IssueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Transactional
    public void init() {
        bookRepository.addNewBook("Война и мир");
        bookRepository.addNewBook("Мертвые души");
        bookRepository.addNewBook("Чистый код");
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }

    @Transactional
    public Book addBook(String name) {
        bookRepository.addNewBook(name);
        List<Book> books = bookRepository.getAllBooks();
        return books.get(books.size() - 1);
    }
}
