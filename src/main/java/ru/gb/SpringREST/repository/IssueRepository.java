package ru.gb.SpringREST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.SpringREST.model.Issue;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO issues (BOOK_ID, READER_ID, TIMESTAMP) " +
            "VALUES (:bookId, :readerId, :timestamp)")
    void saveNewIssue(@Param("bookId") Long bookId, @Param("readerId") Long readerId,
                      @Param("timestamp") LocalDateTime timestamp);

    @Query(nativeQuery = true, value = "SELECT * FROM issues")
    public List<Issue> getAllIssues();

    @Query(nativeQuery = true, value = "SELECT * FROM issues WHERE id = :queryId")
    public Issue getIssueById(@Param("queryId") Long queryId);

    @Query(nativeQuery = true, value = "DELETE FROM issues WHERE id = :queryId")
    public void deleteIssue(@Param("queryId") Long queryId);
}
