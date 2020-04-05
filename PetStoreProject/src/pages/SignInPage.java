package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

	private static WebDriver driver;
	private static Properties locators;
	private WebDriverWait waiter;

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public static WebElement getUser() {
		return driver.findElement(By.xpath(locators.getProperty("username")));

	}

	public static WebElement getPassword() {
		return driver.findElement(By.xpath(locators.getProperty("password")));
	}

	public static WebElement getLogIn() {
		return driver.findElement(By.xpath(locators.getProperty("logInBtn")));
	}

	public static void setUser(String firstName) {
		getUser().clear();
		getUser().sendKeys(firstName);
	}

	public static void setPassword(String password) {
		getPassword().clear();
		getPassword().sendKeys(password);

	}

	public static WebElement getRegisterBtn() {
		return driver.findElement(By.xpath(locators.getProperty("registerBtn")));

	}

	public static void LogIn(String username, String password) {
		getUser().clear();
		getUser().sendKeys(username);
		getPassword().clear();
		getPassword().sendKeys(password);
		getLogIn().click();
	}

	public boolean isLogged() {
		boolean isLogged = true;
		try {
			driver.findElement(By.xpath(this.locators.getProperty("wellcomeMessage")));
		} catch (Exception e) {
			isLogged = false;
		}
		return isLogged;
	}
}