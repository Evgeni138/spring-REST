package ru.gb.SpringREST.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.SpringREST.model.Issue;
import ru.gb.SpringREST.service.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

    @Autowired
    private IssueService service;

    @PostMapping
    public ResponseEntity<Issue> issuerBook(@RequestBody IssuerRequest request) {
        log.info("Request for issuer received: readerId = {}, bookId = {}",
                request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @GetMapping(path = "/{id}")
    public Issue getIssueById(@PathVariable long id) {
        log.info("Request for getIssueById received with id: {}", id);

        Issue issue = service.getIssueById(id);

        if (issue != null) {
            log.info("Found issue with id {}: {}", id, issue);
            return issue;
        } else {
            log.warn("Issue with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found");
        }
    }

    @GetMapping
    public List<Issue> gelAllIssues() {
        return service.getAllIssues();
    }
}
