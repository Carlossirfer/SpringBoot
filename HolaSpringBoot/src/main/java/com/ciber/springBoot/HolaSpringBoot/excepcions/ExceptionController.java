/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.excepcions;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class ExceptionController {


	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(Exception e) {
		System.out.println("EXCEPTION-----------------"+e.getClass());
		ModelAndView model = new ModelAndView();
		//CONTROL DEL ERROR 404
		if (e.getClass()==HttpClientErrorException.class) {
			model.addObject("excepcion", e);
			model.setViewName("/errores/404");
			return model;
		}
		if (e.getClass()==AccessDeniedException.class) {
			model.setViewName("/errores/403");
			return model;
		}
		model.addObject("excepcion", e);
		model.setViewName("error");
		return model;
	}
	
	
	  @ExceptionHandler({NoHandlerFoundException.class})
	    public ModelAndView handle404(Exception e) {
	       ModelAndView model=new ModelAndView();
	       model.setViewName("error");
	       model.addObject("excepcion",e);
	       return model;
	    }
	  
	  
	  @GetMapping("/404")
	  public String error404(){
		  return "/error/404";
	  }

}