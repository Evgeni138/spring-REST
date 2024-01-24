package ru.gb.SpringREST.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.SpringREST.model.Reader;
import ru.gb.SpringREST.repository.BookRepository;
import ru.gb.SpringREST.repository.IssueRepository;
import ru.gb.SpringREST.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class ReaderService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.getReaders();
    }

    public Reader getReaderById(long id) {
        return readerRepository.getReaders().stream().filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteReader(long id) {
        readerRepository.deleteReader(id);
    }

    public Reader addReader(String name) {
        return readerRepository.addReader(name);
    }
}
