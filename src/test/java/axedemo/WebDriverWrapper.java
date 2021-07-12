package axedemo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class WebDriverWrapper {
	
	WebDriver driver;
	int defaultTimeoutInSeconds;
	
	public WebDriverWrapper(WebDriver driver, int defaultTimeout) {
		this.defaultTimeoutInSeconds = defaultTimeout;
		this.driver = driver;
	}
	
	public void waitForPageToLoad() {
		waitForPageToLoad(defaultTimeoutInSeconds);
	}
	
	public void waitForPageToLoad(int timeInSeconds) {
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
	
	public void waitForElement(By by) {
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(defaultTimeoutInSeconds))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));

	}
	
	public void waitAndClick(By by) {
		waitForElement(by);
		driver.findElement(by).click();
	}
	
	public void waitAndSendKeys(By by, String text) {
		waitForElement(by);
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(text);
	}

}
