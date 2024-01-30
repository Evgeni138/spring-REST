package ru.gb.SpringREST.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import ru.gb.SpringREST.model.Book;
import ru.gb.SpringREST.model.Book2;
//import ru.gb.SpringREST.repository.BookRepository;
import ru.gb.SpringREST.repository.BookRepository2;
//import ru.gb.SpringREST.repository.IssueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService2 {

    @Autowired
    private final BookRepository2 bookRepository;

    @Transactional
    public void init() {
        bookRepository.addNewBook("Война и мир");
        bookRepository.addNewBook("Мертвые души");
        bookRepository.addNewBook("Чистый код");
    }

    public List<Book2> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book2 getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }

    @Transactional
    public Book2 addBook(String name) {
        bookRepository.addNewBook(name);
        List<Book2> books = bookRepository.getAllBooks();
        return books.get(books.size() - 1);
    }
}
