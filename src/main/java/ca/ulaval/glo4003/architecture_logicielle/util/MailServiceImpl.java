package ca.ulaval.glo4003.architecture_logicielle.util;

import java.io.File;

import javax.mail.MessagingException;  
import javax.mail.internet.MimeMessage; 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResource;  
import org.springframework.mail.javamail.JavaMailSenderImpl; 
import org.springframework.mail.javamail.MimeMessageHelper;  
import org.springframework.util.Assert;  

public class MailServiceImpl implements MailService {
	
	private static final Log log = LogFactory.getLog(MailServiceImpl.class);
	
	//Wrapper spring about javax.mail
	private JavaMailSenderImpl mailSender;
	
	private String from;
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	//flag to indicate the service is active
	public boolean active = true;
	
	public boolean isActive(){
		return active;
	}
	
	public void setActive(boolean active){
		this.active= active;
	}
	
	private static final File[] NO_ATTACHMENTS = null;  

	@Override
	public void send(String to, String subject, String text) {
		send(to, subject, text); 
	}

	@Override
	public void send(String to, String subject, String text, File... attachments) {
		 // check parameters   
        Assert.hasLength(to, "email 'to' needed");  
        Assert.hasLength(subject, "email 'subject' needed");  
        Assert.hasLength(text, "email 'text' needed");  
  
        
        if (log.isDebugEnabled()) {  
            final boolean usingPassword = !"".equals(mailSender.getPassword());  
            log.debug("Sending email to: '" + to + "' [through host: '" + mailSender.getHost() + ":"  
                    + mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"  
                    + usingPassword + "].");  
            log.debug("isActive: " + active);  
        }  
        // The service is active?
        if (!active) return;  
  
        
        final MimeMessage message = mailSender.createMimeMessage();  
  
        try {  
              
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);  
              
            // settings the parameters 
            helper.setTo(to);  
            helper.setSubject(subject);  
            helper.setFrom(getFrom());  
            helper.setText(text);  
  
            // attach files 
            if (attachments != null) {  
                for (int i = 0; i < attachments.length; i++) {  
                    FileSystemResource file = new FileSystemResource(attachments[i]);  
                    helper.addAttachment(attachments[i].getName(), file);  
                    if (log.isDebugEnabled()) {  
                        log.debug("File '" + file + "' attached.");  
                    }  
                }  
            }  
  
        } catch (MessagingException e) {  
            new RuntimeException(e);  
        }  
          
        // send  
        this.mailSender.send(message);  
    }  

}
