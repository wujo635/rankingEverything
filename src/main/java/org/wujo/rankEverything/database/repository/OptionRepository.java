package org.wujo.rankEverything.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wujo.rankEverything.database.entry.Option;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    @Query(value = "SELECT * FROM option ORDER BY RANDOM() LIMIT 2", nativeQuery = true)
    List<Option> findTwoRandomOptions();

    @Modifying
    @Transactional
    @Query("UPDATE Option o SET o.upvote = o.upvote + 1 WHERE o.id = :id")
    void incrementUpvote(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Option o SET o.downvote = o.downvote + 1 WHERE o.id = :id")
    void incrementDownvote(Long id);
}
