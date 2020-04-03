package com.evansitzes.vocabularyapi;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEncryptableProperties
public class VocabularyApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(VocabularyApiApplication.class, args);
	}
}
