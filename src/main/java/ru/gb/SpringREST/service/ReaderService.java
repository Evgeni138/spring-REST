package ru.gb.SpringREST.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.SpringREST.model.Reader;
import ru.gb.SpringREST.repository.IssueRepository;
import ru.gb.SpringREST.repository.ReaderRepository;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class ReaderService {

    @Autowired
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    @Transactional
    public void init() {
        readerRepository.addNewReader("Евгений");
        readerRepository.addNewReader("Ника");
        readerRepository.addNewReader("Алиса");
    }

    public List<Reader> getAllReaders() {
        return readerRepository.getAllReaders();
    }

    public Reader getReaderById(Long id) {
        return readerRepository.getReaderById(id);
    }

    public void deleteReader(Long id) {
        readerRepository.deleteReader(id);
    }

    @Transactional
    public Reader addReader(String name) {
        readerRepository.addNewReader(name);
        List<Reader> readers = readerRepository.getAllReaders();
        return readers.get(readers.size() - 1);
    }
}
