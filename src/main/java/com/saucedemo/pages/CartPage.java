package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	
	private WebDriver driver;

    // Locators
    private By cartItemName = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By removeButton = By.id("remove-sauce-labs-backpack");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public String getItemName() {
        return driver.findElement(cartItemName).getText();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void removeItem() {
        driver.findElement(removeButton).click();
    }

}
