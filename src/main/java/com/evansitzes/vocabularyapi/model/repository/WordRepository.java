package com.evansitzes.vocabularyapi.model.repository;

import com.evansitzes.vocabularyapi.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {

}
