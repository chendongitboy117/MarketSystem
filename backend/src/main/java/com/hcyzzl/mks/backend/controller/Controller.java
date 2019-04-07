package com.hcyzzl.mks.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controller {
    private final static Logger logger = LoggerFactory.getLogger(Controller.class);
    @RequestMapping("/getName")
    public String getName(){
        logger.info("hello Sfl4j + logback......");
        return "Hello SpringBoot";
    }
}
