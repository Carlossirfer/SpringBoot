/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ciber.springBoot.HolaSpringBoot.beans.UserRest;
import com.ciber.springBoot.HolaSpringBoot.constants.Constants;

/**
 * @author ciber
 *
 */

@Service
public class UserService {

	@Autowired
	public RestTemplate restTemplate;

	
	//OBTENER TODOS LOS USUARIOS
	public UserRest[] getAllUsers() {
		return restTemplate.getForEntity(Constants.API_USERS, UserRest[].class).getBody();
	}

	//OBTENER UN USUARIO
	public UserRest getUser(String name) {
		ResponseEntity<UserRest> response = restTemplate.getForEntity(Constants.API_USERS + name, UserRest.class, 12L);
		return response.getBody();
	}
	
	//AÑADIR UN USUARIO
	public UserRest addUser(UserRest user){
		return restTemplate.postForObject(Constants.API_USERS, user, UserRest.class);
	}
	
	//BORRAR UN USUARIO
	public void deleteUser(String nombre){
		restTemplate.delete(Constants.API_USERS + nombre, nombre);
	}
	
	//MODIFICAR UN USUARIO
	public void updateUser(UserRest usuario, String nombre){
		restTemplate.put(Constants.API_USERS + nombre, usuario, nombre);
	}
	
	

}
