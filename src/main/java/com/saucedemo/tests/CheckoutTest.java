package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.base.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;

public class CheckoutTest extends TestBase {

    @Test
    public void testCompleteE2ECheckoutFlow() throws InterruptedException {
        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // 2. Add product & Go to Cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();

        // 3. Click Checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        Thread.sleep(1500); // Visual clarity

        // 4. Fill Checkout Information
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutDetails("Mubeena", "V A", "682001");
        checkoutPage.clickContinue();

        Thread.sleep(1500);

        // 5. Click Finish
        checkoutPage.clickFinish();

        // 6. Verify Success Order Confirmation
        String successMsg = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(successMsg, "Thank you for your order!", "Order placement failed!");
    }

}
