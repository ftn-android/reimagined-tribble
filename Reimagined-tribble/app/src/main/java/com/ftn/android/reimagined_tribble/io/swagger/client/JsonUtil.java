package com.ftn.android.reimagined_tribble.io.swagger.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import com.ftn.android.reimagined_tribble.io.swagger.client.model.*;

public class JsonUtil {
    public static GsonBuilder gsonBuilder;

    static {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    public static Gson getGson() {
        return gsonBuilder.create();
    }

    public static String serialize(Object obj) {
        return getGson().toJson(obj);
    }

    public static <T> T deserializeToList(String jsonString, Class cls) {
        return getGson().fromJson(jsonString, getListTypeForDeserialization(cls));
    }

    public static <T> T deserializeToObject(String jsonString, Class cls) {
        return getGson().fromJson(jsonString, getTypeForDeserialization(cls));
    }

    public static Type getListTypeForDeserialization(Class cls) {
        String className = cls.getSimpleName();

        if ("HeaderImage".equalsIgnoreCase(className)) {
            return new TypeToken<List<HeaderImage>>() {
            }.getType();
        }

        if ("Location".equalsIgnoreCase(className)) {
            return new TypeToken<List<Location>>() {
            }.getType();
        }

        if ("User".equalsIgnoreCase(className)) {
            return new TypeToken<List<User>>() {
            }.getType();
        }

        return new TypeToken<List<Object>>() {
        }.getType();
    }

    public static Type getTypeForDeserialization(Class cls) {
        String className = cls.getSimpleName();

        if ("HeaderImage".equalsIgnoreCase(className)) {
            return new TypeToken<HeaderImage>() {
            }.getType();
        }

        if ("Location".equalsIgnoreCase(className)) {
            return new TypeToken<Location>() {
            }.getType();
        }

        if ("User".equalsIgnoreCase(className)) {
            return new TypeToken<User>() {
            }.getType();
        }

        return new TypeToken<Object>() {
        }.getType();
    }

};
