package com.orangehrm.listeners;

import com.aventstack.extentreports.Status;
import com.orangehrm.base.BaseTest;
import com.orangehrm.utils.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            File dest = new File("test-output/screenshots/" + result.getName() + ".png");
            Files.copy(screenshot.toPath(), dest.toPath());
            test.log(Status.FAIL, "Test Failed. Screenshot: " + dest.getAbsolutePath());
            test.addScreenCaptureFromPath(dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
