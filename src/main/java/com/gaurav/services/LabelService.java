package com.gaurav.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaurav.model.Label;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gaurav on 4/29/2017.
 *
 * Inits the data from the translation.json file in a map and finds the label associated with the key
 */


/**
 * Service to find the required key from the translationMap genetrated through the ObjectMapper
 */
@Service
public class LabelService {
    private Label label;
    private Map<String, Object> translationMap = null;


    /**
     * function to find the label or translation of given key from the translationMap
     * @param key
     * @return Label object
     */
    public Label findTranslationFromKey(String key){
        Label result = null;
        if(translationMap == null || key == null) {
            return result;
        }

        //Split the requested key and find each component of the key query

        String[] parts = key.split("\\.");
        if(parts.length == 1){
            if(translationMap.containsKey(parts[0])) {
                Object obj = translationMap.get(parts[0]);
                if(obj instanceof String) {
                    this.label = new Label(key, obj);
                    result = this.label;
                }
            }
            return result;
        } else{
            if(!translationMap.containsKey(parts[0])){
                return result;
            } else {
                result = findNode(translationMap, parts, 0);
            }
        }
        return result;
    }

    /**
     * function to find node in case of nested JSON objects
     * @param map
     * @param parts
     * @param i
     * @return Label object
     */
    private Label findNode(Map<String, Object> map, String[] parts, int i) {

        if(i == parts.length){
            return null;
        }

        /* If given key's value is String, return as that's the end.
         * Else call the function with Object Map
         */
        Object obj = map.get(parts[i]);
        if (obj instanceof String) {
            return new Label(parts[i], obj);
        } else if (obj instanceof Map) {
            if(i == parts.length - 1){
                return null;
            }
            return findNode((Map<String, Object>) obj, parts, ++i);
        } else {
            return null;
        }
    }


    /**
     * function to initialize the data from "translation.json" file. To get your own file, make changes in path below.
     * Annotated as PostConstruct
     */

    @PostConstruct
    private void initData(){

        ObjectMapper jsonMapper = new ObjectMapper();

        try {
            translationMap = jsonMapper.readValue(new File("translation.json"), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
