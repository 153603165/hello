package com.kfwl.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@SpringBootApplication
@RestController
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * 测试hello
	 * 
	 * @return hello world
	 */
	@GetMapping("/hello")
	public String hello() {
		return "hello World";
	}

	@GetMapping("/k8s")
	public String k8s() {
		return "hello k8s";
	}
}
