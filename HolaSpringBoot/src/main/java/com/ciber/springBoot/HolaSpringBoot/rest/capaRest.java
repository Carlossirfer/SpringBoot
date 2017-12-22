/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ciber.springBoot.HolaSpringBoot.beans.Book;
import com.ciber.springBoot.HolaSpringBoot.beans.Post;
import com.ciber.springBoot.HolaSpringBoot.beans.Usuario;
import com.ciber.springBoot.HolaSpringBoot.constants.RestConstants;
import com.ciber.springBoot.HolaSpringBoot.handler.CapaHandler;
import com.ciber.springBoot.HolaSpringBoot.util.CreatePdfCat;
import com.ciber.springBoot.HolaSpringBoot.util.json.JsonResponse;

/**
 * @author ciber
 *
 */
@RestController
public class capaRest {

	@Autowired
	private CapaHandler handler;

	/*
	 * API REST
	 */
	// OBTENER LIBROS
	@GetMapping(value = RestConstants.OBTENER_LIBROS, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public JsonResponse obtenerLibros() {
		return handler.obtenerLibros();
	}

	// OBTENER UN LIBRO
	@PostMapping(value = RestConstants.OBTENER_LIBRO, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public JsonResponse obtenerLibro(@RequestBody Book libro) {
		return handler.obtenerLibro(libro);
	}

	// OBTENER POSTS
	// @Secured({ "ROLE_USER" })
	@GetMapping(value = RestConstants.OBTENER_POSTS, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public JsonResponse obtenerPosts() {
		return handler.obtenerPosts();
	}

	// OBTENER UN POST
	// @Secured({ "ROLE_USER" })
	@PostMapping(value = RestConstants.OBTENER_POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public JsonResponse obtenerPost(@RequestBody Post post) {
		return handler.obtenerPost(post);
	}

	/*
	 * API REST
	 */

	/*
	 * MONGO
	 */
	// OBTENER USUARIOS MONGO
	// @Secured({ "ROLE_ADMIN" })
	@GetMapping(value = RestConstants.OBTENER_USUARIOS_MONGO, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public JsonResponse obtenerUsuariosMongo() {
		return handler.handlerUsuariosMongo();
	}

	// OBTENER USUARIOS MONGO FILTRO
	// @Secured({ "ROLE_ADMIN" })
	@PostMapping(value = RestConstants.OBTENER_USUARIOS_MONGO, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public JsonResponse obtenerUsuariosMongoFiltro(Usuario usuario) {
		return handler.handlerUsuariosMongo(usuario);
	}

	// INSERTAR USUARIO EN MONGO
	// @Secured({ "ROLE_ADMIN" })
	@PostMapping(value = RestConstants.AÑADIR_USUARIO_MONGO, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public JsonResponse añadirUsuarioMongo(@RequestBody Usuario usuario) {
		return handler.handlerAddUsuarioMongo(usuario);
	}

	// BORRAR UN USUARIO EN MONGO
	// @Secured({ "ROLE_ADMIN" })
	@PostMapping(value = RestConstants.BORRAR_USUARIO_MONGO, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public JsonResponse borrarUsuarioMongo(@RequestBody Usuario usuario) {
		return handler.borrarUsuarioMongo(usuario);
	}
	/*
	 * MONGO
	 */

	/*
	 * LLAMADAS A VISTAS HTML
	 */

	@RequestMapping("/")
	public ModelAndView index() throws Exception {
		return new ModelAndView("index");
	}

	@RequestMapping("/home")
	public ModelAndView home() throws Exception {
		return new ModelAndView("home");
	}

	@RequestMapping("/login")
	public ModelAndView login() throws Exception {
		return new ModelAndView("login");
	}

	/*
	 * LLAMADAS A VISTAS HTML
	 */

	
	/*
	 * GENERAR PDF
	 */
//
	@GetMapping(value = RestConstants.GENERAR_PDF_USUARIOS, produces = { MediaType.APPLICATION_PDF_VALUE })
	public void generarPdf() {
		CreatePdfCat createPdf=new CreatePdfCat();
		try {
			createPdf.CreatePdf(handler.pdfUsuarios());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		ByteArrayInputStream bis = GeneratePdfReport.citiesReport(lista);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
//
//		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
//				.body(new InputStreamResource(bis));
	}
	
	/*
	 * GENERAR PDF
	 */
	
	
	/*
	 * ENVIAR EMAIL
	 */

	// ENVIAR EMAIL
		// @Secured({ "ROLE_ADMIN" })
		@PostMapping(value = RestConstants.ENVIAR_EMAIL, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
		public JsonResponse enviarEmail(@RequestBody Usuario usuario) {
			return handler.enviarEmail(usuario);
		}

	/*
	 * ENVIAR EMAIL
	 */
	
	
}



