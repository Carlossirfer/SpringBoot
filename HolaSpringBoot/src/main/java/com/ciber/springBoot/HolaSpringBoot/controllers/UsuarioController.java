/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ciber.springBoot.HolaSpringBoot.beans.Usuario;
import com.ciber.springBoot.HolaSpringBoot.daoMongo.DaoUsers;

/**
 * @author ciber
 *
 */
@Controller
public class UsuarioController {

	@Autowired
	DaoUsers daoUsers;

	@Autowired
	@Qualifier(value = "mongoTemplateBasePrueba")
	MongoTemplate mongoApp;

	@Autowired
	@Qualifier(value = "mongoTemplateUsuariosLogin")
	private MongoTemplate mongoLogin;

//	@Autowired
//	private HttpSession httpSesion;

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping("/mongo")
	public ModelAndView mongo(Model model) throws Exception {
		try {
//			model.addAttribute("usuario", httpSesion.getAttribute("usuario").toString());
//			model.addAttribute("roles", httpSesion.getAttribute("roles").toString());
			model.addAttribute("userList", daoUsers.findAll());
			return new ModelAndView("mongo");
		} catch (Exception e) {
			throw new Exception("Error al traer los usuarios de mongo: " + e.getMessage() + " : " + e.getCause());
		}

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute Usuario user) throws Exception {
		try {
			mongoApp.insert(user, "usuarios2");
			return "redirect:mongo";
		} catch (Exception e) {
			throw new Exception("Error al añadir un usuario a mongo: " + e.getCause());

		}

	}

	@RequestMapping("/")
	public String index() throws Exception {
		return "index";
	}

	@RequestMapping("/home")
	public String home() throws Exception {
		return "home";
	}

	@PostMapping("/login")
	public void loginPost() throws Exception {
		System.out.println("Entra en post login");
	}

	@RequestMapping("/login")
	public String login() throws Exception {
		return "login";
	}

	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam String search) throws Exception {
		try {
			model.addAttribute("userList", daoUsers.searchUsers(search));
			model.addAttribute("search", search);
			return "mongo";
		} catch (Exception e) {
			throw new Exception("Error al buscar un usuario en mongo: " + e.getMessage() + " : " + e.getCause());
		}
	}

	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam String delete) throws Exception {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("nombre").is(delete));
			model.addAttribute("userList", mongoApp.findAndRemove(query, Usuario.class, "usuarios2"));
			model.addAttribute("search", delete);
			return "redirect:mongo";
		} catch (Exception e) {
			throw new Exception("Error al borrar un usuario en mongo: " + e.getMessage() + " : " + e.getCause());
		}

	}

	@GetMapping("/lanzarError")
	public String lanzaError() throws Exception {
		throw new Exception("excepcion");
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

}
