package com.original.bookstore.controller.advice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

	@GetMapping("/")
	public String index() {
		return "Hello this is a sample book store application";
	}
}
