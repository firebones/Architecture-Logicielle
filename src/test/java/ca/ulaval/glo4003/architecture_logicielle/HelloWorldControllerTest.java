package ca.ulaval.glo4003.architecture_logicielle;

import static org.junit.Assert.*;
import org.junit.Test;
import ca.ulaval.glo4003.architecture_logicielle.web.controllers.LoginController;

public class HelloWorldControllerTest {
	
	@Test
	public void rendersIndex() {
		assertEquals("index", new LoginController().index());
	}

}
