package com.k8s.learning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/v1")
public class PostController {

    private Integer count = 1;
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> getAll(){
        System.out.println("Request count for post : " + count);
        count++;
        String podName = System.getenv("POD_NAME");
        String podUid  = System.getenv("POD_UID");
        System.out.println("Pod Name ::: " + podName);
        System.out.println("Pod UID ::: " + podUid);
        String message = "Response from POST Service ( pod name : " + podName + ", pod id: " + podUid + ")";
        return ResponseEntity.ok(message);
    }
}
