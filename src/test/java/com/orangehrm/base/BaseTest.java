package com.orangehrm.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.orangehrm.utils.DriverFactory;
import com.orangehrm.utils.ExtentReportManager;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
    
    
}
