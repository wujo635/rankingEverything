package org.wujo.rankEverything.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wujo.rankEverything.database.entry.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * FROM test.person ORDER BY RANDOM() LIMIT 2", nativeQuery = true)
    List<Person> findTwoRandomOptions();


//    @Query(value = "SELECT * FROM option ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
//    Option findRandomOption();

    @Modifying
    @Transactional
    @Query("UPDATE Person o SET o.upvote = o.upvote + 1 WHERE o.id = :id")
    void incrementUpvote(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Person o SET o.downvote = o.downvote + 1 WHERE o.id = :id")
    void incrementDownvote(Long id);
}