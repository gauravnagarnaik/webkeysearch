package com.gaurav.model;

/**
 * Created by Gaurav on 4/29/2017.
 * Model of the Label to initiate data
 * Not used currently
 */
public class Label {
    private String key;
    private String label;



    public Label(String key, String label) {
        this.key = key;
        this.label = label;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
