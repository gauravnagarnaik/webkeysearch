package com.gaurav.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaurav.model.Label;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Gaurav on 4/29/2017.
 *
 * Initializes the data from the translation.json file in a map, which further processed to a bi-directional data structure
 * Also, find key and reverse look Up functions here
 */


/**
 * Service to find the required key from the translationMap genetrated through the ObjectMapper
 */
@Service
public class LabelSearchService {

    private Label label;


    /**
     * function to find the label or translation of given key from the translationMap
     * @param key String
     * @return String
     */
    public String findTranslation(String key){
        if(key == null || key.length() == 0){
            return null;
        }
        return label.getKeyToLabelMap().get(key);
    }


    /**
     * function for reverse lookUp
     * @param key String
     * @return String
     */
    public String findReverseTranslation(String key){
        if(key == null || key.length() == 0){
            return null;
        }
        if(label.getLabelToKeyMap().get(key) != null){
            StringBuilder result = new StringBuilder();
            List<String> lst = label.getLabelToKeyMap().get(key);
            for(String str : lst){
                result.append(str);
                result.append(",");
            }
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
        return null;
    }


    /**
     * function to initialize the data from "translation.json" file. To get your own file, make changes in path below.
     * Annotated as PostConstruct
     */

    @PostConstruct
    private void initData(){

        ObjectMapper jsonMapper = new ObjectMapper();
        Map<String, Object> translationMap = null;
        try {
            translationMap = jsonMapper.readValue(new File("translation.json"), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        label = new Label();
        List<String> keyList = new ArrayList<>();
        organizeInitData(translationMap, keyList);

    }

    /**
     * function to organize the data in Bi-Directional structure defined in Label
     * @param tMap Map
     * @param keyList List
     */
    private void organizeInitData(Map<String, Object> tMap, List<String> keyList) {
        Iterator it = tMap.keySet().iterator();

        while (it.hasNext()) {
            String k = (String) it.next();
            Object obj = tMap.get(k);

            if (obj instanceof String) {
                if (keyList == null || keyList.size() == 0) {
                    putDataInMap(k, (String) obj);
                } else {
                    StringBuilder key = new StringBuilder();
                    for (int i = 0; i < keyList.size(); i++) {
                        key.append(keyList.get(i));
                        key.append(".");
                    }
                    key.append(k);
                    keyList.add(key.toString());
                    putDataInMap(key.toString(), (String) obj);
                }
            } else if (obj instanceof Map) {
                keyList.add(k);
                organizeInitData((Map) obj, keyList);
            }

            if (!keyList.isEmpty()) {
                keyList.remove(keyList.size() - 1);
            }
        }
    }


    /**
     * function to put the data into custom Bi-directional structure
     * @param key String
     * @param value String
     */
    private void putDataInMap(String key, String value){
        if(key.length() == 0 || value.length() == 0){
            return;
        }

        List<String> lst;

        label.getKeyToLabelMap().put(key, value);
        if (label.getLabelToKeyMap().get(value) != null) {
            lst = label.getLabelToKeyMap().get(value);
        } else {
            lst = new ArrayList<>();
        }
        lst.add(key);
        label.getLabelToKeyMap().put(value, lst);
    }

}
