package org.wujo.rankEverything.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wujo.rankEverything.database.entry.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * FROM test.person ORDER BY RANDOM() LIMIT 2", nativeQuery = true)
    List<Person> findTwoRandomOptions();

}