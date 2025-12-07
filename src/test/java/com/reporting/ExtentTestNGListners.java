package com.reporting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestNGListners implements ITestListener{
	
		private static ExtentReports extent = ExtentManager.getInstance();
		private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
		// To Handle collision during parallel Execution
	
	public static ExtentTest currentTest() {
		return test.get();
	}
	
	  public void onTestStart(ITestResult result) {
		  ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		  test.set(extentTest);
		  }
	  
	  public void onTestSuccess(ITestResult result) {
		  test.get().pass("Test Passed");
		  }
	  
	  public void onTestFailure(ITestResult result) {
		   test.get().fail(result.getThrowable());
		  }
	  
	  public void onTestSkipped(ITestResult result) {
		  test.get().skip(result.getThrowable());
		  }
	  
	  public  void onFinish(ITestContext context) {
		    extent.flush();
		    System.out.println();
		  }
	
}
