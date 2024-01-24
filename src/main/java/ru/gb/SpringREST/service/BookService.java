package ru.gb.SpringREST.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.SpringREST.model.Book;
import ru.gb.SpringREST.repository.BookRepository;
import ru.gb.SpringREST.repository.IssueRepository;
import ru.gb.SpringREST.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public List<Book> getAllBooks() {
        return bookRepository.getBooks();
    }

    public Book getBookById(long id) {
        return bookRepository.getBooks().stream().filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteBook(long id) {
        bookRepository.deleteBook(id);
    }

    public Book addBook(String name) {
        return bookRepository.addBook(name);
    }

}
