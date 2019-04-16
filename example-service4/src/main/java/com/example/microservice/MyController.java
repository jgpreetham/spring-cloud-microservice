package com.example.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExampleController {

	@Autowired
	private ExampleService5Client client;

	@GetMapping
	public String sayValue() {
		return "Hi from Controller";
	}
	
	
	@GetMapping(value="/{word}")
	public String translateTheWord(@PathVariable("word") String word){
		String translatedWord = client.translateWord(word);
		return translatedWord;
	}
}