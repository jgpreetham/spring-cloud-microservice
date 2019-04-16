package com.example.microservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("example-service5")
public interface ExampleService5Client {

	@GetMapping("/translate/{word}")
	public String translateWord(@PathVariable("word")String word) ;
	
	
}
