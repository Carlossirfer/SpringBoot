package com.ciber.springBoot.HolaSpringBoot.util.json;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ciber.springBoot.HolaSpringBoot.exception.GenericException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Ciber
 */
public class JsonResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(JsonResponse.class);

    private ResponseType status;

    @JsonInclude(Include.NON_NULL)
    private Map<String, Object> data;

    @JsonProperty("error")
    @JsonInclude(Include.NON_NULL)
    private GenericException error;

    /**
     * Constructor que devuelve mensajes con estado satisfactorio (SUCCESSFUL).
     * Inicializa el mapa de datos a introducir en el JSON.
     */
    public JsonResponse() {
        super();
        status = ResponseType.SUCCESSFUL;
        data = new HashMap<String, Object>();
    }

    /**
     * Constructor de mensajes de error. Devuelve un status FAILED y el codigo y
     * mensaje de la GenericException de parametro.
     * 
     * @param genericException
     */
    public JsonResponse(GenericException genericException) {
        this.status = ResponseType.FAILED;
        this.error = genericException;
    }

    /**
     * Genera un JSON de con los datos del objeto actual. Se hace de forma no
     * estatica y mediante el ObjectMapper de Jackson.
     * 
     * @return
     */
    @JsonCreator
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        mapper.setSerializationInclusion(Include.NON_NULL);

        try {
            return mapper.writeValueAsString(this);

        } catch (JsonProcessingException e) {
            LOG.error("Error convirtiendo a JSON: ", e);
        }
        return null;
    }

    public ResponseType getStatus() {
        return status;
    }

    public void setStatus(ResponseType status) {
        this.status = status;
    }

    /**
     * 
     * @param key
     * @param value
     */
    public void addData(String key, Object value) {
        data.put(key, value);
    }

    public Map<String, Object> getData() {
        return data;
    }

    /**
     * 
     * @author ciber
     *
     */
    public enum ResponseType {
        SUCCESSFUL("SUCCESSFUL"), FAILED("FAILED");

        private String message;

        private ResponseType(String s) {
            message = s;
        }

        public String getMessage() {
            return message;
        }
    }

}
