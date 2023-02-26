package com.example.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExampleController {

	@Autowired
	private ConfigProperties properties;

	@GetMapping("/value")
	public String sayValue() {
		return properties.getSomeProperty();
	}
}