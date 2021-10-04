package com.sap.config;

import com.jacob.com.Variant;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.ArrayList;

import static com.sap.config.ExtentReport.*;

public class TestNGListener extends GeneralTestConfig implements ITestListener {

    public static int passedTests;
    public static int failedTests;
    public static String failedTestName;

    private CommandField commandField = new CommandField();

    @Override
    public void onStart(ITestContext arg0) {
        System.out.println("\nEXECUTION STARTING.....");
    }


    @Override
    public void onTestStart(ITestResult arg0) {
        // Print test status in the console
        System.out.println("\nTEST STARTED: [ " +arg0.getName() + " ]");

    }


    @Override
    public void onTestSuccess(ITestResult arg0) {
        // Print test status in the console
        System.out.println("\nTEST PASSED: [ " +arg0.getName() + " ]");

        // Count passed tests
        ArrayList<ITestNGMethod> passed = new ArrayList<ITestNGMethod>();
        passed.add(arg0.getMethod());
    }


    @Override
    public void onTestFailure(ITestResult arg0) {
        // Print test status in the console
        System.out.println("\nTEST FAILED: [ " +arg0.getName() + " ]");

        // Get throwable
        Throwable throwable = arg0.getThrowable();
        // Print exception message in report
        test.fail(throwable);
        // Print stack trace into console
        throwable.printStackTrace();

        // Take screenshot failed screen
        try {
            passFailScreenshot(arg0.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Count failed tests
        ArrayList<ITestNGMethod> failed = new ArrayList<ITestNGMethod>();
        failed.add(arg0.getMethod());
        for (ITestNGMethod s : failed) {
            failedTestName = failed.get(0).getMethodName();
        }
    }


    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("\nTEST SKIPPED: [ " + arg0.getName() + " ]");
    }


    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\nEXECUTION FINISH......");
        passedTests = context.getPassedTests().size();
        failedTests = context.getFailedTests().size();

        System.out.println("***** Passed  Tests: [ " + context.getPassedTests().size()    + " ]");
        System.out.println("***** Failed  Tests: [ " + context.getFailedTests().size()    + " ]");
        System.out.println("***** Skipped Tests: [ " + context.getSkippedTests().size()   + " ]");
    }



    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }
}