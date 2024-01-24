package ru.gb.SpringREST.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.gb.SpringREST.model.Book;
import ru.gb.SpringREST.model.Issue;

import java.util.ArrayList;
import java.util.List;

@Repository
@Data
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository(List<Issue> issues) {
        this.issues = new ArrayList<>();
    }

    public void saveIssue(Issue issue) {
        issues.add(issue);
    }
}
