package com.ciber.springBoot.HolaSpringBoot.exception;

import java.net.SocketTimeoutException;
import java.sql.SQLException;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.client.ResourceAccessException;

import com.ciber.springBoot.HolaSpringBoot.constants.CommonConstants;
import com.ciber.springBoot.HolaSpringBoot.exception.constants.GenericErrorConstants;
import com.ciber.springBoot.HolaSpringBoot.exception.enums.LevelType;

/**
 * @author ciber
 *
 */
public class ExceptionHandler {

    private static final String SQL_CODE_PARSING_STRING = "SQLCODE=";
    private static final String SQL_MESSAGE_PARSING_STRING = "SQLERRMC=";

    private ExceptionHandler() {
        // default constructor.
    }

    /**
     * Encapsula una excepcion tratando su codigo, mensaje y nivel de excepcion.
     * El tratamiento de la excepcion depende de su tipologia. Existen
     * excepciones que deben controlarse de forma explicita en la aplicacion.
     * 
     * @param e
     * @return
     */
    public static GenericException handleGenericException(Exception e) {
        GenericException genericException = new GenericException(e);
        String exceptionErrorCode = CommonConstants.STR_VACIO;
        String exceptionDescription = CommonConstants.STR_VACIO;

        
        /* SQL EXCEPTIONS */
        if (e instanceof EmptyResultDataAccessException) {
            exceptionErrorCode = GenericErrorConstants.DATA_BASE_ERROR;
            exceptionDescription = "EmptyResult";
            genericException.setLevel(LevelType.OK);

        } else if (e instanceof BeanInstantiationException) {
            exceptionErrorCode = GenericErrorConstants.DATA_BASE_ERROR;
            exceptionDescription = "IncorrectDataConversionType";
            genericException.setLevel(LevelType.ERROR);

        } else if (e instanceof DataAccessException) {
            /*
             * DataAccessException debe tratarse de forma explicita por la
             * aplicacion.
             */
        	exceptionErrorCode = getSQLErrorCode(e.getCause().toString());
            exceptionDescription = GenericErrorConstants.ERROR_DEFAULT;
            genericException.setLevel(LevelType.ERROR);

        } else if (e instanceof SQLException) {
            /*
             * SQLException debe tratarse de forma explicita por la aplicacion.
             */
            exceptionErrorCode = Integer.toString(((SQLException) e).getErrorCode());
            exceptionDescription = GenericErrorConstants.ERROR_DEFAULT;
            genericException.setLevel(LevelType.ERROR);

        }  else if (e instanceof GenericException) {
            exceptionErrorCode = ((GenericException) e).getCode();
            exceptionDescription = ((GenericException) e).getErrorMessage();
        } else if (e instanceof ResourceAccessException
                && e.getCause() != null
                && (e.getCause()  instanceof SocketTimeoutException)) {
            exceptionErrorCode = GenericErrorConstants.TIMEOUT_ERROR;
            exceptionDescription = GenericErrorConstants.TIMEOUT_ERROR;
            genericException.setLevel(LevelType.ERROR);
        }

        genericException.setCode(exceptionErrorCode);
        genericException.setErrorMessage(exceptionDescription);

        /* Checks Common Exceptions */
        CommonExceptions ce = new CommonExceptions();
        return ce.checkCommonExceptions(genericException);
    }

    /**
     * Comprueba si el codigo de la excepcion y el que entra como parametro
     * coinciden, de forma que el atributo level sea OK, correspondiendo con una
     * excepcion controlada.
     * 
     * @param e
     * @param code
     * @return
     */
    public static GenericException checkErrorCode(Exception e, String code) {
        GenericException genericException = handleGenericException(e);

        if (code != null
                && !code.isEmpty()
                && code.equals(genericException.getCode() != null ? genericException.getCode()
                        .trim() : genericException.getCode())) {
            genericException.setLevel(LevelType.OK);
        }

        return genericException;
    }

    private static String getSQLErrorCode(String s) {
        if (s != null && !s.isEmpty() && s.toUpperCase().contains(SQL_CODE_PARSING_STRING)) {
            String aux =
                    s.toUpperCase().substring(
                            s.indexOf(SQL_CODE_PARSING_STRING) + SQL_CODE_PARSING_STRING.length());
            return aux.substring(0, aux.indexOf(CommonConstants.STR_COMA));
        }
        return CommonConstants.STR_VACIO;
    }

    private static String getSQLErrorDescription(String s) {
        if (s != null && !s.isEmpty() && s.toUpperCase().contains(SQL_MESSAGE_PARSING_STRING)) {
            String aux =
                    s.toUpperCase().substring(
                            s.indexOf(SQL_MESSAGE_PARSING_STRING)
                                    + SQL_MESSAGE_PARSING_STRING.length());
            return aux.substring(0, aux.indexOf(CommonConstants.STR_COMA));
        }
        return CommonConstants.STR_VACIO;
    }

}
