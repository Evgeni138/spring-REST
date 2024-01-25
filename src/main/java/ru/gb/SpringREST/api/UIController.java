package ru.gb.SpringREST.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.SpringREST.model.Book;
import ru.gb.SpringREST.model.Issue;
import ru.gb.SpringREST.model.Reader;
import ru.gb.SpringREST.service.BookService;
import ru.gb.SpringREST.service.IssuerService;
import ru.gb.SpringREST.service.ReaderService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui")
public class UIController {

    @Autowired
    BookService bookService;

    @Autowired
    ReaderService readerService;

    @Autowired
    IssuerService issuerService;

    @GetMapping("/books")
    public String bookList(Model model) {
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/readers")
    public String readerList(Model model) {
        List<Reader> readers = readerService.getAllReaders();

        model.addAttribute("readers", readers);
        return "readers";
    }

    @GetMapping("/issues")
    public String issueList(Model model) {
        List<Issue> issues = issuerService.getAllIssues();

        model.addAttribute("issues", issues);
        return "issues";
    }
}
