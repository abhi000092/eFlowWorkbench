package com.workbench.client.core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class ListenerUtils extends TestListenerAdapter {
	
	public ListenerUtils() {
		Log.setLogger("ListenerUtils");
	}


	public void onTestStart(ITestResult result) {

		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$       " + "Test Case started: " + result.getName()
				+ "    $$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("X");
		System.out.println("X");
	}

	@Step("Test Passed. Screenshot is Attached:")
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("X");
		System.out.println("X");
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXX    " + "TEST CASE PASSED:" + result.getName() + "XXXXXXXXXXXXXXXXXXXXXX");
		takeScreenShot(result.getName());
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
	}

	@Step("Test failed. Please take a look at the attachments:")
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("X");
		System.out.println("X");
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXX    " + "TEST CASE FAILED:" + result.getName() + "    XXXXXXXXXXXXXXXXXXXXXX");
		takeScreenShot(result.getName());
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
	}

	@Step("Test Skipped. Please take a look at the attachments:")
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("X");
		System.out.println("X");
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXX    " + "TEST CASE SKIPPED:" + result.getName() + "    XXXXXXXXXXXXXXXXXXXXXX");
		takeScreenShot(result.getName());
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		Log.info("Number of Passed Tests: " + context.getPassedTests().getAllMethods().size());
		Log.info("Failed Tests: " + context.getFailedTests().getAllMethods());
		Log.info("Skipped Tests: " + context.getSkippedTests().getAllMethods());
	}

	@Attachment(value = "Screenshot of {0} ", type = "image/png")
	private byte[] takeScreenShot(String methodName) {

		Log.info("SCREENSHOT of the following test is taken: " + methodName);
		Log.info("----- ***** -----\n");
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
	}

	
	
	

}