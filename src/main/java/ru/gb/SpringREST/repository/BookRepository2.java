package ru.gb.SpringREST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.SpringREST.model.Book2;

import java.util.List;

@Repository
public interface BookRepository2 extends JpaRepository<Book2, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO books (name) VALUES (:name)")
    void addNewBook(@Param("name") String name);



    @Query(nativeQuery = true, value = "SELECT * FROM books")
    public List<Book2> getAllBooks();

    @Query(nativeQuery = true, value = "SELECT * FROM books WHERE id = :queryId")
    public Book2 getBookById(@Param("queryId") Long queryId);

    @Query(nativeQuery = true, value = "DELETE FROM books WHERE id = :queryId")
    public void deleteBook(@Param("queryId") Long queryId);
}
