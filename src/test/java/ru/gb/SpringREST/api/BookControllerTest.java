package ru.gb.SpringREST.api;

import lombok.Data;
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
import ru.gb.SpringREST.repository.BookRepository;
import ru.gb.SpringREST.repository.UserRepository;

import java.util.List;
import java.util.Objects;

public class BookControllerTest extends JUnitTestBase {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;

    @Data
    static class JUnitBookResponse {
        private Long id;
        private String name;
    }

    @LocalServerPort
    int port;

    @Test
    void testGetAll() {
        bookRepository.saveAll(List.of(
                new Book("Война и мир"),
                new Book("Фауст")
        ));

        List<Book> expected = bookRepository.findAll();

        List<JUnitBookResponse> responseBody = webTestClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitBookResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JUnitBookResponse customerResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), customerResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), customerResponse.getName()));
            Assertions.assertTrue(found);
        }
        System.out.println("port: " + port);
    }

    @Test
    void testFindByIdSuccess() {
        Book expected = bookRepository.save(new Book("Горе от ума"));

        JUnitBookResponse responseBody = webTestClient.get()
                .uri("/book/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }

    @Test
    void testSave() {
        JUnitBookResponse request = new JUnitBookResponse();
        request.setName("Test");
        JUnitBookResponse responseBody = webTestClient.post()
                .uri("/book")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        System.out.println(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        System.out.println(port);

        Assertions.assertTrue(bookRepository.findById(responseBody.getId()).isPresent());

    }

}
