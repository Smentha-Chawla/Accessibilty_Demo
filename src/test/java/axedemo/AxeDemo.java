package axedemo;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import com.deque.html.axecore.extensions.WebDriverExtensions;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import axe_reporting.AxeReportGenerator;

public class AxeDemo {

	@Test
	public void testAccess() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\AC76894\\staf-data\\webdrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://swiftenv4/");
		Login.login();
		waitForPageToLoad(driver, 60);

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

	private static void waitForPageToLoad(WebDriver driver, int timeInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeInSeconds))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class);

		wait.until(expectation);

	}

}
