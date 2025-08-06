package org.wujo.rankEverything.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.wujo.rankEverything.database.entry.Person;
import org.wujo.rankEverything.database.repository.PersonRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OptionService {

    private final PersonRepository optionRepository;

    public OptionService(PersonRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    /**
     * Fetches two random, non-duplicate options from the database.
     */
    public List<Person> getTwoRandomOptions() {
        List<Person> options = optionRepository.findTwoRandomOptions();

        // TODO - figure out if need fallback if less than 2 unique options
        if (options.size() < 2) {
            throw new RuntimeException("Not enough options available for comparison.");
        }

        return options;
    }

    /**
     * Records the user's selection by incrementing upvote/downvote.
     */
    @Transactional
    public void recordSelection(Long selectedId, Long otherId) {
        Person selected = optionRepository.findById(selectedId)
                .orElseThrow(() -> new RuntimeException("Selected option not found"));
        Person other = optionRepository.findById(otherId)
                .orElseThrow(() -> new RuntimeException("Other option not found"));

        // Increment upvote for selected option
        optionRepository.incrementUpvote(selectedId);
        // TODO - handle downvote logic if needed

        System.out.println("User selected option ID: " + selectedId + " over ID: " + otherId);
    }

    /**
     * Optional: Logs skip action (doesn't modify votes).
     */
    public void recordSkip(Long id1, Long id2) {
        System.out.printf("User skipped comparison between ID %d and ID %d%n", id1, id2);
        // Optionally log to a skip table or analytics service
    }

    public void addNewOption(String name) {
        Person newOption = new Person(name, 0, 0);
        optionRepository.save(newOption);
    }

    public List<Person> findAllSortedByScore() {
        return optionRepository.findAll().stream()
                .sorted(Comparator.comparingInt(o -> -(o.getUpvote() - o.getDownvote())))
                .collect(Collectors.toList());
    }

}
