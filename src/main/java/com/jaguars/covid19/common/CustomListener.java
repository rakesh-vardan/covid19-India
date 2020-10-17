package com.jaguars.covid19.common;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import com.epam.reportportal.service.ReportPortal;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener {
	static final Logger logger=LogManager.getLogger(CustomListener.class);
	
	String filePath = "target/Screenshots";
	
	
	public void onTestFailure(ITestResult result) {		
		String methodName = result.getName().toString().trim();
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		logger.info("Driver from test context: "+driver);
		File screenshotFile = takeScreenShot(methodName, driver);
		ReportPortal.emitLog("Screenshot", "ERROR", Calendar.getInstance().getTime(), screenshotFile);
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("The name of the testcase Skipped is :" + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

	}

	@Override
	public void onStart(ITestContext iTestContext) {

	}

	@Override
	public void onFinish(ITestContext iTestContext) {

	}

	// When Test case get Started, this method is called.
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName() + " test case started");
	}

	// When Test case get passed, this method is called.
	public void onTestSuccess(ITestResult result) {
		System.out.println("The name of the testcase passed is :" + result.getName());
	}

	public File takeScreenShot(String methodName, WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath + File.separator+ methodName + ".png"));
			System.out.println("***Placed screen shot in " + filePath + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scrFile;
	}
}
