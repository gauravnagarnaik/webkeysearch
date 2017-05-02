package com.gaurav.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Gaurav on 5/1/2017.
 * Search crieria for reverse lookUp
 */
public class ReverseSearchCriteria {

    @NotBlank(message = "Please Enter label to search!")
    private String label;

    /**
     * get label for reverse lookUp
     * @return String
     */
    public String getLabel() {
        return label;
    }

    /**
     * set label for reverse lookUp
     * @param label String
     */
    public void setLabel(String label) {
        this.label = label;
    }

}