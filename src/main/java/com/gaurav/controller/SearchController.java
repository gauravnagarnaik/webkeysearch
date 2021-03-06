package com.gaurav.controller;

import com.gaurav.model.AjaxResponseBody;
import com.gaurav.model.SearchCriteria;
import com.gaurav.services.LabelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Gaurav on 4/29/2017.
 *
 * Search Controller which is the Rest Controller handles response from and to view
 */

@RestController
public class SearchController {
    private LabelSearchService labelSearchService;

    @Autowired
    public void setUserService(LabelSearchService labelSearchService) {
        this.labelSearchService = labelSearchService;
    }

    /**
     * function to control flow between AjaxResponseBody and LabelSearchService
     * @param search SearchCriteria
     * @param errors Errors
     * @return ResponseEntity
     */
    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(
            @Valid @RequestBody SearchCriteria search, Errors errors){


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

        String label = labelSearchService.findTranslation(search.getLabel());

        translation.setTranslation(label);
        if(label == null) {
            return null;
        }
        return ResponseEntity.ok(translation);
    }




}
