package ru.gb.SpringREST.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.SpringREST.api.IssuerRequest;
import ru.gb.SpringREST.model.Issue;
import ru.gb.SpringREST.repository.BookRepository;
import ru.gb.SpringREST.repository.IssueRepository;
import ru.gb.SpringREST.repository.ReaderRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Issue issue(IssuerRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.saveIssue(issue);

        return issue;
    }

    public Issue getIssue(long id) {
        return issueRepository.getIssues().stream().filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }



}
