package org.wujo.rankEverything;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.wujo.rankEverything.database.entry.Person;
import org.wujo.rankEverything.database.repository.PersonRepository;

import java.util.List;

@Controller
public class RouteController {

    private final PersonRepository personRepository;

    public RouteController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/people")
    public String getAllPeople(Model model) {
        List<Person> people = personRepository.findAll();
        model.addAttribute("people", people);
        return "people";
    }

    @GetMapping("/rank")
    public String rankThings(Model model) {
        List<Person> options = personRepository.findTwoRandomOptions();
        if (options.size() < 2) {
            model.addAttribute("error", "Not enough options available.");
            return "error";
        }
        model.addAttribute("option1", options.get(0));
        model.addAttribute("option2", options.get(1));
        return "rankThings";
    }

    @GetMapping("/")
    public String index() {
        return "Greetings";
    }
}
