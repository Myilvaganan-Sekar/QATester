package com.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
 
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if(extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter("./extent-report.html");
			spark.config().setDocumentTitle("API Test Report");
			spark.config().setReportName("Rest Assured + TestNG");
			extent = new ExtentReports();
			extent.attachReporter(spark);
			
			extent.setSystemInfo("Env", System.getProperty("env", "qa"));
			
			extent.setSystemInfo("Executor", System.getProperty("SquadName"));
	}
		return extent;
	}
}
