package com.gaurav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Gaurav on 4/29/2017.
 *
 * Main Class
 * get the data from a given json file and provide a search functionality for the key and label.
 */
@SpringBootApplication
public class WebKeySearchApplication {
    public static void main(String[] args) {


        //System.out.println(translationMap);

        SpringApplication.run(WebKeySearchApplication.class, args);
    }

}

