package ru.gb.SpringREST.api;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @GetMapping
    public String resource() {
        return "resource";
    }

    @GetMapping("/auth")
    public String auth() {
        return "Authorised";
    }

    @GetMapping("/user")
//    @Secured("user")
    public String user() {
        return "User";
    }

    @GetMapping("/admin")
//    @Secured("admin")
    public String admin() {
        return "Admin";
    }

}
