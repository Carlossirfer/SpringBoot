package com.ciber.springBoot.HolaSpringBoot.security;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ciber.springBoot.HolaSpringBoot.beans.MongoUser;

@Service
@Repository
public class MongoDBAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	@Qualifier(value = "mongoTemplateUsuariosLogin")
	private MongoTemplate mongo;

	@Autowired
	private HttpSession httpSession;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;

		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("username").is(username)
					.andOperator(Criteria.where("password").is(authentication.getCredentials())));
			MongoUser user = mongo.findOne(query, MongoUser.class, "users");

			// TENEMOS QUE CONVERTIR LOS ROLES QUE NOS VIENEN EN UN LIST<STRING>
			// A UN LIST<GRANTEDAUTHORITY>
			List<GrantedAuthority> authoritys;
			String roles = "";
			for (int i = 0; i < user.getRoles().size(); i++) {
				roles += user.getRoles().get(i) + ",";
			}

			// CREAMOS LA LISTA DE AUTHORITYS
			authoritys = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
			loadedUser = new User(user.getUsername(), user.getPassword(), authoritys);

			// GUARDAMOS EN LA SESION El usuario y los roles
//			httpSession.setAttribute("usuario", loadedUser.getUsername());
//			httpSession.setAttribute("roles", roles);

		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		return loadedUser;
	}
}
