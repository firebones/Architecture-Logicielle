package ca.ulaval.glo4003.architecture_logicielle.util;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
  

@Scope("singleton")
@Service("SendMailMessageService")
public class MailServiceImpl implements MailService {
	
	@Autowired
    private MailSender mailSender;
    
    public void setMailSender(MailSender mailSender) {  
        this.mailSender = mailSender;  
    }

	@Override
	public void send(String to, String subject, String text) throws Exception {
		SimpleMailMessage msg = new SimpleMailMessage();
		
	     
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        
 
        try{
            mailSender.send(msg);
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());
        }
		
	}

	@Override
	public void aprovedSend(String to) throws Exception {
		
		String subject= "Transaction Aproved";
		String text = "The Week Entry is aproved";
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
	     
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        
 
        try{
            mailSender.send(msg);
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());
        }
		
	}

	@Override
	public void refusedSend(String to) throws Exception {
		String subject= "Transaction refused";
		String text = "The Week Entry is refused";
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
	     
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        
 
        try{
            mailSender.send(msg);
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());
        }
		
	}  

}
