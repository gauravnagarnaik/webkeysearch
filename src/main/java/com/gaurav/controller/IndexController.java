package com.gaurav.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Gaurav on 4/29/2017.
 * Controller to point the view to ajax html
 */
@Controller
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * function to index
     * @return String
     */
    @GetMapping("/")
    public String index() {
        return "ajax";
    }
}
