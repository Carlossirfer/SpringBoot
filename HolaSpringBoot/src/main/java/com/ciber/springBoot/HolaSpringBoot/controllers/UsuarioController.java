/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import javax.servlet.http.HttpServletResponse;

import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ciber.springBoot.HolaSpringBoot.beans.Usuario;
import com.ciber.springBoot.HolaSpringBoot.daoMongo.DaoUsers;
import com.ciber.springBoot.HolaSpringBoot.domain.Client;

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
	MongoTemplate mongo;

	@Autowired
	private MongoCollection users;

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping("/mongo")
	public ModelAndView home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		Client client = users.findOne("{#: #}", Client.USERNAME, userDetails.getUsername()).as(Client.class);
		model.addAttribute("roles", client.getRoles());
		model.addAttribute("userList", daoUsers.findAll());
		return new ModelAndView("mongo");
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute Usuario user) {
		mongo.insert(user, "usuarios2");
		return "redirect:mongo";
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/home")
	public String home() {
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

	@ExceptionHandler(Exception.class)
	public ModelAndView accesoDenegado(Exception ex, HttpServletResponse response) {
		return new ModelAndView("error","Exception",ex);
	}

}
