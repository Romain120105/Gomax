package fr.romain120105.gomax.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonManager {

    private static JsonManager instance = new JsonManager();

    private Gson gson;

    public JsonManager(){
        gson = build();
    }

    public Gson build(){
        ExclusionStrategy strategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }

            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                return field.getAnnotation(Exclude.class) != null;
            }
        };

        return new GsonBuilder().setPrettyPrinting().setExclusionStrategies(strategy).create();
    }

    public Gson getGson() {
        return gson;
    }

    public static JsonManager get(){
        return instance;
    }

}
