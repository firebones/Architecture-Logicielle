package ca.ulaval.glo4003.architecture_logicielle.util;



public interface IMailService {
	public void send(String to, String subject, String text) throws Exception;

}
