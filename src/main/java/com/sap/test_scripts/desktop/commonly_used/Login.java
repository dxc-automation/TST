package com.sap.test_scripts.desktop.commonly_used;

import com.jacob.activeX.ActiveXComponent;
import com.sap.config.GeneralTestConfig;
import com.sap.config.TestNGListener;
import com.sap.properties.TestDataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.sap.config.ExtentReport.*;
import static com.sap.properties.TestData.*;

@Listeners(TestNGListener.class)
public class Login extends GeneralTestConfig {


    // ***  Element Locators
    private String windowTitle  = "SAP Logon 760";
    private String logonBtnId   = "1068";
    private String logonBtnText = "&Log On";
    private final String usernameTxtField = "wnd[0]/usr/txtRSYST-BNAME";
    private final String passwordTxtField = "wnd[0]/usr/pwdRSYST-BCODE";
    private final String enterButton  = "wnd[0]/tbar[0]/btn[0]";


    public void launchSAPLogonApp() throws Exception {
        String testCaseString = "SAP GUI Logon";

        testName = "<b>SAP GUI Logon</b>";
        testDescription =
                "<br><b>* * * &Tab; T E S T &nbsp; &nbsp; S T E P S &Tab; * * *</b><br>" +
                "[1] Launch SAP GUI Logon application.<br>" +
                "[2] Validate login to Test [TST] server.<br>";

        startTestReport(testName, testDescription, testCaseString);
        test.pass("Verify that SAP Logon 760 application was launched successfully");

    }

    public void setAccount(String accountType) throws Exception {
        switch (accountType) {
            case "reviewer":
                username = testData.getAccountingReviewerUser();
                password = testData.getAccountingReviewerPass();
                break;
            case "manager":
                username = testData.getManagerUser();
                password = testData.getManagerPass();
                break;
            case "requester":
                username = testData.getRequesterUser();
                password = testData.getRequesterPass();
                break;
        }
    }

    public void logonServer() throws IOException, InterruptedException {
        autoItX.winWait(windowTitle);
        autoItX.winActivate(windowTitle);
        autoItX.controlFocus(windowTitle, logonBtnText, logonBtnId);
        autoItX.sleep(delay);
        autoItX.controlClick(windowTitle, logonBtnText, logonBtnId);

        autoItX.sleep(2000);
        getSession();
        Obj = new ActiveXComponent(Session.invoke("FindById", usernameTxtField).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("Text", username);
        autoItX.sleep(delay);

        Obj = new ActiveXComponent(Session.invoke("FindById", passwordTxtField).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.setProperty("Text", password);
        autoItX.sleep(delay);

        Obj = new ActiveXComponent(Session.invoke("FindById", enterButton).toDispatch());
        Obj.invoke("setFocus");
        autoItX.sleep(delay);
        Obj.invoke("press");
        test.pass("Verify that login functionality is working properly<br>Username <b>" + username + "</b><br>Password <b>" + commons.getMasketPassword(password) + "</b>");
    }
}

