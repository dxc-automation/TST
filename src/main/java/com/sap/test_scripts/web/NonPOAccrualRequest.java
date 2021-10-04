package com.sap.test_scripts.web;

import com.sap.config.ExtentReport;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.TestData.testDescription;
import static com.sap.properties.TestData.testName;

@Listeners(TestNGListener.class)
public class NonPOAccrualRequest extends GeneralTestConfig {

    public void sendRequest(int testCase) throws Exception {
        String testCaseString = testData.getTestCase(testCase);

        // Declare what will be information printed in the report
        testName = "<b>TEST NAME</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                        "[1] Call transaction code using command field.<br>" +
                        "[2] Open system status dialog and verify transaction details.<br>" +
                        "[3] Search for specific document data entry.<br>" +
                        "[4] Validate document details.<br>" +
                        "[5] Close transaction.";

        // Start report listener
        startTestReport(testName, testDescription, testCaseString);

        // Call key points method
        keyPoints(testCase);

        // Call company code business area
        companyCodeBusinessArea(testCase);
    }


    // Method should contains only validation that correct page is opened and click continue
    private void keyPoints(int testCase) throws Exception {
        String value = testData.getTestPropertyValue_1(testCase);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("text_field"))).sendKeys(value);
        test.pass("");
    }


    // Method should contains code for selecting company code, business area and click continue
    private void companyCodeBusinessArea(int testCase) throws Exception {
        String value = testData.getTestPropertyValue_1(testCase);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("text_field"))).sendKeys(value);
        test.pass("");
    }
}