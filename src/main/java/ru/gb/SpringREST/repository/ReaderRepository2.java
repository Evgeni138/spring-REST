package ru.gb.SpringREST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.SpringREST.model.Reader2;

import java.util.List;

@Repository
public interface ReaderRepository2 extends JpaRepository<Reader2, Long> {

    @Query(nativeQuery = true, value = "SELECT id, name FROM readers")
    public List<Reader2> getAllReaders();

    @Query(nativeQuery = true, value = "SELECT * FROM readers WHERE id = :queryId")
    public Reader2 getReaderById(@Param("queryId") Long queryId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO readers (name) VALUES (:name)")
    void addNewReader(@Param("name") String name);

    @Query(nativeQuery = true, value = "DELETE FROM readers WHERE id = :queryId")
    public void deleteReader(@Param("queryId") Long queryId);
}
