package com.evansitzes.vocabularyapi.controller;

import com.evansitzes.vocabularyapi.model.entity.Word;
import com.evansitzes.vocabularyapi.model.repository.WordRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "words", produces = MediaType.APPLICATION_JSON_VALUE)
public class WordRestController {

    private final WordRepository wordRepository;

    public WordRestController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @GetMapping(path="")
    public List<Word> getWords(
            @RequestParam(value="category", required = false) final String category,
            @RequestParam(value="language", required = false) final String language) {

        final Word wordFilter = Word.builder()
                                .category(category)
                                .language(language)
                                .active(true)
                                .build();

        final ExampleMatcher matcher = ExampleMatcher
                                        .matching()
                                        .withIgnorePaths("id", "level", "knowledgeCount")
                                        .withIgnoreNullValues();

        return wordRepository.findAll(Example.of(wordFilter, matcher));
    }

    @GetMapping(path="/{id}")
    public Optional<Word> getWord(@PathVariable(value="id") final int id) {
        return wordRepository.findById(id);
    }
}
