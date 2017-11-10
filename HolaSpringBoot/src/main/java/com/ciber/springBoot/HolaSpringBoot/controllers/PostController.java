package com.ciber.springBoot.HolaSpringBoot.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ciber.springBoot.HolaSpringBoot.HolaSpringBootApplication;
import com.ciber.springBoot.HolaSpringBoot.beans.Post;
import com.ciber.springBoot.HolaSpringBoot.constants.Constants;

/**
 * @author ciber
 *
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private HttpSession httpSesion;

	@Secured({ "ROLE_USER" })
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllPosts() throws Exception {
		
			ModelAndView model=new ModelAndView("posts");
			ResponseEntity<Post[]> response = HolaSpringBootApplication.restTemplate
					.getForEntity(Constants.URL_API_PRUEBA, Post[].class);
			model.addObject("posts", response.getBody());
			model.addObject("usuario",httpSesion.getAttribute("usuario").toString());
			model.addObject("roles", httpSesion.getAttribute("roles").toString());
			return model;

	}

	@Secured({ "ROLE_USER" })
	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public ModelAndView getPost(@PathVariable("postId") long postId) throws Exception {
		try {
			ModelAndView model=new ModelAndView("posts");
			ResponseEntity<Post> response = HolaSpringBootApplication.restTemplate
					.getForEntity(Constants.URL_API_PRUEBA+postId, Post.class);
			model.addObject("posts", response.getBody());
			model.addObject("usuario",httpSesion.getAttribute("usuario").toString());
			model.addObject("roles", httpSesion.getAttribute("roles").toString());
			return model;
		} catch (Exception e) {
			throw new Exception("Error en PostController, getPost(): " + e.getMessage() + " : " + e.getCause());
		}
	}
}
