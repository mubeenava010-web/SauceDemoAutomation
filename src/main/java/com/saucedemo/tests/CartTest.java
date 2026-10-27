package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.base.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;

public class CartTest extends TestBase {

    @Test
    public void testCartItemAndCheckoutNavigation() throws InterruptedException {
        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // 2. Add product to cart & navigate to cart page
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();

        Thread.sleep(1500); // Visual clarity small pause

        // 3. Verify Item in Cart Page
        CartPage cartPage = new CartPage(driver);
        String addedItemName = cartPage.getItemName();
        
        Assert.assertEquals(addedItemName, "Sauce Labs Backpack", "Cart item mismatch!");

        // 4. Click Checkout
        cartPage.clickCheckout();
        
        // Verify user navigated to Checkout Step One
        boolean isNavigated = driver.getCurrentUrl().contains("checkout-step-one.html");
        Assert.assertTrue(isNavigated, "Failed to navigate to Checkout Page!");
//        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"), "Failed to navigate to Checkout Page!");
    }

}
