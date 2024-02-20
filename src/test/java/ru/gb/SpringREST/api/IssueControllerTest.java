package ru.gb.SpringREST.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.SpringREST.JUnitTestBase;
import ru.gb.SpringREST.model.Book;
import ru.gb.SpringREST.model.Issue;
import ru.gb.SpringREST.repository.BookRepository;
import ru.gb.SpringREST.repository.IssueRepository;
import ru.gb.SpringREST.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class IssueControllerTest extends JUnitTestBase {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Data
    @NoArgsConstructor
    static class JUnitIssueResponse {
        private Long id;
        private long bookId;
        private long readerId;
        private LocalDateTime timestamp;

        public JUnitIssueResponse(long bookId, long readerId) {
            this.bookId = bookId;
            this.readerId = readerId;
            this.timestamp = LocalDateTime.now();
        }
    }



    @LocalServerPort
    int port;

    @Test
    void testGetAll() {
        issueRepository.saveAll(List.of(
                new Issue(1L, 1L),
                new Issue(2L, 2L)
        ));

        List<Issue> expected = issueRepository.findAll();

        List<JUnitIssueResponse> responseBody = webTestClient.get()
                .uri("/issue")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitIssueResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JUnitIssueResponse issueResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), issueResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getBookId(), issueResponse.getBookId())
                                    && Objects.equals(it.getReaderId(), issueResponse.getReaderId()));
            Assertions.assertTrue(found);
        }
        System.out.println("port: " + port);
    }

    @Test
    void testFindByIdSuccess() {
        Issue expected = issueRepository.save(new Issue(1L, 1L));

        JUnitIssueResponse responseBody = webTestClient.get()
                .uri("/issue/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitIssueResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getReaderId(), responseBody.getReaderId());
        Assertions.assertEquals(expected.getBookId(), responseBody.getBookId());

    }

}
