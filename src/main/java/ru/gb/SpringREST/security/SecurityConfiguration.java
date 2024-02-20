package ru.gb.SpringREST.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(registry -> registry
                                .requestMatchers("/ui/issues/**").hasAuthority("admin")
                                .requestMatchers("/ui/readers/**").hasAnyAuthority("user", "admin")
                                .requestMatchers("/ui/books").permitAll()
                                .requestMatchers("/**").permitAll()
//                                .anyRequest().denyAll()
                )
                .csrf(AbstractHttpConfigurer::disable) // не для реального проекта
                .formLogin(Customizer.withDefaults())
                .build();
    }
}

