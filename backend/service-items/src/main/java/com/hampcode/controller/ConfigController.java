package com.hampcode.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configs")
public class ConfigController {

	private static Logger log = LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	private Environment env;

	@Value("${config.text}")
	private String text;

	@GetMapping
	public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
		log.info(text);

		Map<String, String> json = new HashMap<>();
		json.put("text", text);
		json.put("port", port);

		if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("developer.name", env.getProperty("config.developer.name"));
			json.put("developer.email", env.getProperty("config.developer.email"));
		}
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

}
