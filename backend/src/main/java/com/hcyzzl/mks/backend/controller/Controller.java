package com.hcyzzl.mks.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping("/getName")
    public String getName(){
        return "Hello SpringBoot";
    }
}
