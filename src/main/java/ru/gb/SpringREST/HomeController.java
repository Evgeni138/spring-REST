package ru.gb.SpringREST;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/table")
    public String table(Model model) {
        List<Person> people = List.of(
                new Person("John", "Smith", 33, "google.com"),
                new Person("Andy", "Ramos", 55, "apple.com"),
                new Person("Garry", "Moore", 66, "amazon.com")
        );
        model.addAttribute("people", people);
        return "table";
    }

    @GetMapping("/list")
    public String list(@RequestParam(name = "count", required = false) Integer count, Model model) {
        if (count == null) {
            count = 1; // значение по умолчанию, если параметр не передан
        }

        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            items.add(new Item(i, "Item #" + i));
        }

        model.addAttribute("count", count);
        model.addAttribute("items", items);
        return "list";
    }


    @GetMapping("/home")
    public String home(@RequestParam(required = false) String name, Model model,
                       @RequestParam(required = false) String color) {
        if (name != null) {
            model.addAttribute("name", name);
        } else {
            model.addAttribute("name", "world");
        }
        model.addAttribute("color", (color == null ? "red" : color));
        return "home";
    }
}
