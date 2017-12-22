/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciber.springBoot.HolaSpringBoot.beans.Book;
import com.ciber.springBoot.HolaSpringBoot.beans.Post;
import com.ciber.springBoot.HolaSpringBoot.beans.Usuario;
import com.ciber.springBoot.HolaSpringBoot.controllers.ApiService;
import com.ciber.springBoot.HolaSpringBoot.controllers.DaoMongo;
import com.ciber.springBoot.HolaSpringBoot.controllers.EmailController;
import com.ciber.springBoot.HolaSpringBoot.exception.ExceptionHandler;
import com.ciber.springBoot.HolaSpringBoot.util.json.JsonResponse;

/**
 * @author ciber
 *
 */
@Service
public class CapaHandler {

	@Autowired
	private ApiService bookService;

	@Autowired
	private DaoMongo daoMongo;
	
	@Autowired
	private EmailController emailController;

	// OBTENEMOS LIBROS DE API
	public JsonResponse obtenerLibros() {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.addData("libros", bookService.getAllBooks());
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ExceptionHandler.handleGenericException(e));
		}
		return jsonResponse;
	}

	// OBTENEMOS UN LIBRO DE API
	public JsonResponse obtenerLibro(Book libro) {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.addData("libro", bookService.getBook(libro.getId()));
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ExceptionHandler.handleGenericException(e));
		}
		return jsonResponse;
	}

	// OBTENEMOS POSTS DE API
	public JsonResponse obtenerPosts() {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.addData("posts", bookService.getAllPosts());
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ExceptionHandler.handleGenericException(e));
		}
		return jsonResponse;
	}

	// OBTENEMOS UN POST DE API
	public JsonResponse obtenerPost(Post post) {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.addData("post", bookService.getPost(post.getId()));
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ExceptionHandler.handleGenericException(e));
		}
		return jsonResponse;
	}

	// OBTENEMOS USUARIOS DE MONGO
	public JsonResponse handlerUsuariosMongo() {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.addData("usuarios", daoMongo.getAllUsers());
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ExceptionHandler.handleGenericException(e));
		}
		return jsonResponse;
	}

	// OBTENEMOS USUARIOS DE MONGO
	public JsonResponse handlerUsuariosMongo(Usuario usuario) {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse.addData("usuarios", daoMongo.searchUsers(usuario));
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ExceptionHandler.handleGenericException(e));
		}
		return jsonResponse;
	}

	// AÑADIMOS USUARIO EN MONGO
	public JsonResponse handlerAddUsuarioMongo(Usuario usuario) {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			daoMongo.addUserMongo(usuario);
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ExceptionHandler.handleGenericException(e));
		}
		return jsonResponse;
	}

	// BORRAMOS UN USUARIO EN MONGO
	public JsonResponse borrarUsuarioMongo(Usuario usuario) {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			daoMongo.deleteUserMongo(usuario);
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ExceptionHandler.handleGenericException(e));
		}
		return jsonResponse;
	}

	// OBTENEMOS USUARIOS PARA EL PDF
	public List<Usuario> pdfUsuarios() {
		return daoMongo.getAllUsers();
	}
	
	//ENVIAR EMAIL
	public JsonResponse enviarEmail(Usuario usuario){
		JsonResponse jsonResponse=new JsonResponse();
		try {
			emailController.enviarEmail(usuario);
		} catch (Exception e) {
			jsonResponse = new JsonResponse(ExceptionHandler.handleGenericException(e));
		}
		return jsonResponse;
	}

}
