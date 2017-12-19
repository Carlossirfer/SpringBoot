/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ciber.springBoot.HolaSpringBoot.beans.Book;
import com.ciber.springBoot.HolaSpringBoot.beans.Post;
import com.ciber.springBoot.HolaSpringBoot.constants.Constants;

/**
 * @author ciber
 *
 */

@Service
public class ApiService {

	@Autowired
	public RestTemplate restTemplate;

	//OBTENEMOS LIBROS
	public Book[] getAllBooks() throws Exception {
		return restTemplate.getForEntity(Constants.URL_API_BOOKS, Book[].class).getBody();
		
	}

	//OBTENEMOS LIBRO
	public Book getBook(long bookId) throws Exception {
		ResponseEntity<Book> response = restTemplate.getForEntity(Constants.URL_API_BOOKS + bookId, Book.class, 12L);
		return response.getBody();
	}
	
	//OBTENEMOS POSTS
	public Post[] getAllPosts() throws Exception {

		return restTemplate.getForEntity(Constants.URL_API_PRUEBA, Post[].class).getBody();

	}

	//OBTENEMOS POST
	public Post getPost(long postId) throws Exception {

		return restTemplate.getForEntity(Constants.URL_API_PRUEBA + postId, Post.class).getBody();

	}

}
