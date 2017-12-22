/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciber.springBoot.HolaSpringBoot.beans.Usuario;
import com.ciber.springBoot.HolaSpringBoot.email.Email;
import com.ciber.springBoot.HolaSpringBoot.email.EmailService;
import com.ciber.springBoot.HolaSpringBoot.email.EmailTemplate;

/**
 * @author ciber
 *
 */
@Service
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	
	//Enviar email
	public void enviarEmail(Usuario usuario){
		
		String from = "carlossirfer@gmail.com";
		String to = usuario.getEmail();
		String subject = "Email Java desde Spring boot";

		EmailTemplate template = new EmailTemplate("hello-world.html");

		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("user", "Pavan");
		replacements.put("today", String.valueOf(new Date()));

		String message = template.getTemplate(replacements);

		Email email = new Email(from, to, subject, message);
		email.setHtml(true);
		emailService.send(email);
	}
	
}
