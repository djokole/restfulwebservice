package com.udemy.webservices.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public String helloWord() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWordBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWordInternationalized(@RequestHeader(name = "Accept-language", required = false)Locale locale) {
		return messageSource.getMessage("good.morning.message",null,"Default Message",
				// If we dont want to have @RequestHeader, we can get locale like LocaleContextHolder.getLocale()
				locale);
	}
}
