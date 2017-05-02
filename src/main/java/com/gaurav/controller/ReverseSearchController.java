package com.gaurav.controller;

import com.gaurav.model.AjaxResponseBody;
import com.gaurav.model.ReverseSearchCriteria;
import com.gaurav.services.LabelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Gaurav on 5/1/2017.
 * Rest Controller for reverse look Up
 */

@RestController
public class ReverseSearchController {
    private LabelSearchService reverseLabelSearchService;

    @Autowired
    public void setUserService(LabelSearchService reverseLabelService) {
        this.reverseLabelSearchService = reverseLabelService;
    }


    @PostMapping("/api/searchReverse")
    public ResponseEntity<?> getReverseSearchResultViaAjax(
            @Valid @RequestBody ReverseSearchCriteria searchReverse, Errors errors){


        AjaxResponseBody translation = new AjaxResponseBody();

        /**
         * if errors return bad request
         */
        if(errors.hasErrors()){

            return ResponseEntity.badRequest().body(translation);

        }

        /**
         * If no errors, search for the translation of given key
         */

        String label = reverseLabelSearchService.findReverseTranslation(searchReverse.getLabel());

        translation.setTranslation(label);

        if(label == null) {
            return null;
        }

        return ResponseEntity.ok(translation);
    }

}
