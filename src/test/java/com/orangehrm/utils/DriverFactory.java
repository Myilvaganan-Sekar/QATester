package com.orangehrm.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driver == null) {
        	ChromeOptions options = new ChromeOptions();
        	options.addArguments("--headless=new");   // REQUIRED for GitHub Actions
        	options.addArguments("--no-sandbox");
        	options.addArguments("--disable-dev-shm-usage");
        	options.addArguments("--window-size=1920,1080");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            System.out.println("Browser maximized");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
