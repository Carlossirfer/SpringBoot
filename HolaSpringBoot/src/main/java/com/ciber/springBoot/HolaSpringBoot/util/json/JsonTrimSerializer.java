package com.ciber.springBoot.HolaSpringBoot.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author ciber
 *
 */
public class JsonTrimSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String data, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeObject(data != null ? data.trim() : null);
    }
}