package com.ciber.springBoot.HolaSpringBoot.exception;

import org.springframework.beans.BeanUtils;

import com.ciber.springBoot.HolaSpringBoot.exception.constants.GenericErrorConstants;
import com.ciber.springBoot.HolaSpringBoot.exception.enums.LevelType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Ciber
 *
 */
@JsonIgnoreProperties(value = { "stackTrace", "suppressed" })
public class GenericException extends Exception {

    private static final long serialVersionUID = 972749430108677483L;

    private String code;

    private String errorMessage;

    private LevelType level;

    /**
     * Generates a new GenericException with error code, error message and a
     * defined importance in case the instantiating exception is a controlled
     * Warning.
     * 
     * @param errorCode
     * @param errorMessage
     * @param level
     * @param importance
     */
    public GenericException(String errorCode, String errorMessage, LevelType level) {
        this.level = level;
        this.code = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Generates a new GenericException with error code and error message.
     * 
     * @param errorCode
     * @param errorMessage
     */
    public GenericException(String errorCode, String errorMessage) {
        this.level = LevelType.ERROR;
        this.code = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Generates a new GenericException with error code.
     * 
     * @param errorCode
     */
    public GenericException(String errorCode) {
        this.level = LevelType.ERROR;
        this.code = errorCode;
    }

    /**
     * Generates default GenericException for non handled errors.
     * 
     * @param e
     */
    public GenericException(Exception e) {

        BeanUtils.copyProperties(e, this);

        this.level = LevelType.ERROR;
        this.code = GenericErrorConstants.ERROR_DEFAULT;
        this.errorMessage = e.getMessage();

    }

    /**
     * @return the errorCode
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the errorCode to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the importance
     */
    public LevelType getLevel() {
        return level;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(LevelType level) {
        this.level = level;
    }

}
