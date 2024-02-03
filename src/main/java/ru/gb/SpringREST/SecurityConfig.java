package ru.gb.SpringREST;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class SecurityConfig {

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            System.out.println("User authenticated: " + userDetails.getUsername());
            System.out.println("User authenticated: " + userDetails.getPassword());
            // Дополнительная логика с данными пользователя
        } else {
            System.out.println("User authenticated: " + principal);
        }
    }
}

