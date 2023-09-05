package com.thehyundai.thepet.global.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerTest {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}