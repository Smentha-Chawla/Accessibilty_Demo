package axedemo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Login {

	
	public static void login() throws InterruptedException {
		try {
				Robot rb = new Robot();

				// Enter user name in username field
				StringSelection username = new StringSelection("ac76889");
				// Enter password in password field
				StringSelection pwd = new StringSelection("8TTgd#7!");
				
				
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username, null);
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_V);
				rb.keyRelease(KeyEvent.VK_V);
				rb.keyRelease(KeyEvent.VK_CONTROL);

				// press tab to move to password field
				rb.keyPress(KeyEvent.VK_TAB);
				rb.keyRelease(KeyEvent.VK_TAB);
				Thread.sleep(1000);

				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_V);
				rb.keyRelease(KeyEvent.VK_V);
				rb.keyRelease(KeyEvent.VK_CONTROL);

				// press enter
				rb.keyPress(KeyEvent.VK_ENTER);
				rb.keyRelease(KeyEvent.VK_ENTER);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		Thread.sleep(5000);
		}

}
