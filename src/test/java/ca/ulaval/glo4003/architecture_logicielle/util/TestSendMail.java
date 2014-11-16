package ca.ulaval.glo4003.architecture_logicielle.util;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
  


//@ContextConfiguration(locations = { "file:mail/mailcontext.xml"})
public class TestSendMail {

	private static final Log log = LogFactory.getLog(TestSendMail.class);  
	  
    @Resource  
    private IMailService mailService;  
  
    
    @Test  
    public void cantSendMails() {  
    	BeanFactory beanFactory = new ClassPathXmlApplicationContext("file:mail/mailcontext.xml");
    	IMailService sendMessage=(IMailService)beanFactory.getBean(IMailService.class);
		String to = "sebasbravo@gmail.com";
		String subject = "Test Mail";
		String text = "Tes mail architecture 2014";
       
		try {  
			sendMessage.send(to, subject, text);
           
        }  
        catch(Exception e){  
            log.trace(" Controlled Excepción, normal in enviroment test",e);  
        }  
    }  


}
