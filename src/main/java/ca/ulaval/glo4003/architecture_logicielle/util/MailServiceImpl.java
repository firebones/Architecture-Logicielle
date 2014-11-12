package ca.ulaval.glo4003.architecture_logicielle.util;

import java.io.File;

import javax.mail.MessagingException;  
import javax.mail.internet.MimeMessage; 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResource;  
import org.springframework.m
import org.springframework.mail.javamail.MimeMessageHelper;  
import org.springframework.util.Assert;  

public class MailServiceImpl implements MailService {
	
	private static final Log log = LogFactory.getLog(MailServiceImpl.class);
	
	//Wrapper spring about javax.mail
	private JavaMailSenderImpl mailSender;

	@Override
	public void send(String to, String subject, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(String to, String subject, File... attachments) {
		// TODO Auto-generated method stub

	}

}
