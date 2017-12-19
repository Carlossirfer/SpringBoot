/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;

import com.ciber.springBoot.HolaSpringBoot.beans.Usuario;

/**
 * @author ciber
 *
 */
@Controller
public class DaoMongo {

	@Autowired
	@Qualifier(value = "mongoTemplateBasePrueba")
	MongoTemplate mongoApp;

	// OBTENEMOS LOS USUARIOS DE MONGO
	public List<Usuario> getAllUsers() {
		return mongoApp.findAll(Usuario.class, "usuarios2");
	}

	// GUARDAMOS UN USUARIO EN MONGO
	public void addUserMongo(Usuario user) {
		mongoApp.insert(user, "usuario2");
	}

	// REALIZAMOS UNA BUSQUEDA EN MONGO
	public List<Usuario> searchUsers(Usuario usuario) {
		return mongoApp.find(Query.query(new Criteria()
				.orOperator(Criteria.where("nombre").regex(usuario.getNombre(), "i"), Criteria.where("apellidos").regex(usuario.getApellidos(), "i"))),
				Usuario.class, "usuarios2");
	}
	
	// BORRAMOS UN USUARIO EN MONGO
	public void deleteUserMongo(Usuario usuario) {
		mongoApp.remove(usuario,"usuarios2");
	}

}
