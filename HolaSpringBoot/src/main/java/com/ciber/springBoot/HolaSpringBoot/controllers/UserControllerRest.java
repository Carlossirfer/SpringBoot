/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.springBoot.HolaSpringBoot.beans.User;
import com.ciber.springBoot.HolaSpringBoot.services.UserService;

/**
 * @author ciber
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserControllerRest {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() throws Exception {
		try {
			return userService.findAll();
		} catch (Exception e) {
			throw new Exception("Error en UserController, getAllUsers(): " + e.getMessage() + " : " + e.getCause());
		}
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable long userId) throws Exception {
		try {
			User user = userService.findById(userId);
			ResponseEntity<User> response;
			if (user == null) {
				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				response = new ResponseEntity<>(user, HttpStatus.OK);
			}
			return response;

		} catch (Exception e) {
			throw new Exception("Error en UserController, getUser(): " + e.getMessage() + " : " + e.getCause());
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void insertUser(@RequestBody User user) throws Exception {
		try {
			userService.save(user);
		} catch (Exception e) {
			throw new Exception("Error en UserController, insertUser(): " + e.getMessage() + " : " + e.getCause());
		}

	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@PathVariable long userId, @RequestBody User user) throws Exception {
		try {
			User userOld = userService.findById(userId);
			if (userOld != null) {
				userService.update(userOld.copyFrom(user));
			}
		} catch (Exception e) {
			throw new Exception("Error en UserController, updateUser(): " + e.getMessage() + " : " + e.getCause());
		}

	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable long userId) throws Exception {
		try {
			userService.delete(userId);
		} catch (Exception e) {
			throw new Exception("Error en UserController, deleteUser(): " + e.getMessage() + " : " + e.getCause());
		}

	}
}
