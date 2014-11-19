package ca.ulaval.glo4003.architecture_logicielle.util;



public interface MailService {
	public void send(String to, String subject, String text) throws Exception;

}
