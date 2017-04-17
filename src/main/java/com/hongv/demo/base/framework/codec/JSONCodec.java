package com.hongv.demo.base.framework.codec;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;

/**
 * 将对象转Json， 将Json转对象（类似Android里的Gson框架）
 */
public abstract class JSONCodec {

    static com.fasterxml.jackson.databind.ObjectMapper mapper = null;

    static {
        mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException( //
                    "parse json fail: requiredType=" + typeReference + ", json=" + json, e);
        }
    }

    public static <T> T decode(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException( //
                    "parse json fail: requiredType=" + clazz + ", json=" + json, e);
        }
    }

    public static <T> String encode(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("write json fail: " + object, e);
        }
    }
}
