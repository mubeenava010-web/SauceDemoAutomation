package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.base.TestBase;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;

public class InventoryTest extends TestBase{
	
	@Test
    public void testAddProductToCart() throws InterruptedException {
        // 1. Login to Application
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        
        // 2. Verify Page Title
        String title = inventoryPage.getPageTitle();
        Assert.assertEquals(title, "Products", "Inventory Page title mismatch!");

        // 3. Add Item to Cart
        inventoryPage.addBackpackToCart();
        
        // Waiting
        Thread.sleep(2000);

        // 4. Verify Cart Badge Count is 1
        String cartCount = inventoryPage.getCartBadgeCount();
        Assert.assertEquals(cartCount, "1", "Cart count did not update correctly!");
    }

}
