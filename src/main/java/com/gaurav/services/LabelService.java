package com.gaurav.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaurav.model.Label;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gaurav on 4/29/2017.
 *
 * Inits the data from the translation.json file in a map and finds the label associated with the key
 */

@Service
public class LabelService {
    private List<Label> labels;
    private Map<String, Object> translationMap = null;


    public Object findTranslationFromKey(String key){
        Object result = null;
        if(translationMap == null || key == null) {
            return result;
        }

        String[] parts = key.split("\\.");
        if(parts.length == 0){
            Object obj = translationMap.get(key);
            if(obj instanceof String){
                result = obj;
            }
            return result;
        }
        if(parts.length == 1){
            if(translationMap.containsKey(parts[0])) {
                Object obj = translationMap.get(parts[0]);
                if(obj instanceof String){
                    result = obj;
                } else if(obj instanceof Map){
                    return obj;
                }
            }
        } else{
            if(!translationMap.containsKey(parts[0])){
                return result;
            } else {
                result = findNode(translationMap, parts, 0);
            }
        }
        return result;
    }

    private Object findNode(Map<String, Object> map, String[] parts, int i) {

        if(i == parts.length){
            return null;
        }

        Object obj = map.get(parts[i]);
        if (obj instanceof String) {
            return obj;
        } else if (obj instanceof Map) {
            if(i == parts.length - 1){
                return obj;
            }
            return findNode((Map<String, Object>) obj, parts, ++i);
        } else {
            return null;
        }
    }


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
