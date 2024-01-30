package ru.gb.SpringREST.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.SpringREST.model.Reader2;
import ru.gb.SpringREST.repository.IssueRepository2;
import ru.gb.SpringREST.repository.ReaderRepository2;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class ReaderService2 {

    @Autowired
    private final ReaderRepository2 readerRepository;
    private final IssueRepository2 issueRepository;

    @Transactional
    public void init() {
        readerRepository.addNewReader("Евгений");
        readerRepository.addNewReader("Ника");
        readerRepository.addNewReader("Алиса");
    }

    public List<Reader2> getAllReaders() {
        return readerRepository.getAllReaders();
    }

    public Reader2 getReaderById(Long id) {
        return readerRepository.getReaderById(id);
    }

    public void deleteReader(Long id) {
        readerRepository.deleteReader(id);
    }

    @Transactional
    public Reader2 addReader(String name) {
        readerRepository.addNewReader(name);
        List<Reader2> readers = readerRepository.getAllReaders();
        return readers.get(readers.size() - 1);
    }
}
