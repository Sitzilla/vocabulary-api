package com.evansitzes.vocabularyapi.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private int level;

    private int knowledgeCount;

    private boolean active;

    private String foreignWord;

    private String englishWord;

    private String category;

    private String language;
}
