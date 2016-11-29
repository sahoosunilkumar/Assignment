/**
 * Responsibilty: To initialize parser
 */
package com.logitech.inventorycontoller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 * Created by sunilkumarsahoo on 7/5/16.
 */
public class Parser {
    private static Gson gson;

    private Parser(){
        initializeParser();
    }

    public final static Parser INSTANCE = new Parser();

    public Object parse(String response, Class<?> type){
        JsonReader reader = new JsonReader(new StringReader(response));
        reader.setLenient(true);

        return gson.fromJson(reader, type);
    }
    private void initializeParser(){
        gson= new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
}
