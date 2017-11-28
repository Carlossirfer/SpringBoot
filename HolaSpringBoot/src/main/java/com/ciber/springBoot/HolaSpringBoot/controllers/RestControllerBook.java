/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.springBoot.HolaSpringBoot.beans.Book;
import com.ciber.springBoot.HolaSpringBoot.rest.BookService;

/**
 * @author ciber
 *
 */
@RestController
@RequestMapping("/api/books")
public class RestControllerBook {
	
	@Autowired
	public BookService bookService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book[] getAllBooks() throws Exception {
		return bookService.getAllBooks();
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book getBook(@PathVariable("bookId") long bookId) throws Exception {

		return bookService.getBook(bookId);

	}

}
