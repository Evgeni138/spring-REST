package ru.gb.SpringREST;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
                    classes = JUnitTestBase.TestSecurityConfiguration.class)
@AutoConfigureWebTestClient
public class JUnitTestBase {

    @TestConfiguration
    static class TestSecurityConfiguration {

        @Bean
        SecurityFilterChain testSecurityFilterChain(HttpSecurity security) throws Exception {
            return security.authorizeHttpRequests(registry -> registry
                            .anyRequest().permitAll())
                            .build();
        }

    }
}
