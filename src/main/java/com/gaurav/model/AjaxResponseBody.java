package com.gaurav.model;

/**
 * Created by Gaurav on 4/29/2017.
 *
 * This creates the response body for the JSON object and returns the translation of the given key
 */
public class AjaxResponseBody {
    private Object translation;
    //String msg;

    public Object getTranslation() {
        return translation;
    }

    public void setTranslation(Object translation) {
        this.translation = translation;
    }

   /* public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }*/
}
