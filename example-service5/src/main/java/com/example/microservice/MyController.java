package com.example.microservice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExampleController {

	private Map<String,String> map = new HashMap<>();
	
	
	@PostConstruct
	public void init() {
		map.put("hello", "Bonjour");
		map.put("bye", "au revoir");
	}

	@GetMapping
	public String sayValue() {
		return "Hi from Controller";
	}
	
	
	@GetMapping(value = "/translate/{word}")
	public String translateWord(@PathVariable("word")String word) {
		return map.get(word);
	}
	
}