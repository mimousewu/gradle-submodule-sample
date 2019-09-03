package com.thoughtworks.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.thoughtworks.exceptions.JsonParseException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        if (json == null) {
            return null;
        }

        try {
            return getMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            log.error("Unmarshall JSON error. JSON: {}; Type Ref: {}", json, typeReference, exception);
            throw new JsonParseException(exception);
        }
    }

    public static <T> T toObject(String json, Class<T> clz) {
        if (json == null) {
            return null;
        }

        try {
            return getMapper().readValue(json, clz);
        } catch (IOException exception) {
            log.error("Unmarshall JSON error. JSON: {}; Class: {}", json, clz, exception);
            throw new JsonParseException(exception);
        }
    }

    public static <T> T toObject(Object json, Class<T> clz) {
        if (json == null) {
            return null;
        }
        return getMapper().convertValue(json, clz);
    }

    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }

        try {
            return getMapper().writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            log.error("Marshall to JSON error. Object: {}", object, exception);
            throw new JsonParseException(exception);
        }
    }
}
