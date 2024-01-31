package ru.gb.SpringREST.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.SpringREST.api.IssuerRequest;
import ru.gb.SpringREST.model.Issue;
import ru.gb.SpringREST.repository.BookRepository;
import ru.gb.SpringREST.repository.IssueRepository;
import ru.gb.SpringREST.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final ReaderRepository readerRepository;

    @Autowired
    private final IssueRepository issueRepository;

    public Issue getIssueById(Long id) {
        return issueRepository.getIssueById(id);
    }

    @Transactional
    public Issue issue(IssuerRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.saveNewIssue(issue.getBookId(), issue.getReaderId(), issue.getTimestamp());
        List<Issue> issues = issueRepository.getAllIssues();
        return issues.get(issues.size() - 1);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }
}
