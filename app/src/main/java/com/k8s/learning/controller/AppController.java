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
        String podName = System.getenv("POD_NAME");
        String podUid  = System.getenv("POD_UID");
        System.out.println("Pod Name ::: " + podName);
        System.out.println("Pod UID ::: " + podUid);
        String message = "Response from V1 version APP ( pod name is : " + podName + ", pod id is: " + podUid + ")";
        return ResponseEntity.ok(message);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/message")
    public ResponseEntity<?> getMessage(){
        String podName = System.getenv("POD_NAME");
        String podUid  = System.getenv("POD_UID");
        System.out.println("Pod Name ::: " + podName);
        System.out.println("Pod UID ::: " + podUid);
        String message = "Response from V1 version APP with message api ( pod name is : " + podName + ", pod id is: " + podUid + ")";
        return ResponseEntity.ok(message);
    }
}
