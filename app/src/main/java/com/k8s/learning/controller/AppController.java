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
        return ResponseEntity.ok("Response from <h1>V1.1 version</h1> APP");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/message")
    public ResponseEntity<?> getMessage(){
        return ResponseEntity.ok("Message from APP application, used API-Gateway");
    }
}
