package com.solventa.testmc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testmc")
public class TestmcController {

    @GetMapping
    public String testRequest(){
        return "hello World Config Server";
    }
}
