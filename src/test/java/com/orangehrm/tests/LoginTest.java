package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        test = extent.createTest("Valid Login Test");

        driver.get("https://opensource-demo.orangehrmlive.com/");
        LoginPage login = new LoginPage(driver);
        login.loginToApp("Admin", "admin123");
        Assert.assertTrue(login.getCurrentUrl().contains("dashboard"), "Login Failed!");
        System.out.println("Add a  new Line");
    }
}
