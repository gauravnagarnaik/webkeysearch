package com.gaurav.model;

/**
 * Created by Gaurav on 4/29/2017.
 *
 * This creates the response body for the JSON object and returns the translation of the given key
 */
public class AjaxResponseBody {
    private Label translation;
    //String msg;

    public Label getTranslation() {
        return translation;
    }

    public void setTranslation(Label translation) {
        this.translation = translation;
    }
}
