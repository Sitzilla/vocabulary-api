package com.evansitzes.vocabularyapi.controller;

import com.evansitzes.vocabularyapi.model.entity.Word;
import com.evansitzes.vocabularyapi.model.repository.WordRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "words", produces = MediaType.APPLICATION_JSON_VALUE)
public class WordRestController {

    private final WordRepository wordRepository;

    public WordRestController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @GetMapping(path="")
    public ResponseEntity<List<Word>> getWords(
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

        return ResponseEntity.accepted().body(wordRepository.findAll(Example.of(wordFilter, matcher)));
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Word> getWord(@PathVariable(value="id") final int id) {
        final Word word = wordRepository.findById(id).orElse(null);

        if (word == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.accepted().body(word);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Word> deleteWord(@PathVariable(value="id") final int id) {
        final Word word = wordRepository.findById(id).orElse(null);

        if (word == null) {
            return ResponseEntity.notFound().build();
        }

        word.setActive(false);
        return ResponseEntity.accepted().body(wordRepository.save(word));
    }
}
