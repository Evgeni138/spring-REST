package ru.gb.SpringREST.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.SpringREST.model.*;
import ru.gb.SpringREST.service.BookService2;
import ru.gb.SpringREST.service.IssueService2;
import ru.gb.SpringREST.service.ReaderService2;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui")
public class UIController {

    @Autowired
    BookService2 bookService;

    @Autowired
    ReaderService2 readerService;

    @Autowired
    IssueService2 issuerService;

    @GetMapping("/books")
    public String bookList(Model model) {
        List<Book2> books = bookService.getAllBooks();

        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/readers")
    public String readerList(Model model) {
        List<Reader2> readers = readerService.getAllReaders();

        model.addAttribute("readers", readers);
        return "readers";
    }

    @GetMapping("/issues")
    public String issueList(Model model) {
        List<Issue2> issues = issuerService.getAllIssues();

        model.addAttribute("issues", issues);
        return "issues";
    }
}
