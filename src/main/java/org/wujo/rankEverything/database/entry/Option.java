package org.wujo.rankEverything.database.entry;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Entity
@Table(schema = "test")
@Getter
public class Option implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer upvote;
    private Integer downvote;


    public Option(String name, Integer upvote, Integer downvote) {
        this.name = name;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public Option(){}
}

