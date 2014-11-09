package ca.ulaval.glo4003.architecture_logicielle.util;

import java.util.Properties;
import javax.

public class ToolsMail {
	
public void sendMail(){
	try{
		// properties to  conection
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", "tparchitecture2014@gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        
     
        Session session = Session.getDefaultInstance(props);

        // make the message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sebasbravo@gmail.com"));//test mail
        message.addRecipient(
            Message.RecipientType.TO,
            new InternetAddress("tparchitecture2014@gmail.com"));
        message.setSubject("test");
        message.setText(
            "Test mail");

        // Lo enviamos.
        Transport t = session.getTransport("smtp");
        t.connect("chuidiang@gmail.com", "la clave");
        t.sendMessage(message, message.getAllRecipients());

        // Cierre.
	
	
}catch(Exception e){
	e.printStackTrace();
}	
}

}
