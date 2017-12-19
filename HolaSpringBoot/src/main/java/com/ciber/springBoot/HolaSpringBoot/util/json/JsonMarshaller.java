package com.ciber.springBoot.HolaSpringBoot.util.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author ciber
 *
 */
public class JsonMarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(JsonMarshaller.class);
    private static final String ERROR_READING_JSON = "Error convirtiendo desde JSON: ";
    private static final String ERROR_WRITING_JSON = "Error convirtiendo a JSON: ";

    private JsonMarshaller() {
        // default constructor
    }


    /**
     * Convierte un JSON de forma estatica a un objeto determinado mediante un
     * ObjectMapper de Jackson
     * 
     * @param json
     * @param type
     * @return
     */
    public static <T> Object jsonToObject(String json, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        LOG.debug("JSON de entrada: ", json);

        try {
            return mapper.readValue(json, type);

        } catch (JsonParseException e) {
            LOG.error(ERROR_READING_JSON + json, e);
        } catch (JsonMappingException e) {
            LOG.error(ERROR_READING_JSON + json, e);
        } catch (IOException e) {
            LOG.error(ERROR_READING_JSON + json, e);
        }
        return null;
    }

    /**
     * Convierte de forma estatica un objeto a JSON mediante un ObjectMapper de
     * Jackson.
     * 
     * @param o
     * @return
     */
    @JsonCreator
    public static String objectToJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        mapper.setSerializationInclusion(Include.NON_NULL);

        try {
            String result = mapper.writeValueAsString(o);
            LOG.debug("JSON RESPONSE: \n" + result);
            return result;
        } catch (JsonProcessingException e) {
            LOG.error(ERROR_WRITING_JSON + o.toString(), e);
        }
        return null;
    }

}
