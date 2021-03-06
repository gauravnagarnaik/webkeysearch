package com.gaurav.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Gaurav on 4/29/2017.
 * The Criteria for the search and sets the key to be searched
 */
public class SearchCriteria {

    @NotBlank(message = "Please Enter key to search!")
    private String label;

    /**
     * get label for search
     * @return String
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set label for search
     * @param label String
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
