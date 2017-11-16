/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.daoMongo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ciber.springBoot.HolaSpringBoot.beans.Usuario;

/**
 * @author ciber
 *
 */
@Repository
public class DaoUsers {

	@Autowired
	@Qualifier(value = "mongoTemplateBasePrueba")
	MongoTemplate mongo;

	public Iterable<Usuario> findAll() {
		return mongo.findAll(Usuario.class, "usuarios2");
	}

	public Collection<Usuario> searchUsers(String text) {

		return (Collection<Usuario>) mongo.find(Query.query(new Criteria()
				.orOperator(Criteria.where("nombre").regex(text, "i"), Criteria.where("apellidos").regex(text, "i"))),
				Usuario.class, "usuarios2");

	}

}
