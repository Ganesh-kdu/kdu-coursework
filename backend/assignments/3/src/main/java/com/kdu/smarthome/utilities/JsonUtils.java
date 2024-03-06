package com.kdu.smarthome.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
/**
 * Utility class for JSON conversions in API responses
 */
public class JsonUtils {
    /**
     * Convert List of JSON objects to a string
     * @param objList
     * @return List as a string
     * @param <T>
     * @throws JsonProcessingException
     */
    public <T> String convertListToJsonString(List<T> objList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(objList);
    }

    /**
     * Convert JSON object to a string
     * @param object
     * @return Object as string
     * @param <T>
     * @throws JsonProcessingException
     */
    public <T> String convertObjToJsonString(T object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }
}