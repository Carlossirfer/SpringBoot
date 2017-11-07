/*
 * *
 *  * Copyright (c) 2015 Ivan Hristov
 *  * <p/>
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  * <p/>
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * <p/>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.ciber.springBoot.HolaSpringBoot.security;

import java.util.List;

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
		
			//TENEMOS QUE CONVERTIR LOS ROLES QUE NOS VIENEN EN UN LIST<STRING> A UN LIST<GRANTEDAUTHORITY>
			List<GrantedAuthority> authoritys;
			String roles = "";
			for (int i = 0; i < user.getRoles().size(); i++) {
				roles+=user.getRoles().get(i)+",";
			}
			
			//CREAMOS LA LISTA DE AUTHORITYS
			authoritys=AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
			loadedUser = new User(user.getUsername(), user.getPassword(), authoritys);
			
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		return loadedUser;
	}
}
