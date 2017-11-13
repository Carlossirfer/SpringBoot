/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ciber.springBoot.HolaSpringBoot.beans.Book;
import com.ciber.springBoot.HolaSpringBoot.rest.BookService;

/**
 * @author ciber
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class, secure = false)
public class BookContollerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	public BookService bookService;

	Book Mockbook = new Book(20L, "title");
	
	Book[] MockBooks={Mockbook,Mockbook};

	String Jsonbook = "{\"id\":\"20\",\"title\":\"titulo\",\"description\":\"description\"}";
	String Jsonbooks="[{\"id\":\"20\",\"title\":\"titulo\"}]";
	
	
	@Test
	public void getAllBooks() throws Exception {
		Mockito.when(bookService.getAllBooks()).thenReturn(MockBooks);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/api/books").accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		System.out.println("RESULTADO DE LA PRUEBA TEST GETALLBOOKS:---------"+result);
	}

	@Test
	public void getBook() throws Exception {
		Mockito.when(bookService.getBook(Mockito.anyLong())).thenReturn(Mockbook);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/2").accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		System.out.println("RESULTADO DE LA PRUEBA TEST GETBOOK:---------"+result);
	

	}

}
