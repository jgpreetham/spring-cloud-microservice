package com.example.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExampleController {

	@Autowired
	private ConfigProperties properties;

	@RequestMapping
	public String sayValue() {
		return properties.getSomeProperty();
	}
}