package com.gaurav.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gaurav on 4/29/2017.
 * Model of the Label to initiate data
 */
public class Label {

    /**
     * Bi-Directional Data Structure
     */
    private Map<String, String> keyToLabelMap;
    private Map<String, List<String>> labelToKeyMap;

    /**
     * Constructor
     */
    public Label(){
        keyToLabelMap = new HashMap<>();
        labelToKeyMap = new HashMap<>();
    }


    public Map<String, String> getKeyToLabelMap() {
        return keyToLabelMap;
    }

    public void setKeyToLabelMap(Map<String, String> keyToLabelMap) {
        this.keyToLabelMap = keyToLabelMap;
    }

    public Map<String, List<String>> getLabelToKeyMap() {
        return labelToKeyMap;
    }

    public void setLabelToKeyMap(Map<String, List<String>> labelToKeyMap) {
        this.labelToKeyMap = labelToKeyMap;
    }
}
