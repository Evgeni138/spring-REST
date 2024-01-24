package ru.gb.SpringREST.repository;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.gb.SpringREST.model.Book;
import ru.gb.SpringREST.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Data
public class ReaderRepository {

    private final List<Reader> readers;

    public ReaderRepository(List<Reader> readers) {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        readers.addAll(List.of(
                new Reader("Евгений"),
                new Reader("Ника"),
                new Reader("Алиса")
        ));
    }

    public Reader getReaderById(long id) {
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Reader addReader(String name) {
        Reader reader = new Reader(name);
        readers.add(reader);
        return reader;
    }

    public void deleteReader(long id) {
        readers.removeIf(reader -> reader.getId() == id);
    }
}
