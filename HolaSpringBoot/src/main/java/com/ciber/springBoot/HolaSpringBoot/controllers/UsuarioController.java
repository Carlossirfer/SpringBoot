/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ciber.springBoot.HolaSpringBoot.beans.MongoUser;
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

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping("/mongo")
	public ModelAndView home(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession httpSesion) {

		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(userDetails.getUsername()));
		MongoUser user = mongoLogin.findOne(query, MongoUser.class, "users");
		model.addAttribute("usuario", httpSesion.getAttribute("usuario").toString());
		model.addAttribute("roles", httpSesion.getAttribute("roles").toString());
		model.addAttribute("roles", user.getRoles());
		model.addAttribute("userList", daoUsers.findAll());
		return new ModelAndView("mongo");
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute Usuario user) {
		mongoApp.insert(user, "usuarios2");
		return "redirect:mongo";
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/home")
	public String home(HttpSession httpSesion, Model model) {
		model.addAttribute("usuario", httpSesion.getAttribute("usuario").toString());
		model.addAttribute("roles", httpSesion.getAttribute("roles").toString());
		return "home";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam String search) {
		model.addAttribute("userList", daoUsers.searchUsers(search));
		model.addAttribute("search", search);
		return "mongo";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam String delete) {
		Query query = new Query();
		query.addCriteria(Criteria.where("nombre").is(delete));
		model.addAttribute("userList", mongoApp.findAndRemove(query, Usuario.class,"usuarios2"));
		model.addAttribute("search", delete);
		return "redirect:mongo";

	}

	@GetMapping("/error")
	public ModelAndView accesoDenegado(Exception ex, HttpServletResponse response) {
		return new ModelAndView("error", "Exception", ex);
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

}
