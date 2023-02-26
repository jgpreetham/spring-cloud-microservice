package com.example.microservice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExampleController {

	@Autowired
	private Environment environment;
	private Map<String,String> map = new HashMap<>();
	
	
	@PostConstruct
	public void init() {
		map.put("hello", "Bonjour");
		map.put("bye", "au revoir");
	}

	@GetMapping("/value")
	public String sayValue() {
		return "Hi from Controller";
	}
	
	
	@GetMapping(value = "/translate/{word}")
	public String translateWord(@PathVariable("word")String word) {
		System.out.println("Request received "+ word);
		String translatedWord = map.get(word);
		return translatedWord + "   Port number: " + environment.getProperty("local.server.port");
	}
	
}