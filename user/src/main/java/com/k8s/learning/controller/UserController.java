package com.k8s.learning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/v1")
public class UserController {

    private Integer count = 1;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getAll(){
        System.out.println("Request count for users : " + count);
        count++;
        return ResponseEntity.ok("Response from USER service");
    }
}
