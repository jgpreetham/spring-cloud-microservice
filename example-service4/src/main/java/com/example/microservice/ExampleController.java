package com.example.microservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ExampleController {

	@Autowired
	private ExampleService5Client client;

	
	@GetMapping(value="/{word}")
	@Retry(name = "retry", fallbackMethod = "defaultTranslate")
	public String translateTheWord(@PathVariable("word") String word){
		String translatedWord = client.translateWord(word);
		return translatedWord;
	}
	
	public String defaultTranslate(@PathVariable("word") String word, Throwable t){
		return "Circuit breaker default fallback response: "+word;
	}
}