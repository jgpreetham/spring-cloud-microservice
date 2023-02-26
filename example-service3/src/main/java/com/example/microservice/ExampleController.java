package com.example.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExampleController {


	@GetMapping("/value")
	public String sayValue() {
		return "Hi from Controller";
	}
}