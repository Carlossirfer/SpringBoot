/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ciber.springBoot.HolaSpringBoot.beans.Book;
import com.ciber.springBoot.HolaSpringBoot.constants.Constants;

/**
 * @author ciber
 *
 */

@Service
public class BookService {

	@Autowired
	public RestTemplate restTemplate;

	public Book[] getAllBooks() throws Exception {
		return restTemplate.getForEntity(Constants.URL_API_BOOKS, Book[].class).getBody();
		
	}

	public Book getBook(long bookId) throws Exception {
		ResponseEntity<Book> response = restTemplate.getForEntity(Constants.URL_API_BOOKS + bookId, Book.class, 12L);
		return response.getBody();
	}

}
