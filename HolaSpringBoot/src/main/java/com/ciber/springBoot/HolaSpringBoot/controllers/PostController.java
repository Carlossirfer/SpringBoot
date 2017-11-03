package com.ciber.springBoot.HolaSpringBoot.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.springBoot.HolaSpringBoot.HolaSpringBootApplication;
import com.ciber.springBoot.HolaSpringBoot.beans.Post;

/**
 * @author ciber
 *
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {
	

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post[]> getAllPosts() {
		ResponseEntity<Post[]> response = HolaSpringBootApplication.restTemplate.getForEntity(HolaSpringBootApplication.URL_API_PRUEBA, Post[].class);
		return response;
				
	}
    
    @RequestMapping(value = "/{postId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Post getPost(@PathVariable("postId") long postId) {
    	 ResponseEntity<Post> response = HolaSpringBootApplication.restTemplate.getForEntity(HolaSpringBootApplication.URL_API_PRUEBA + postId, Post.class);
    	 return response.getBody();
    
    }

}
