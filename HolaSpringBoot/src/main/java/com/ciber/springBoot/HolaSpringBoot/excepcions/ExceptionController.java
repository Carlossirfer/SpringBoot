/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.excepcions;

/**
 * @author ciber
 *
 */
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
 
@ControllerAdvice
public class ExceptionController {
 
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e){
    	ModelAndView model=new ModelAndView("error");
    	model.addObject("excepcion", e);
        return model;
    }
}