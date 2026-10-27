package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.base.TestBase;
import com.saucedemo.pages.LoginPage;

public class LoginTest extends TestBase{
	
	@Test(priority = 1)
	public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login execution failed!");
    }
	
	@Test(priority = 2)
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        
        String errorText = loginPage.getErrorMessage();
        Assert.assertTrue(errorText.contains("Sorry, this user has been locked out."));
	}

}
