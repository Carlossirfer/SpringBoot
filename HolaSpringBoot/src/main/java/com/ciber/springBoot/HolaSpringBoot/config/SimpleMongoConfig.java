package com.ciber.springBoot.HolaSpringBoot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author ciber
 *
 */
@Configuration
@EnableMongoRepositories(basePackages="com.ciber.springBoot.daoMongo")
public class SimpleMongoConfig {
	 

	
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost");
    }
    
    @Primary
    @Bean(name = "mongoTemplateBasePrueba")
    public MongoTemplate mongoApp() throws Exception {
        return new MongoTemplate(mongo(), "baseprueba");
    }
    
    @Bean(name = "mongoTemplateUsuariosLogin")
    public MongoTemplate mongoLogin() throws Exception {
        return new MongoTemplate(mongo(), "usuarioslogin");
    }

   
}
