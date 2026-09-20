package io.serv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServController {

    @GetMapping
    public String status() {
        return "Serv is running";
    }
}