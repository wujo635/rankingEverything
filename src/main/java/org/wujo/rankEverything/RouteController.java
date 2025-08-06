package org.wujo.rankEverything;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wujo.rankEverything.database.entry.Person;
import org.wujo.rankEverything.service.OptionService;

import java.util.List;

@Controller
@RequestMapping("/options")
public class RouteController {

    private final OptionService optionService;

    public RouteController(OptionService optionService) {
        this.optionService = optionService;
    }

    // Display comparison page
    @GetMapping("/compare")
    public String showComparison(Model model) {
        List<Person> options = optionService.getTwoRandomOptions();

        model.addAttribute("option1", options.get(0));
        model.addAttribute("option2", options.get(1));

        return "compare";  // Thymeleaf template: resources/templates/compare.html
    }

    // Handle a vote
    @PostMapping("/vote")
    public String handleVote(@RequestParam Long selectedId, @RequestParam Long otherId) {
        optionService.recordSelection(selectedId, otherId);
        return "redirect:/options/compare";
    }

    // Handle skip
    @PostMapping("/skip")
    public String handleSkip(@RequestParam Long option1Id, @RequestParam Long option2Id) {
        optionService.recordSkip(option1Id, option2Id);
        return "redirect:/options/compare";
    }

    @GetMapping("/new")
    public String showAddForm() {
        return "add";
    }

    @PostMapping("/add")
    public String addOption(@RequestParam String name) {
        optionService.addNewOption(name);
        return "redirect:/options/compare";
    }

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        List<Person> options = optionService.findAllSortedByScore();
        model.addAttribute("options", options);
        return "leaderboard";
    }

}
