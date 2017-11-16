/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
