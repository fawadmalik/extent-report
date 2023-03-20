package com.fmzm.extentreporting.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGListener implements ITestListener {

    private ExtentReports extent = new ExtentReports();
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        String htmlReportPath = System.getProperty("user.dir") + "/test-output/report.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(htmlReportPath);
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Do nothing
    }
}
