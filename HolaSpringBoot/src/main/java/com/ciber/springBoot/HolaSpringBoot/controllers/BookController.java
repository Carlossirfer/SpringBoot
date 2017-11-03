/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.springBoot.HolaSpringBoot.HolaSpringBootApplication;
import com.ciber.springBoot.HolaSpringBoot.beans.Book;

/**
 * @author ciber
 *
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
	

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book[]> getAllBooks() {
		ResponseEntity<Book[]> response = HolaSpringBootApplication.restTemplate.getForEntity(HolaSpringBootApplication.URL_API_BOOKS, Book[].class);
		return response;
				
	}
    
    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@PathVariable("bookId") long bookId) {
    	 ResponseEntity<Book> response = HolaSpringBootApplication.restTemplate.getForEntity(HolaSpringBootApplication.URL_API_BOOKS + bookId, Book.class, 12L);
    	 return response.getBody();
    
    }

}
