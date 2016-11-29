package com.badoo.assignment.productviewer.controller;

/**
 * Created by sunilkumarsahoo on 11/16/16.
 */

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 * Created by sunilkumarsahoo on 7/5/16.
 */
public class Parser {
    public final static Parser INSTANCE = new Parser();
    private static Gson gson;

    private Parser() {
        initializeParser();
    }

    public <T> T parse(String response, Class<?> type) {
        JsonReader reader = new JsonReader(new StringReader(response));
        reader.setLenient(true);

        return gson.fromJson(reader, type);
    }

    private void initializeParser() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy
                        .LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
}
