package com.sap.test_scripts.web;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestData;
import com.sap.test_scripts.desktop.commonly_used.CommandField;
import com.sun.xml.bind.v2.TODO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.startTestReport;
import static com.sap.config.ExtentReport.test;
import static com.sap.properties.TestData.*;

@Listeners(TestNGListener.class)
public class WebLogin extends GeneralTestConfig {

    public void webLogin(int testCase) throws Exception {
        String testCaseString = testData.getTestCase(testCase);

        // Declare what will be information printed in the report
        testName = "<b>     NAME    </b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                        "[1] Call transaction code using command field.<br>" +
                        "[2] Open system status dialog and verify transaction details.<br>" +
                        "[3] Search for specific document data entry.<br>" +
                        "[4] Validate document details.<br>" +
                        "[5] Close transaction.";

        // Start report listener
        startTestReport(testName, testDescription, testCaseString);

        // Open login page
        driver.get(appURL);
        test.pass("");

        // Fill credentials fields
        login(testCase);
        test.pass("");
    }


    // Login
    private void login(int testCase) throws Exception {
        String usernameField = "//form[@name='PWChange']/input[@name='USERNAME']";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(usernameField))).sendKeys("dsadas");
        driver.findElement(By.xpath(usernameField)).submit();
    }


}