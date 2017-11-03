/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	MongoTemplate mongo;
	
	@RequestMapping("/home")
	public String home(Model model){
		model.addAttribute("userList",daoUsers.findAll());
		return "home";
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(@ModelAttribute Usuario user){
		mongo.insert(user,"usuarios2");
		return "redirect:home";
	}
	@RequestMapping(value="/search")
	public String search(Model model, @RequestParam String search){
		model.addAttribute("userList", daoUsers.searchUsers(search));
		model.addAttribute("search", search);
		return "home";
	}
	
	@ExceptionHandler(Exception.class)
	public String error404(Exception ex, HttpServletResponse response){
		return "error";
	}

}
