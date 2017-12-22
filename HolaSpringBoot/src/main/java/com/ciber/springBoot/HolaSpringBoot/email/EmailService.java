/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @author ciber
 *
 */
@Component
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
 	
	public void send(Email eParams) {
		if (eParams.isHtml()) {
			try {
				sendHtmlMail(eParams);
			} catch (MessagingException e) {
			}
		} else {
			sendPlainTextMail(eParams);
		}
	}
 
	private void sendHtmlMail(Email eParams) throws MessagingException {
		boolean isHtml = true;
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED );
		helper.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
		helper.setReplyTo(eParams.getFrom());
		helper.setFrom(eParams.getFrom());
		helper.setSubject(eParams.getSubject());
		helper.setText(eParams.getMessage(), isHtml);
		ClassLoader classLoader = getClass().getClassLoader();
		FileSystemResource file=new FileSystemResource(new File(classLoader.getResource("demo-template.pdf").getFile()));
		helper.addAttachment("pdf", file);
		if (eParams.getCc().size() > 0) {
			helper.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
		}
		mailSender.send(message);
	}
 
	private void sendPlainTextMail(Email eParams) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		eParams.getTo().toArray(new String[eParams.getTo().size()]);
		mailMessage.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
		mailMessage.setReplyTo(eParams.getFrom());
		mailMessage.setFrom(eParams.getFrom());
		mailMessage.setSubject(eParams.getSubject());
		mailMessage.setText(eParams.getMessage());
		if (eParams.getCc().size() > 0) {
			mailMessage.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
		}
		mailSender.send(mailMessage);
	}
}
