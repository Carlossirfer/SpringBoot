package com.ciber.springBoot.HolaSpringBoot.exception;

import java.util.HashMap;
import java.util.Map;

import com.ciber.springBoot.HolaSpringBoot.exception.enums.LevelType;


/**
 * @author ciber
 *
 */
public class CommonExceptions {

    private Map<String, GenericException> exceptions;

    private static final String FAILED_AUTHENTICATION_CODE = "FailedAuthentication";
    private static final String FAILED_AUTHENTICATION_DESC = "Error de autentificación";

    private static final String NO_ACCOUNTS_CODE = "NO_ACCOUNTS";
    private static final String NO_ACCOUNTS_DESC =
            "No dispones de cuentas para realizar la contratación.";

    /**
     * Constructor
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public CommonExceptions() {
        this.exceptions = new HashMap();
        this.exceptions.put(FAILED_AUTHENTICATION_CODE, new GenericException(
                FAILED_AUTHENTICATION_CODE, FAILED_AUTHENTICATION_DESC, LevelType.ERROR));
        this.exceptions.put(NO_ACCOUNTS_CODE,
                new GenericException(NO_ACCOUNTS_CODE, NO_ACCOUNTS_DESC, LevelType.ERROR));
    }

    /**
     * Comprueba si el codigo de la excepcion que entra como parametro coincide
     * con los ya cargados en el mapa, atributo de la clase.
     * 
     * @param ge
     * @return
     */
    public GenericException checkCommonExceptions(GenericException ge) {
        if (ge != null && ge.getCode() != null && this.getExceptions().get(ge.getCode()) != null) {
            return this.getExceptions().get(ge.getCode());
        }
        return ge;
    }

    /**
     * @return the exceptions
     */
    public Map<String, GenericException> getExceptions() {
        return exceptions;
    }

    /**
     * @param exceptions
     *            the exceptions to set
     */
    public void setExceptions(Map<String, GenericException> exceptions) {
        this.exceptions = exceptions;
    }
}
