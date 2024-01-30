package ru.gb.SpringREST.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.SpringREST.api.IssuerRequest;
import ru.gb.SpringREST.model.Book2;
import ru.gb.SpringREST.model.Issue2;
import ru.gb.SpringREST.repository.BookRepository2;
import ru.gb.SpringREST.repository.IssueRepository2;
import ru.gb.SpringREST.repository.ReaderRepository2;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueService2 {

    @Autowired
    private final BookRepository2 bookRepository;

    @Autowired
    private final ReaderRepository2 readerRepository;

    @Autowired
    private final IssueRepository2 issueRepository;

    public Issue2 getIssueById(Long id) {
        return issueRepository.getIssueById(id);
    }

    @Transactional
    public Issue2 issue(IssuerRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }

        Issue2 issue = new Issue2(request.getBookId(), request.getReaderId());
        issueRepository.saveNewIssue(issue.getBookId(), issue.getReaderId(), issue.getTimestamp());
        List<Issue2> issues = issueRepository.getAllIssues();
        return issues.get(issues.size() - 1);
    }

    public List<Issue2> getAllIssues() {
        return issueRepository.getAllIssues();
    }
}
