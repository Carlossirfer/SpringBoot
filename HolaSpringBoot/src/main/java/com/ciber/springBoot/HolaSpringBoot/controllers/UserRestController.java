/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.springBoot.HolaSpringBoot.beans.UserRest;
import com.ciber.springBoot.HolaSpringBoot.rest.UserService;

/**
 * @author ciber
 *
 */
@RestController
@RequestMapping("/api/usuarios")
public class UserRestController {

	@Autowired
	public UserService userService;

	
	@GetMapping
	public List<UserRest> getAll() {
		return Arrays.asList(userService.getAllUsers());
	}

	@GetMapping("/{nombre}")
	public UserRest getUser(@PathVariable("nombre") String nombre) {
		return userService.getUser(nombre);
	}
	
	@PostMapping
	public UserRest addUser(@RequestBody UserRest usuario){
		return userService.addUser(usuario);
	}
	
	@DeleteMapping("/{nombre}")
	public void deleteUser(@PathVariable("nombre") String nombre){
		userService.deleteUser(nombre);
	}
	
	@PutMapping("/{nombre}")
	public void updateUser(@RequestBody UserRest usuario, @PathVariable("nombre") String nombre){
		userService.updateUser(usuario, nombre);
	}

}
