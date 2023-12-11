package repository;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BasePage;

public class HomePage extends BasePage{
	
	@FindBy(xpath="//input[@type='search']")
	private WebElement searchBox;
	
	@FindBy(xpath="//button[text()='ADD TO CART']")
	private WebElement addToCart;
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void enterText(String text) {
		searchBox.sendKeys(text,Keys.ENTER);
	}
	
	public void clickAddToCart() {
		addToCart.click();
	}
	
	public void clearText() {
		searchBox.clear();
	}

}
