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
import ru.gb.SpringREST.model.Reader;
import ru.gb.SpringREST.repository.ReaderRepository;

import java.util.List;
import java.util.Objects;

public class ReaderControllerTest extends JUnitTestBase {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Data
    static class JUnitReaderResponse {
        private Long id;
        private String name;
    }

    @LocalServerPort
    int port;

    @Test
    void testGetAll() {
        readerRepository.saveAll(List.of(
                new Reader("Аля"),
                new Reader("Галя")
        ));

        List<Reader> expected = readerRepository.findAll();

        List<JUnitReaderResponse> responseBody = webTestClient.get()
                .uri("/reader")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitReaderResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JUnitReaderResponse customerResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), customerResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), customerResponse.getName()));
            Assertions.assertTrue(found);
        }
        System.out.println("port: " + port);
    }

    @Test
    void testFindByIdSuccess() {
        Reader expected = readerRepository.save(new Reader("Сима"));

        JUnitReaderResponse responseBody = webTestClient.get()
                .uri("/reader/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitReaderResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }

    @Test
    @WithMockUser(username = "admin", roles = "admin")
    void testSave() {
        JUnitReaderResponse request = new JUnitReaderResponse();
        request.setName("Test");
        JUnitReaderResponse responseBody = webTestClient.post()
                .uri("/reader")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitReaderResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        System.out.println(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        System.out.println(port);

        Assertions.assertTrue(readerRepository.findById(responseBody.getId()).isPresent());

    }

}
