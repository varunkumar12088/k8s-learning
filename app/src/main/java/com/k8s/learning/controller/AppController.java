package com.k8s.learning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> get(){
        return ResponseEntity.ok("Response from <h1>V2 version</h1> APP");
    }
}
