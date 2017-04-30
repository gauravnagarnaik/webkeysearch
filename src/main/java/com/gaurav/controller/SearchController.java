package com.gaurav.controller;

import com.gaurav.model.AjaxResponseBody;
import com.gaurav.model.SearchCriteria;
import com.gaurav.services.LabelService;
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

    private LabelService labelService;

    @Autowired
    public void setUserService(LabelService labelService) {
        this.labelService = labelService;
    }

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(
            @Valid @RequestBody SearchCriteria search, Errors errors){


        AjaxResponseBody translation = new AjaxResponseBody();

        if(errors.hasErrors()){

            return ResponseEntity.badRequest().body(translation);

        }

        Object label = labelService.findTranslationFromKey(search.getLabel());

        translation.setTranslation(label);
        if(label == null) {
            return null;
        }
        return ResponseEntity.ok(translation);
    }

    @PostMapping("/api/revSearch")
    public ResponseEntity<?> getReverseSearchResultViaAjax(
            @Valid @RequestBody SearchCriteria reverseSearch, Errors errors){


        AjaxResponseBody translation = new AjaxResponseBody();

        if(errors.hasErrors()){

            return ResponseEntity.badRequest().body(translation);

        }

        Object label = labelService.findTranslationFromKey(reverseSearch.getLabel());

        translation.setTranslation(label);
        if(label == null) {
            return null;
        }
        return ResponseEntity.ok(translation);
    }
}
