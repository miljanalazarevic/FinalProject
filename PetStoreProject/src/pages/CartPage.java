package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public List<WebElement> getAllItems() {
		return this.driver.findElements(By.xpath(locators.getProperty("productRows")));
	}

	public WebElement getItem(int rowIndex) {
		return this.getAllItems().get(rowIndex).findElement(By.xpath(this.locators.getProperty("itemId")));
	}

	public WebElement getProduct(int rowIndex) {
		return this.getAllItems().get(rowIndex).findElement(By.xpath(this.locators.getProperty("productID")));
	}

	public WebElement getDescription(int rowIndex) {
		return this.getAllItems().get(rowIndex).findElement(By.xpath(this.locators.getProperty("description")));

	}

	public WebElement getStock(int rowIndex) {
		return this.getAllItems().get(rowIndex).findElement(By.xpath(this.locators.getProperty("inStock")));
	}

	public WebElement getQuantity(int rowIndex) {
		return this.getAllItems().get(rowIndex).findElement(By.xpath(this.locators.getProperty("quantity")));
	}

	public WebElement getPrice(int rowIndex) {
		return this.getAllItems().get(rowIndex).findElement(By.xpath(this.locators.getProperty("price")));
	}

	public WebElement getTotalCost(int rowIndex) {
		return this.getAllItems().get(rowIndex).findElement(By.xpath(this.locators.getProperty("cost")));
	}

	public WebElement getRemove(int rowIndex) {
		return this.getAllItems().get(rowIndex).findElement(By.xpath(this.locators.getProperty("remove")));
	}

	public WebElement getProceedToCheckout() {
		return driver.findElement(By.xpath(locators.getProperty("checkoutBtn")));

	}

	public List<WebElement> getItemsId() {
		return this.driver.findElements(By.xpath(locators.getProperty("itemIdList")));

	}

	public boolean areItemsAdded(String itemId) {
		List<WebElement> listId = this.getItemsId();
		for (int i = 0; i < listId.size(); i++) {
			if(listId.get(i).getText().contentEquals(itemId)) {
				return true;
			}
		}
		return false;
	}

	public boolean cartIsEmpty() {
		boolean empty = true;
		try {
			driver.findElement(By.xpath(locators.getProperty("cartIsEmptyMessage")));

		} catch (Exception e) {
			empty = false;
		}
		return empty;

	}

}
