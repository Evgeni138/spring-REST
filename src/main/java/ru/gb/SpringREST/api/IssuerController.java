package ru.gb.SpringREST.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.SpringREST.model.Issue2;
import ru.gb.SpringREST.service.IssueService2;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

    @Autowired
    private IssueService2 service;

    @PostMapping
    public ResponseEntity<Issue2> issuerBook(@RequestBody IssuerRequest request) {
        log.info("Request for issuer received: readerId = {}, bookId = {}",
                request.getReaderId(), request.getBookId());

        final Issue2 issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @GetMapping(path = "/{id}")
    public Issue2 getIssueById(@PathVariable long id) {
        log.info("Request for getIssueById received with id: {}", id);

        Issue2 issue = service.getIssueById(id);

        if (issue != null) {
            log.info("Found issue with id {}: {}", id, issue);
            return issue;
        } else {
            log.warn("Issue with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found");
        }
    }

    @GetMapping
    public List<Issue2> gelAllIssues() {
        return service.getAllIssues();
    }
}
