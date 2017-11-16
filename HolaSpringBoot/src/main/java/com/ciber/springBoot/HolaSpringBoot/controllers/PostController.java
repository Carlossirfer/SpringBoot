package com.ciber.springBoot.HolaSpringBoot.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ciber.springBoot.HolaSpringBoot.rest.PostService;

/**
 * @author ciber
 *
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private HttpSession httpSesion;

	@Autowired
	public PostService postService;

	@Secured({ "ROLE_USER" })
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllPosts() throws Exception {
		ModelAndView model = new ModelAndView("posts");
		model.addObject("posts", postService.getAllPosts());
		model.addObject("usuario", httpSesion.getAttribute("usuario").toString());
		model.addObject("roles", httpSesion.getAttribute("roles").toString());
		return model;
	}

	@Secured({ "ROLE_USER" })
	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public ModelAndView getPost(@PathVariable("postId") long postId) throws Exception {
		ModelAndView model = new ModelAndView("posts");
		model.addObject("posts", postService.getPost(postId));
		model.addObject("usuario", httpSesion.getAttribute("usuario").toString());
		model.addObject("roles", httpSesion.getAttribute("roles").toString());
		return model;
	}
}
