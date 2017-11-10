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
import com.ciber.springBoot.HolaSpringBoot.constants.Constants;

/**
 * @author ciber
 *
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book[]> getAllBooks() throws Exception {
		try {
			ResponseEntity<Book[]> response = HolaSpringBootApplication.restTemplate
					.getForEntity(Constants.URL_API_BOOKS, Book[].class);
			return response;
		} catch (Exception e) {
			throw new Exception("Error en BookController, getAllBooks(): " + e.getMessage() + " : " + e.getCause());
		}
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book getBook(@PathVariable("bookId") long bookId) throws Exception {
		try {
			ResponseEntity<Book> response = HolaSpringBootApplication.restTemplate
					.getForEntity(Constants.URL_API_BOOKS + bookId, Book.class, 12L);
			return response.getBody();
		} catch (Exception e) {
			throw new Exception("Error en BookController, getBook(): " + e.getMessage() + " : " + e.getCause());
		}

	}

}
