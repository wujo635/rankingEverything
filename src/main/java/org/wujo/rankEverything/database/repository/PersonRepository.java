package org.wujo.rankEverything.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wujo.rankEverything.database.entry.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}