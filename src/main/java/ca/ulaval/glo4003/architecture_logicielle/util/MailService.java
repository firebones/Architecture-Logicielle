package ca.ulaval.glo4003.architecture_logicielle.util;

import java.io.File;

public interface MailService {
	public void send(String to, String subject, String text);
	public void send(String to, String subject,String text, File... attachments);

}
