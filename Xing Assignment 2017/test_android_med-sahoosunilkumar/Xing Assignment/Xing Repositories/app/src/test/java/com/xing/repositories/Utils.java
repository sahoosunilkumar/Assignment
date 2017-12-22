package com.xing.repositories;

import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.lang.reflect.Field;

public class Utils {

    //////////////////////////////////////////////////////////////////////////
    //                                GIVEN                                 //
    //////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////
    //                                 WHEN                                 //
    //////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////
    //                                 THEN                                 //
    //////////////////////////////////////////////////////////////////////////


    public static boolean setByReflection(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }

    public static JsonReader readFileAsJSONReader(Object object, String file) throws Exception {
        ClassLoader classLoader = object.getClass().getClassLoader();
        String path = classLoader.getResource(file).getPath();
        return new JsonReader(new FileReader(path));
    }

    public static JSONObject readFileAsJSONObject(Object object, String filePath) throws Exception {
        String jsonString = readFileAsString(object, filePath);
        return new JSONObject(jsonString);
    }

    public static JsonReader readStringAsJSONReader(String jsonString) throws Exception {
        StringReader reader = new StringReader(jsonString);
        return new JsonReader(reader);
    }

    public static String readFileAsString(Object object, String filePath) throws Exception {
        ClassLoader classLoader = object.getClass().getClassLoader();
        String absPath = classLoader.getResource(filePath).getPath();

        StringBuilder jsonString = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(absPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line.trim());
            }
        }

        return jsonString.toString();
    }
}
