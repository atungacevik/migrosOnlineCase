package com.example.migrosOnlineCase.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectMapperFactory {
    public ObjectMapperFactory() {
    }
    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.getFactory().disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
        return objectMapper;
    }
}
