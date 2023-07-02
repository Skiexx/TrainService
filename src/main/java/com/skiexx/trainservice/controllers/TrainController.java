package com.skiexx.trainservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/train")
public class TrainController {

        @GetMapping("/hello")
        public String hello() {
            return "Hello from Train Service";
        }
}
