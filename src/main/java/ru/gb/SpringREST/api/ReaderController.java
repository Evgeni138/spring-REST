package ru.gb.SpringREST.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.SpringREST.model.Issue;
import ru.gb.SpringREST.model.Reader;
import ru.gb.SpringREST.service.ReaderService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService service;

    @GetMapping
    public List<Reader> getAllReaders() {
        return service.getAllReaders();
    }

    @GetMapping(path = "/{id}")
    public Reader getReaderById(@PathVariable long id) {
        log.info("Request for getReaderById received with id: {}", id);

        Reader reader = service.getReaderById(id);

        if (reader != null) {
            log.info("Found reader with id {}: {}", id, reader);
            return reader;
        } else {
            log.warn("Reader with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found");
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deleteReader(@PathVariable long id) {
        log.info("Request for deleteReader with id: {}", id);

        Reader reader = service.getReaderById(id);

        if (reader != null) {
            log.info("Found book with id {}: {} and deleted", id, reader.getName());
            service.deleteReader(id);
        } else {
            log.warn("Reader with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found");
        }
    }

    @PostMapping
    public Reader addNewReader(@RequestBody Reader reader) {
        Reader newReader = service.addReader(reader.getName());
        return newReader;
    }

    @GetMapping(path = "/{id}/issue")
    public List<Issue> getIssuesOfReaderById(@PathVariable long id) {
        log.info("Request for getReaderById received with id: {}", id);

        Reader reader = service.getReaderById(id);
        List<Issue> issues = service.getIssueRepository().getAllIssues().stream()
                .filter(it -> it.getReaderId() == id)
                .collect(Collectors.toList());

        if (issues != null) {
            log.info("Found issues of reader with id {}: {}", id, reader);
            return issues;
        } else {
            log.warn("Issues of reader with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issues not found");
        }
    }

}
