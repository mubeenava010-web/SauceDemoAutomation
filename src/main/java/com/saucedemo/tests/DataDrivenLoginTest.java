package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.base.TestBase;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.ExcelUtils;

public class DataDrivenLoginTest extends TestBase {

    @DataProvider(name = "loginDataFromExcel")
    public Object[][] getLoginData() {
        String excelPath = System.getProperty("user.dir") + "/src/test/resources/LoginData.xlsx";
        return ExcelUtils.getTestData(excelPath, "Sheet1");
    }

    @Test(dataProvider = "loginDataFromExcel")
    public void testLoginWithMultipleUsers(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (username.equals("standard_user")) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed for valid user!");
        } else if (username.equals("locked_out_user")) {
            String errorMsg = loginPage.getErrorMessage();
            Assert.assertTrue(errorMsg.contains("Sorry, this user has been locked out."), "Locked out error mismatch!");
        }
    }
}
