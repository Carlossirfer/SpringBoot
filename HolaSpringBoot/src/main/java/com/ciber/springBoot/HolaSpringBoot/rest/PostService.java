/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ciber.springBoot.HolaSpringBoot.beans.Post;
import com.ciber.springBoot.HolaSpringBoot.constants.Constants;

/**
 * @author ciber
 *
 */
@Service
public class PostService {

	@Autowired
	RestTemplate restTemplate;

	public Post[] getAllPosts() throws Exception {

		return restTemplate.getForEntity(Constants.URL_API_PRUEBA, Post[].class).getBody();

	}

	public Post getPost(long postId) throws Exception {

		return restTemplate.getForEntity(Constants.URL_API_PRUEBA + postId, Post.class).getBody();

	}

}
