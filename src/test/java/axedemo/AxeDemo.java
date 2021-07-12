package axedemo;

import java.io.IOException;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.deque.html.axecore.extensions.WebDriverExtensions;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import axe_reporting.AxeReportGenerator;

public class AxeDemo {

	@Test
	public void testAccess() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\AC76889\\staf-data\\webdrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		WebDriverWrapper wd = new WebDriverWrapper(driver, 20);

		driver.manage().window().maximize();
		driver.get("https://webtest1.lumen.com/login/");
		wd.waitForPageToLoad(60);
		wd.waitAndClick(By.id("continueBtn"));
		wd.waitForPageToLoad(60);
		By usrname = By.id("loginForm:username");
		By pass = By.id("loginForm:password");
		By loginBtn = By.id("loginForm:loginButton");
		wd.waitAndSendKeys(usrname, "49089automationtest1@test1.control.centurylink.com");
		wd.waitAndSendKeys(pass, "Qcontrol12345");
		wd.waitAndClick(loginBtn);
		wd.waitForPageToLoad(60);
		By shop = By.cssSelector("a[href='#enterprise-drawer-6']");
		wd.waitAndClick(shop);
		By shop_drawer = By.xpath("//span[text()='Shop' and @class='chi-sidenav__title']");
		wd.waitAndClick(shop_drawer);
		wd.waitForPageToLoad(60);
		try {
			Results results = WebDriverExtensions.analyze(driver);
			List<Rule> violations = results.getViolations();
			int totalViolations=0;
			for(Rule violation: violations)
			{
				System.out.println(violation);		
				System.out.println(violation.getNodes().size());
				totalViolations=totalViolations+violation.getNodes().size();
			}
			System.out.println("totalViolations"+totalViolations);	
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String json_violations = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(violations);
			AxeReportGenerator.axe_report_generator("Ninja's DEMO AXE Reporter", results.getUrl(),results.getTimestamp(), totalViolations,json_violations);

		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("done");
		driver.quit();

	}

}
