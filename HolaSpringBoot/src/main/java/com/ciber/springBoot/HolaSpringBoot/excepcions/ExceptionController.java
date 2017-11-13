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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {


	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(Exception e) {
		ModelAndView model = new ModelAndView();
		//CONTROL DEL ERROR 404
		if (e.getClass()==HttpClientErrorException.class) {
			model.addObject("excepcion", e);
			model.setViewName("/error/404");
			return model;
		}
		model.addObject("excepcion", e);
		model.setViewName("error");
		return model;
	}

}