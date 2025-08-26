package com.k8s.learning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/v1")
public class PostController {

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok("Response from POST Service");
    }
}
